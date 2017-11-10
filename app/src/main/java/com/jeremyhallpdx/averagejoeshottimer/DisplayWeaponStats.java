package com.jeremyhallpdx.averagejoeshottimer;

import android.os.Bundle;
import android.view.Menu;

public class DisplayWeaponStats extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_stats);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.stats_menu, menu);

        return true;
    }
}
