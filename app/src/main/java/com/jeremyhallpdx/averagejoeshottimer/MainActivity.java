package com.jeremyhallpdx.averagejoeshottimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String WEAPON_TYPE = "weaponType";

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;

        switch (id) {

            case R.id.mnuCombined:
                item.setChecked(true);
                intent = new Intent(this, MainActivity.class);
                break;

            case R.id.mnuCcw:
                item.setChecked(true);
                intent = new Intent (this, DisplayWeaponStats.class);
                intent.putExtra(WEAPON_TYPE, "ccw");
                break;

            case R.id.mnuSidearm:
                item.setChecked(true);
                intent = new Intent (this, DisplayWeaponStats.class);
                intent.putExtra(WEAPON_TYPE, "sidearm");
                break;

            case R.id.mnuCarbine:
                item.setChecked(true);
                intent = new Intent (this, DisplayWeaponStats.class);
                intent.putExtra(WEAPON_TYPE, "carbine");
                break;

            case R.id.mnuMarksman:
                item.setChecked(true);
                intent = new Intent (this, DisplayWeaponStats.class);
                intent.putExtra(WEAPON_TYPE, "marksman");
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        startActivity(intent);
        return true;
    }

    public void trackRound(View view) {

        Intent intent = new Intent (this, TrackRound.class);
        startActivity(intent);
    }
}