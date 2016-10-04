package proj.school.chessclock;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private int totalMin = 5;
    private int totalSec = 0;
    private int incrementMin = 0;
    private int incrementSec = 05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void updateAll() {
        //update minutes for total time
        EditText totalMins = (EditText) findViewById(R.id.totalMins);
        if (totalMin < 10) {
            totalMins.setText("0" + Integer.toString(totalMin));
        } else {
            totalMins.setText(Integer.toString(totalMin));
        }

        // update the seconds for total time
        EditText totalsecs = (EditText) findViewById(R.id.totalSecs);
        if (totalSec < 10) {
            totalsecs.setText("0" + Integer.toString(totalSec));
        } else {
            totalsecs.setText(Integer.toString(totalSec));
        }


        // update the minutes for increment time
        EditText incremMins = (EditText) findViewById(R.id.incMins);
        if (incrementMin < 10) {
            incremMins.setText("0" + Integer.toString(incrementMin));
        } else {
            incremMins.setText(Integer.toString(incrementMin));
        }

        // update the seconds for increment time
        EditText incremSecs = (EditText) findViewById(R.id.incSecs);
        if (incrementSec < 10) {
            incremSecs.setText("0" + Integer.toString(incrementSec));
        } else {
            incremSecs.setText(Integer.toString(incrementSec));
        }

    }

    public void totalMinIncrease(View view) {
        ++totalMin;
        updateAll();
    }

    public void totalMinDecrease(View view) {
        if (totalMin > 0) {
            --totalMin;
        } else {
            totalMin = 0;
            // ======== play error message.
        }
        updateAll();
    }

    public void totalSecIncrease(View view) {
        if (totalSec < 59) {
            ++totalSec;
        } else {
            totalSec = 0;
            ++totalMin;
        }
        updateAll();
    }

    public void totalSecDecrease(View view) {
        if (totalSec > 0) {
            --totalSec;
        } else {
            totalSec = 0;
            // ======== play error message.
        }
        updateAll();
    }


    public void incMinIncrease(View view) {
        ++incrementMin;
        updateAll();
    }

    public void incMinDecrease(View view) {
        if (incrementMin > 0) {
            --incrementMin;
        } else {
            incrementMin = 0;
            // ======== play error sound.
        }
        updateAll();
    }

    public void incSecIncrease(View view) {
        if (incrementSec < 59) {
            ++incrementSec;
        } else {
            incrementSec = 0;
            ++incrementMin;
        }
        updateAll();
    }

    public void incSecDecrease(View view) {
        if (incrementSec > 0) {
            --incrementSec;
        } else {
            incrementSec = 0;
            // ======== play error sound.
        }
        updateAll();
    }

    public void startClock(View view) {
        Intent startNew = new Intent(MainActivity.this, ClockActivity.class);
        startNew.putExtra("totalMin", totalMin);
        startNew.putExtra("totalSec", totalSec);
        startNew.putExtra("incMin", incrementMin);
        startNew.putExtra("incSec", incrementSec);
        startActivity(startNew);
    }


}
