package net.raysuhyunlee.awesomebinder;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Created by SuhyunLee on 2015. 11. 19..
 */

public class AwesomeBinder {
    protected JSONObject data;
    ViewDataBinding binding;

    public AwesomeBinder() {

    }

    public AwesomeBinder setContentView(Activity activity, int layoutId) {
        binding = DataBindingUtil.setContentView(activity, layoutId);
        return this;
    }

    public AwesomeBinder bind(int variableId, AwesomeData data) {
        binding.setVariable(variableId, data);
        return this;
    }

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
    }
}
