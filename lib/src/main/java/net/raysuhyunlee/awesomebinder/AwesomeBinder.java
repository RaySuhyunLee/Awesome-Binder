package net.raysuhyunlee.awesomebinder;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by SuhyunLee on 2015. 11. 19..
 */

public class AwesomeBinder {
    protected ViewMap viewMap;
    protected JSONObject valueMap;
    protected Activity activity;

    public AwesomeBinder(Activity activity) {
        this.activity = activity;
        viewMap = new ViewMap();
        valueMap = new JSONObject();
    }

    public AwesomeBinder setContentView(int layoutId) {
        activity.setContentView(layoutId);

        XmlResourceParser parser = activity.getResources().getLayout(layoutId);
        try {
            parser.next();
            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch(eventType) {
                    case XmlPullParser.START_TAG:
                        int attrCount = parser.getAttributeCount();
                        String bind = null;
                        int id = 0;
                        for (int i=0; i< attrCount; i++) {
                            String attrName = parser.getAttributeName(i);
                            if (attrName.equals("bind")) { // FIXME hardcoded attribute name. fix if possible
                                bind = parser.getAttributeValue(i);
                            } else if(attrName.equals("id")) {
                                id = parser.getAttributeResourceValue(i, 0);
                            }
                        }
                        bindView(id, bind);
                        break;
                    case XmlPullParser.END_TAG:
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

    private void bindView(final int id, final String key) {
        if (key != null) {
            View view = activity.findViewById(id);
            viewMap.put(key, view);
            if(valueMap.get(key) == null) {
                valueMap.put(key, "");
            }

            // attach value change event listener
            if(view instanceof TextView) {
                ((TextView) view).addTextChangedListener(new BaseTextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!valueMap.get(key).equals(s.toString())) {
                            valueMap.put(key, s.toString());
                            updateViews(key);
                        }
                    }
                });
            }
        }
    }

    private void updateViews(String key) {
        ArrayList<View> viewList = viewMap.getAll(key);
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
}
