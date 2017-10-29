package com.jeremyhallpdx.averagejoeshottimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TrackRound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView displayTimer;
        Button shooterReady;
        ListView shotsRecorded;

        long millisecondTime, startTime, timeBuff, updateTime = 0L;
        int minutes, seconds, milliseconds;

        ArrayAdapter<String> adapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_round);

        shooterReady = findViewById(R.id.button_shooter_ready);
        displayTimer = findViewById(R.id.display_shot_timer);
        shotsRecorded = findViewById(R.id.listview_shots_recorded);

        shooterReady.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Make a "beep" to start the stopwatch and access the microphone to record shots.
                // Also must display the "shots" in the listView
            }
        });
    }
}
