package com.example.a18balanagav.equationbalancer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setThemeDark(View view) {
        getApplication().setTheme(R.style.AppThemeDark);
        setContentView(R.layout.activity_main);
    }

    public void setThemeLight(View view) {
        getApplication().setTheme(R.style.AppThemeLight);
        setContentView(R.layout.activity_main);
    }

    public void newAct(View view) {
        Intent intent = new Intent(MainActivity.this, BalanceActivity.class);
        startActivity(intent);
    }
}
