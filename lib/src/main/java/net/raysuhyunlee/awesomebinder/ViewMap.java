package net.raysuhyunlee.awesomebinder;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SuhyunLee on 2015. 11. 23..
 */
public class ViewMap {
    protected HashMap<String, ArrayList<View>> map;
    public ViewMap() {
        map = new HashMap<>();
    }

    public void put(String key, View view) {
        if (!map.containsKey(key))
            map.put(key, new ArrayList<View>());

        map.get(key).add(view);
    }

    public ArrayList<View> getAll(String key) {
        return map.get(key);
    }

    public View get(String key, int index) {
        return map.get(key).get(index);
    }
}
