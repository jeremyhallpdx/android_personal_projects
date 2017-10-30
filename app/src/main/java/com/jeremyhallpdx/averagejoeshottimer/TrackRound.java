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
    private Button shooterReady, bang;
    private ListView listviewShotsRecorded;
    private List<String> listArrayShots;
    private ShotsAdapter adapter;
    private Handler handler;

    private long millisecondTime, startTime, timeBuff, updateTime, shotCount = 0L;
    private long shotRecordTime, shotRecordSplit = 0L;
    private String shotReocordTitle;
    private int minutes, seconds, milliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_round);

        shooterReady = findViewById(R.id.button_shooter_ready);
        bang = findViewById(R.id.button_bang);
        displayTimer = findViewById(R.id.display_shot_timer);
        listviewShotsRecorded = findViewById(R.id.listview_shots_recorded);

        handler = new Handler();

        listArrayShots = new ArrayList<>();
        adapter = new ShotsAdapter(TrackRound.this, R.layout.list_record, listArrayShots);
        listviewShotsRecorded.setAdapter(adapter);

        shooterReady.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Make a "beep" to signal the timer start and access the microphone to record shots.
                // Also must display the "shots" in the listView
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
            }
        });

        bang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                listArrayShots.add(displayTimer.getText().toString());
                System.out.println(String.format(displayTimer.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });
    }

    public Runnable runnable = new Runnable () {

        String timerTime;

        public void run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
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
}
