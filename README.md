**NOTE Current in development. If you are curious, you can use code in development branch issue/#1. Thanks!**

#Awesome Binder for Android
*"Android UI Programming has never been this easy!"*  
*"No pain, Yes gain"*

##What is it?
AwesomeBinder is a two-way databinding library that supports observables. It is aimed to present an AngularJS-like experience to Android Developers. In other words, you can create a fully reactive layout without painful, annoying Java UI coding.

##What does it do?
AwesomeBinder does many things, and that's why it is AWESOME.

###1. Data Binding In One Line

**With Vanilla Android**  
```Java
// MyActivity.java
public class MyActivity extends Activity {
  ...
  public void onCreate(...) {
    setContentView(R.layout.simple);
    button = (Button)findViewById(R.id.button);
    textView = (TextView)findViewById(R.id.textView);
    editText = (EditText)findViewById(R.id.editText);
    checkBox = (CheckBox)findViewById(R.id.checkBox);
    button.setText("I");
    textView.setText("Really");
    editText.setText("Hate");
    checkBox.setText("This");
  }
}
```

```xml
<!-- simple.xml -->
<LinearLayout ...>
  <Button ...
    android:id="@+id/button" />
  <TextView ...
    android:id="@+id/textView" />
  <EditText ...
    android:id="@+id/editText" />
  <CheckBox ...
    android:id="@+id/checkBox" />
</LinearLayout>
```

**With AwesomeBinder**  
```Java
// MyActivity.java
public class MyActivity extends Activity {
  public void onCreate(...) {
    binder = AwesomeBinder.bind(this, R.layout.simple)
        .set("{'str1':'This', 'str2':'Is',
            'str3': 'Much', 'str4': 'Better'}");
  }
}
```

```xml
<!-- simple.xml -->
<LinearLayout ...>
  <Button ...
    app:bind="str1" />
  <TextView ...
    app:bind="str2" />
  <EditText ...
    app:bind="str3" />
  <CheckBox ...
    app:bind="str4" />
</LinearLayout>
```

###2. Reactive UI  
```xml
<!-- reactive.xml -->
<LinearLayout ...>
  <EditText ...
    app:bind="input" />
  <TextView ...
    app:bind="input" />
</LinearLayout>
```

###3. Function Injection

##Contribution
Feel free to fork. 

##Licenses
I'm considering which license to use.

##Special Thanks To
[Data Binding Guide](http://developer.android.com/intl/ko/tools/data-binding/guide.html)  
[Two-way Android Data Binding](https://medium.com/@fabioCollini/android-data-binding-f9f9d3afc761#.8w9rk69sf)  
[StackOverflow](http://stackoverflow.com/questions/4685563/how-to-pass-a-function-as-a-parameter-in-java)  
