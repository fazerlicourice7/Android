package com.atrium.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by spockm on 7/1/2014.
 */
public class gameOverActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose_activity); 
        Intent intent = getIntent();
        String time = intent.getStringExtra("scoreMSG");
        TextView scoreTV = (TextView)findViewById(R.id.textView2);
        scoreTV.setText(time);
    }
    
    public void onClickRetry(View v)
    {
    	Intent i = new Intent(this, GameActivity.class);
    	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(i);
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        View decorView = getWindow().getDecorView();
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
}