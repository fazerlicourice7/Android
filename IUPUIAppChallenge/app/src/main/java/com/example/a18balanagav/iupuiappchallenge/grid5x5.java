package com.example.a18balanagav.iupuiappchallenge;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
/**
 * Created by 18balanagav on 11/11/2016.
 */

public class grid5x5 extends AppCompatActivity {

    private static final int SIDELENGTH = 5;

    backgroundSound bgsound;
    ImageButton grid[][];
    Unit units[][];
    MediaPlayer clickSound;

    TableLayout table;
    TableRow row0;
    TableRow row1;
    TableRow row2;
    TableRow row3;
    TableRow row4;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid5x5);
        clickSound = MediaPlayer.create(this, R.raw.sound );
        bgsound = new backgroundSound();
        grid =  new ImageButton[SIDELENGTH][SIDELENGTH];
        units = new Unit[SIDELENGTH][SIDELENGTH];
        initButtons();
    }

    public  void onResume(){
        super.onResume();
        bgsound.execute((Void[]) null);
    }

    public void onPause(){
        super.onPause();
        bgsound.cancel(true);
    }

    public void onDestroy(){
        super.onDestroy();
        bgsound.cancel(true);

    }
    private void initButtons(){
        table = (TableLayout) findViewById(R.id.topLayout);
        row0 = (TableRow) findViewById(R.id.row0);
        row1 = (TableRow) findViewById(R.id.row1);
        row2 = (TableRow) findViewById(R.id.row2);
        row3 = (TableRow) findViewById(R.id.row3);
        row4 = (TableRow) findViewById(R.id.row4);
        for(int row = 0; row < SIDELENGTH; ++row){
            for(int col = 0; col < SIDELENGTH; ++col){
                grid[row][col] = new ImageButton(this);
                units[row][col].setCol(col);
                units[row][col].setRow(row);
                grid[row][col].setTag(units[row][col]);
                            grid[row][col].setImageResource(R.mipmap.ic_launcher); // set image
                grid[row][col].setOnClickListener(buttonClick);
                if(row == 0){
                    row0.addView(grid[row][col]);
                } else if(row == 1){
                    row1.addView(grid[row][col]);
                } else if(row == 2){
                    row2.addView(grid[row][col]);
                } else if(row == 3){
                    row3.addView(grid[row][col]);
                } else if(row == 4){
                    row4.addView(grid[row][col]);
                }
            }
        }
    }


    View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Unit unit = (Unit) v.getTag();
            int row = unit.getRow();
            int col = unit.getCol();
        }
    };
}

public class backgroundSound extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params){
        MediaPlayer mpr = MediaPlayer.create(this, R.raw.bgmusic);
        mpr.setLooping(true);
        mpr.setVolume(1.0f, 1.0f);
        mpr.start();

        return null;
    }
}