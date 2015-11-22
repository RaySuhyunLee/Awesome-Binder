package net.raysuhyunlee.awesomebinderexample;

import android.app.Activity;
import android.os.Bundle;

import net.raysuhyunlee.awesomebinder.AwesomeBinder;
import net.raysuhyunlee.awesomebinder.AwesomeData;

/**
 * Created by SuhyunLee on 2015. 11. 19..
 */
public class ExampleActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AwesomeData data = new AwesomeData();
        data.set("text", "blabla");
    }
}
