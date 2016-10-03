package proj.school.chessclock;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by fazer on 10/2/16.
 */

public class ClockActivity extends AppCompatActivity {

    private static final int SECOND = 1000;

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
        int sec = intent.getIntExtra("totalSec", 0);

        p1Time = ((min * 60) + sec) * SECOND; // needs to be in milliseconds
        p2Time = ((min * 60) + sec) * SECOND; // needs to be in milliseconds

        int incMin = intent.getIntExtra("incMin", 0);
        int incSec = intent.getIntExtra("incSec", 5);

        increment = ((incMin * 60) + incSec) * SECOND;  // needs to be in milliseconds

        player1Button = (Button) (findViewById(R.id.player1Button));
        player2Button = (Button) (findViewById(R.id.player2Button));
    }

    private void p1UpdateTime(){
        String time = (p1Time / 60000) + ":" + (p1Time % 60000);
        player1Button.setText(time);
    }

    private void p2UpdateTime(){
        String time = (p2Time / 60000) + ":" + (p2Time % 60000);
        player2Button.setText(time);
    }

    public void p1ButtonPress(View v) {
        timer.cancel();
        p1Time += increment;
        p1UpdateTime();
        player2Button.setEnabled(true);
        player1Button.setEnabled(false);
        timer = new CountDownTimer(p2Time, SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                p2Time -= SECOND;
                p2UpdateTime();
            }

            @Override
            public void onFinish() {
                player2Button.setBackgroundColor(Color.RED);
                player1Button.setBackgroundColor(Color.GREEN);
            }
        }.start();
    }

    public void p2ButtonPress(View v) {
        timer.cancel();
        p2Time += increment;
        p2UpdateTime();
        player1Button.setEnabled(true);
        player2Button.setEnabled(false);
        timer = new CountDownTimer(p1Time, SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                p1Time -= SECOND;
                p1UpdateTime();
            }

            @Override
            public void onFinish() {
                player1Button.setBackgroundColor(Color.RED);
                player2Button.setBackgroundColor(Color.GREEN);
            }
        }.start();
    }

    public void startGame(View view) {
        player2Button.setEnabled(false);
        timer = new CountDownTimer(p1Time, SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                p1Time -= SECOND;
                p1UpdateTime();
            }

            @Override
            public void onFinish() {
                player1Button.setBackgroundColor(Color.RED);
                player2Button.setBackgroundColor(Color.GREEN);
            }
        }.start();
    }
}
