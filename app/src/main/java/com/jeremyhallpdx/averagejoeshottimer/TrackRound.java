package com.jeremyhallpdx.averagejoeshottimer;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrackRound extends AppCompatActivity {

    private TextView displayTimer;
    private Button shooterReady,clearTimer, bang;
    private List<ShotsRecord> listArrayShots;
    private ShotsAdapter adapter;
    private Handler handler;

    private boolean isStarted = false;

    private long millisecondTime, startTime, updateTime = 0L;
    private int minutes, seconds, milliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_round);

        shooterReady = findViewById(R.id.button_shooter_ready);
        clearTimer = findViewById(R.id.button_clear_timer_data);
        bang = findViewById(R.id.button_bang);
        displayTimer = findViewById(R.id.display_shot_timer);
        ListView listShotsRecorded = findViewById(R.id.listview_shots_recorded);

        bang.setEnabled(false);
        clearTimer.setEnabled(false);

        handler = new Handler();

        listArrayShots = new ArrayList<>();
        adapter = new ShotsAdapter(TrackRound.this, R.layout.list_record, listArrayShots);
        listShotsRecorded.setAdapter(adapter);

        // shooterReady Button functionality
        shooterReady.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Button b = (Button) view;

                if (b.getText().toString() == getResources().getString(R.string.button_shooter_ready)) {

                    b.setText(getResources().getString(R.string.button_shooter_stop));
                    bang.setEnabled(true);

                    if (!isStarted) {

                        startTime = SystemClock.uptimeMillis();
                        handler.postDelayed(runnable, 0);
                    }

                    else {

                        startTime = SystemClock.uptimeMillis();
                        handler.postDelayed(runnable, 0);
                    }
                }

                else {

                    isStarted = false;
                    b.setText(getResources().getString(R.string.button_shooter_ready));
                    resetTimerData();
                    bang.setEnabled(false);

                    if (listArrayShots.size() >= 1) {

                        clearTimer.setEnabled(true);
                    }
                }
            }
        });

        // resets timer and shot record data.
        // will be implemented to a save feature that starts another activity
        // and allows input of hits/crits and other round data.
        clearTimer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                resetTimerData();
                listArrayShots.clear();
                adapter.notifyDataSetChanged();
                clearTimer.setEnabled(false);
            }
        });

        // bangButton simulates a recorded gunshot
        bang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ShotsRecord shotRecord = new ShotsRecord(minutes, seconds, milliseconds, updateTime);

                listArrayShots.add(shotRecord);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private Runnable runnable = new Runnable () {

        String timerTime;

        public void run() {

/*
            // plays the beep and sets the running flag to true
            if (!isStarted) {

                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                mp.start();
                startTime = SystemClock.uptimeMillis();
                isStarted = true;
            }
*/

            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = millisecondTime;
            seconds = (int) (updateTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            milliseconds = (int) (updateTime % 1000);
            timerTime = "" + minutes + ":"
                    + String.format("%02d", seconds) + ":"
                    + String.format("%03d", milliseconds);

            displayTimer.setText(timerTime);
            handler.postDelayed(this, 0);
        }
    };

    private void resetTimerData() {

        millisecondTime = 0L;
        startTime = 0L;
        updateTime = 0L;
        displayTimer.setText(getResources().getString(R.string.display_shot_timer));
        handler.removeCallbacks(runnable);
    }
}
