package net.raysuhyunlee.awesomebinder;

import android.app.Activity;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuhyunLee on 2015. 11. 19..
 */

public class AwesomeBinder {
    protected ListMap<View> viewMap;
    protected JSONObject valueMap;
    protected ListMap<Runnable> functionMap;

    public AwesomeBinder() {
        viewMap = new ListMap();
        valueMap = new JSONObject();
    }

    public AwesomeBinder setContentView(Activity activity, int layoutId) {
        LayoutInflater inflater = (LayoutInflater)activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(layoutId, null, false);
        activity.setContentView(content);

        XmlResourceParser parser = activity.getResources().getLayout(layoutId);
        View currentView = null; // view reference for mapping views with its xml tags
        int depth = -1;
        List<Integer> countList = new ArrayList<>();
        try {
            parser.next();
            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch(eventType) {
                    case XmlPullParser.START_TAG:
                        // initialize or increase child index count
                        depth++;
                        if (countList.size() - 1 < depth)
                            countList.add(0);
                        else
                            countList.set(depth, countList.get(depth) + 1);

                        // get view corresponding to current xml tag
                        if (currentView == null)
                            currentView = content;
                        else {
                            currentView = ((ViewGroup)currentView).getChildAt(countList.get(depth));
                        }

                        // get bind value
                        int attrCount = parser.getAttributeCount();
                        String bind = null;
                        for (int i=0; i< attrCount; i++) {
                            String attrName = parser.getAttributeName(i);
                            if (attrName.equals("bind")) { // FIXME hardcoded attribute name. fix if possible
                                bind = parser.getAttributeValue(i);
                            }
                        }
                        if (bind == null) break;
                        viewMap.put(bind, currentView);
                        if(valueMap.get(bind) == null) {
                            valueMap.put(bind, "");
                        }
                        attachListener(currentView, bind);
                        break;
                    case XmlPullParser.END_TAG:
                        if (countList.size() - 1 > depth)
                            countList.remove(depth + 1);
                        depth--;
                        currentView = (View)currentView.getParent();
                        break;
                }
                eventType = parser.next();
            }
        } catch(XmlPullParserException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        parser.close();
        return this;
    }

    private void attachListener(View view, final String key) {
        // attach value change event listener
        if(view instanceof TextView) {
            ((TextView) view).addTextChangedListener(new BaseTextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!valueMap.get(key).equals(s.toString())) {
                        valueMap.put(key, s.toString());
                        updateViews(key);
                    }
                }
            });
        }
    }

    private void updateViews(String key) {
        List<View> viewList = viewMap.getAll(key);
        String value = (String)valueMap.get(key);
        for (View v : viewList) {
            if (v instanceof TextView &&
                    !(((TextView)v).getText().toString().equals(value))) {
                ((TextView)v).setText(value);
            }
        }
    }

    private void updateAll() {
    }

    public void setValue(String key, String value) {
        valueMap.put(key, value);
        updateViews(key);
    }

    public void setFunction(String key, Runnable runnable) {
        functionMap.put(key, runnable);
        updateViews(key);
    }
}
