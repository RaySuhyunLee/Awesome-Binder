package net.raysuhyunlee.awesomebinder;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayMap;

import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * Created by SuhyunLee on 2015. 11. 19..
 */
public class AwesomeData {
    private JSONObject bindables;

    public AwesomeData() {
        super();
        bindables = new JSONObject();
    }

    private BindableObject getBindable(String key) {
        return (BindableObject) bindables.get(key);
    }

    public void set(String key, String value) {
        if (bindables.containsKey(key)) {
            getBindable(key).set(value);
        } else {
            BindableString bindableString = new BindableString();
            bindableString.set(value);
            bindables.put(key, bindableString);
        }
    }

    public BindableString get(String key) {
        return (BindableString)getBindable(key); // FIXME return bindable or raw value?
    }
}
