package net.raysuhyunlee.awesomebinderexample;

import android.app.Activity;
import android.os.Bundle;

import net.raysuhyunlee.awesomebinder.AwesomeBinder;

/**
 * Created by SuhyunLee on 2015. 11. 19..
 */
public class ExampleActivity extends Activity {
    AwesomeBinder binder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = new AwesomeBinder();
        binder.setContentView(this, R.layout.activity_example);
    }

    public String isValid() {
        if (binder.getValue("hello").equals("hello")) {
            return "true";
        } else {
            return "false";
        }
    }
}
