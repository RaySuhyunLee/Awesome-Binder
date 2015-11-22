package net.raysuhyunlee.awesomebinder;

import android.databinding.BaseObservable;
import android.databinding.BindingConversion;

/**
 * Created by SuhyunLee on 2015. 11. 19..
 */
public class BindableString extends BindableObject<String> {
    private String value;

    public String get() {
        return value;
    }

    public void set(String value) {
        this.value = value;
        notifyChange();
    }

    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }

    @BindingConversion
    public static String convertBindableToString(BindableString bindableString) {
        return bindableString.get();
    }
}
