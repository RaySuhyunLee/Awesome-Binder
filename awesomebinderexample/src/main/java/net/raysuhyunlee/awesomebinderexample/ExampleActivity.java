package net.raysuhyunlee.awesomebinderexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import net.raysuhyunlee.awesomebinder.AwesomeBinder;
import net.raysuhyunlee.awesomebinder.AwesomeData;

/**
 * Created by SuhyunLee on 2015. 11. 19..
 */
public class ExampleActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AwesomeBinder binder = new AwesomeBinder();
        binder.setContentView(this, R.layout.activity_example);
        //binder.setValue("hello", "blabla");
    }
}
