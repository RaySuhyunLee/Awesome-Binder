package net.raysuhyunlee.awesomebinder;

import android.databinding.BaseObservable;

/**
 * Created by SuhyunLee on 2015. 11. 22..
 */
public abstract class BindableObject<T> extends BaseObservable {
    public abstract T get();
    public abstract void set(T value);
}
