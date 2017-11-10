package com.jeremyhallpdx.averagejoeshottimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trackNewRound = findViewById(R.id.button_track_new_round);

        trackNewRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trackRound(view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.stats_menu, menu);

        return true;
    }

    public void trackRound(View view) {

        Intent intent = new Intent (this, TrackRound.class);
        startActivity(intent);
    }
}