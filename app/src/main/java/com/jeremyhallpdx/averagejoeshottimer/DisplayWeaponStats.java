package com.jeremyhallpdx.averagejoeshottimer;

import android.os.Bundle;
import android.view.Menu;

public class DisplayWeaponStats extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weapon_stats);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.stats_menu, menu);

        String checkWeapon = getIntent().getExtras().get(WEAPON_TYPE).toString();

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
