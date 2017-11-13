package com.jeremyhallpdx.averagejoeshottimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DisplayWeaponStats extends MainActivity {

    private static final String TAG = "DisplayWeaponStats";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weapon_stats);

        // starts the TrackRound activity
        Button button = findViewById(R.id.button_track_new_round);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trackRound(view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.stats_menu, menu);

        // gets the extraString from the intent to determine which stat set to check
        Intent intent = getIntent();
        String checkWeapon = intent.getStringExtra(MainActivity.WEAPON_TYPE);

        switch (checkWeapon) {

            case "ccw":
                menu.findItem(R.id.mnuCcw).setChecked(true);
                break;

            case "sidearm":
                menu.findItem(R.id.mnuSidearm).setChecked(true);
                break;

            case "carbine":
                menu.findItem(R.id.mnuCarbine).setChecked(true);
                break;

            case "marksman":
                menu.findItem(R.id.mnuMarksman).setChecked(true);
                break;

            default:
                // do nothing...
        }

        return true;
    }
}
