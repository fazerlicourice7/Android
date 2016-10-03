package proj.school.chessclock;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by fazer on 10/2/16.
 */

public class ClockActivity extends AppCompatActivity {

    private static final int MILLIS = 1000;

    private int p1Time;
    private int p2Time;

    private CountDownTimer timer;

    private static boolean p1Turn = true;

    private int increment;

    Button player1Button;
    Button player2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_clock);

        Intent intent = getIntent();
        int min = intent.getIntExtra("totalMin", 5);
        Log.d("totalMins", String.valueOf(min));
        int sec = intent.getIntExtra("totalSec", 0);
        Log.d("totalSecs", String.valueOf(sec));

        p1Time = ((min * 60) + sec) * MILLIS; // needs to be in milliseconds
        p2Time = ((min * 60) + sec) * MILLIS; // needs to be in milliseconds

        int incMin = intent.getIntExtra("intMin", 0);
        Log.d("incMins", String.valueOf(incMin));
        int incSec = intent.getIntExtra("incSec", 0);
        Log.d("incSecs", String.valueOf(incSec));

        increment = ((incMin * 60) + incSec) * MILLIS;  // needs to be in milliseconds

        player1Button = (Button) (findViewById(R.id.player1Button));
        player2Button = (Button) (findViewById(R.id.player2Button));

        p1UpdateTime();
        p2UpdateTime();

        player2Button.setEnabled(false);
    }

    private void p1UpdateTime() {
        String time = (p1Time / 60000) + ":" + (p1Time % 60000);
        if ((p1Time / 60000) < 10) {
            time = "0" + time;
        }
        if ((p1Time % 60000) < 10) {
            time = time.substring(0,3) + "0" + time.substring(3);
        }
        while (time.length() < 5) {
            time += "0";
        }
        time = time.substring(0, 5);
        Log.d("player1Time", time);
        player1Button.setText(time);
    }

    private void p2UpdateTime() {
        String time = (p2Time / 60000) + ":" + (p2Time % 60000);
        if ((p2Time / 60000) < 10) {
            time = "0" + time;
        }
        if ((p2Time % 60000) < 10) {
            time = time.substring(0,3) + "0" + time.substring(3);
        }
        while (time.length() < 5) {
            time += "0";
        }
        time = time.substring(0, 5);
        Log.d("player1Time", time);
        player2Button.setText(time);
    }

    private void startP1Time() {
        timer = new CountDownTimer(p1Time, MILLIS) {
            @Override
            public void onTick(long millisUntilFinished) {
                p1Time -= MILLIS;
                p1UpdateTime();
            }

            @Override
            public void onFinish() {
                p1Time -= MILLIS;
                p1UpdateTime();
                player1Button.setBackgroundColor(Color.RED);
                player2Button.setBackgroundColor(Color.GREEN);
            }
        }.start();
    }

    private void startP2Time() {
        timer = new CountDownTimer(p2Time, MILLIS) {
            @Override
            public void onTick(long millisUntilFinished) {
                p2Time -= MILLIS;
                p2UpdateTime();
            }

            @Override
            public void onFinish() {
                p2Time -= MILLIS;
                p2UpdateTime();
                player2Button.setBackgroundColor(Color.RED);
                player1Button.setBackgroundColor(Color.GREEN);
            }
        }.start();
    }

    public void p1ButtonPress(View v) {
        timer.cancel();
        p1Time += increment;
        p1Turn = false;
        p1UpdateTime();
        player2Button.setEnabled(true);
        player1Button.setEnabled(false);
        startP2Time();
    }

    public void p2ButtonPress(View v) {
        timer.cancel();
        p2Time += increment;
        p1Turn = true;
        p2UpdateTime();
        player1Button.setEnabled(true);
        player2Button.setEnabled(false);
        startP1Time();
    }

    private boolean playImage = true;

    public void playPauseGame(View view) {
        ImageButton but = (ImageButton) findViewById(R.id.playPause);
        if (playImage) {
            but.setImageDrawable(getDrawable(R.drawable.pause));
            playImage = !playImage;
            if (p1Turn) {
                player2Button.setEnabled(false);
                player1Button.setEnabled(true);
                startP1Time();
            } else {
                player2Button.setEnabled(true);
                player1Button.setEnabled(false);
                startP2Time();
            }
        } else {
            timer.cancel();
            but.setImageDrawable(getDrawable(R.drawable.play));
            player2Button.setEnabled(false);
            player1Button.setEnabled(false);
            playImage = !playImage;
        }
    }
}
