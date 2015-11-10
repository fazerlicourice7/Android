package com.atrium.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class GameActivity extends Activity {

    AnimatedSurface mySurfaceView;     

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new AnimatedSurface(this);
        setContentView(mySurfaceView);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mySurfaceView.onResumeMySurfaceView();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mySurfaceView.onPauseMySurfaceView();
        finish();
    }

}
