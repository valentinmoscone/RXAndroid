package com.example.valentinmoscone.testrxandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class MainActivity extends Activity {

    @Bind(R.id.button_change_text)
    Button mButtonChange;
    @Bind(R.id.text_change_text)
    TextView mTextChange;

    Subscription subscription;
    PublishSubject<String> resultEmitterSubject;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //This is my comment, so i can do a pull request ;)

        resultEmitterSubject = PublishSubject.create();
        subscription = resultEmitterSubject.asObservable()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mTextChange.setText(s);
                    }
                });

        resultEmitterSubject.onNext("Holaaaaaaa");
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent,name,context,attrs);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
