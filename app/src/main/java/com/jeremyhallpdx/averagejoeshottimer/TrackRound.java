package com.jeremyhallpdx.averagejoeshottimer;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrackRound extends AppCompatActivity {

    private TextView displayTimer;
    private Button shooterReady,clearTimer, bang;
    private List<ShotsRecord> listArrayShots;
    private ShotsAdapter adapter;
    private Handler handler;
    private boolean isStarted = false;
    private long millisecondTime, startTime, updateTime = 0L;
    private int minutes, seconds, milliseconds;

    //private MediaPlayer mp = new MediaPlayer();
    // thought about declaring this here so i can call .release() in the "stop" timer functionality...


    // Runnable to run the shot timer
    private Runnable runnable = new Runnable () {

        String timerTime;

        public void run() {

            if (!isStarted) {
                // Will be used to play a signal that the timer is starting...
                // Check with Fasching how this works
/*
                mp = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                mp.setVolume(100.0f, 100.0f);
                mp.start();
*/
                bang.setEnabled(true);
                startTime = SystemClock.uptimeMillis();
                isStarted = true;
            }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_round);

        // buttons and other widgets used to handle round data
        shooterReady = findViewById(R.id.button_shooter_ready);
        clearTimer = findViewById(R.id.button_clear_timer_data);
        bang = findViewById(R.id.button_bang);
        displayTimer = findViewById(R.id.display_shot_timer);
        ListView listShotsRecorded = findViewById(R.id.listview_shots_recorded);

        // sets these buttons to disabled until they are actually needed
        clearTimer.setEnabled(false);
        bang.setEnabled(false);

        // runs the timer on a separate thread??
        handler = new Handler();

        // list to hold the recorded to shots to pass to the adapter class
        listArrayShots = new ArrayList<>();
        adapter = new ShotsAdapter(TrackRound.this, R.layout.list_record, listArrayShots);
        listShotsRecorded.setAdapter(adapter);

        // shooterReady Button functionality
        shooterReady.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Button b = (Button) view;

                // creates a random delay for the shot timer to start
                Random r = new Random();

                int l = 1000;
                int h = 5000;
                int startDelay = r.nextInt(h - l) + 1;

                // starts the timer if it's not running
                if (b.getText().toString().equalsIgnoreCase(getResources().getString(R.string.button_shooter_ready))) {

                    b.setText(getResources().getString(R.string.button_shooter_stop));

                    if (!isStarted) {

                        handler.postDelayed(runnable, startDelay);
                    }
                }

                else {  // stops the timer if it is running

                    isStarted = false;
                    b.setText(getResources().getString(R.string.button_shooter_ready));
                    resetTimerData();
                    bang.setEnabled(false);

                    if (listArrayShots.size() >= 1) {  // these buttons are only enabled if they can be used

                        shooterReady.setEnabled(false);
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
                shooterReady.setEnabled(true);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.timer_menu, menu);

        return true;
    }

    // method to reset the timer
    private void resetTimerData() {

        millisecondTime = 0L;
        startTime = 0L;
        updateTime = 0L;
        displayTimer.setText(getResources().getString(R.string.display_shot_timer));
        handler.removeCallbacks(runnable);
    }
}
