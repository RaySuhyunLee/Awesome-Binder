package net.raysuhyunlee.awesomebinder;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by SuhyunLee on 2015. 11. 23..
 */
public class ListMap<T> {
    protected HashMap<String, List<T>> map;
    public ListMap() {
        map = new HashMap<>();
    }

    public void put(String key, T item) {
        if (!map.containsKey(key))
            map.put(key, new ArrayList<>());

        map.get(key).add(item);
    }

    public List<T> getAll(String key) {
        return map.get(key);
    }

    public T get(String key, int index) {
        return map.get(key).get(index);
    }

    public Set<String> getKeys() {
        return map.keySet();
    }
}
