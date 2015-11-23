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
    protected AwesomeData valueMap;

    public AwesomeBinder() {
        viewMap = new ViewMap();
        valueMap = new AwesomeData();
    }

    public AwesomeBinder setContentView(Activity activity, int layoutId) {
        activity.setContentView(layoutId);

        XmlResourceParser parser = activity.getResources().getLayout(layoutId);
        try {
            parser.next();
            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch(eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
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
                        if (bind != null) {
                            final String key = bind;
                            View v = activity.findViewById(id);
                            viewMap.put(bind, v);
                            if(valueMap.get(key) == null) {
                                valueMap.set(key, "");
                            }
                            if(v instanceof TextView) {
                                ((TextView) v).addTextChangedListener(new BaseTextWatcher() {
                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        if(!valueMap.get(key).get().equals(s.toString())) {
                                            valueMap.set(key, s.toString());
                                            updateViews(key);
                                        }
                                    }
                                });
                            }
                        }
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

    private void updateViews(String key) {
        ArrayList<View> viewList = viewMap.getAll(key);
        String value = valueMap.get(key).get();
        for (View v : viewList) {
            if (v instanceof TextView) {
                ((TextView)v).setText(value);
            }
        }
    }

    // code from View class(API version >= 17)
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public void setValue(String key, String value) {
        valueMap.set(key, value);
        updateViews(key);
    }

    /*
    @BindingAdapter("awesome:bind")
    public static void setTextView(TextView view, final BindableString bindableString) {
        if (view.getTag(R.id.binded) == null) {
            view.setTag(R.id.binded, true);
            view.addTextChangedListener(new BaseTextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bindableString.set(s.toString());
                }
            });
        }
        String newValue = bindableString.get();
        if (!view.getText().equals(newValue)) {
            view.setText(newValue);
        }
    }*/
}
