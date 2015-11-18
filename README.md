#Awesome Binder for Android
A super-simple Android UI binder that makes your fingers happy

>"Android UI Programming has never been this easy!" - Anonymous Developer

##What is it?
AwesomeBinder is a two-way databinding library that supports observables. It is aimed to present an AngularJS-like experience to Android Developers.  

With Vanilla Android
```Java
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

With AwesomeBinder
```Java
public class MyActivity extends Activity {
  public void onCreate(...) {
    binder = AwesomeBinder.bind(this, R.layout.simple)
        .set("{'button':'I', 'textView':'Really', 'editText': 'Like', 'checkBox': 'This'}");
  }
}
```
