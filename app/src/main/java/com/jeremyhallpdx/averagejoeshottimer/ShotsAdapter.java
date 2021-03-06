package com.jeremyhallpdx.averagejoeshottimer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jeremy on 10/30/17.
 *
 * Custom adapter to fill the listView widget in the TrackRound activity with shot records
 * includes logic to get split times for each shot after the first.
 */

public class ShotsAdapter extends ArrayAdapter {

    private static final String TAG = "ShotsAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<ShotsRecord> shots;

    public ShotsAdapter(@NonNull Context context, int resource, List<ShotsRecord> shots) {

        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.shots = shots;
    }

    @Override
    public int getCount() {

        return shots.size();  // returns the number of shots in the List<> for use in calculating views for the ListView
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // ViewHolder Pattern
        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        // places recorded shots into the listView
        ShotsRecord currentShot = shots.get(position);
        // uses the prevshot if it exists to get the split time
        ShotsRecord prevShot = null;

        // String vars for setting widget text
        String shotText = "Shot " + (position + 1);
        String shotTime = currentShot.getTime();
        String shotSplit = "0:00.000";

        viewHolder.recordShot.setText(shotText);  // set the shot number
        viewHolder.recordShotTime.setText(shotTime);  // set the shot time

        if (position > 0) {  // if there are previous shots to calculate split times...

            prevShot = shots.get(position - 1);
            shotSplit = currentShot.getSplit(prevShot);
        }

        viewHolder.recordSplit.setText(shotSplit);  // set the split times

        return convertView;
    }

    // inner ViewHolder class for an efficient ListView
    private class ViewHolder {

        final TextView recordShot;
        final TextView recordShotTime;
        final TextView recordSplit;

        ViewHolder (View v) {

            this.recordShot = v.findViewById(R.id.list_record_shot);
            this.recordShotTime = v.findViewById(R.id.list_record_shot_time);
            this.recordSplit = v.findViewById(R.id.list_record_split_time);
        }
    }
}
