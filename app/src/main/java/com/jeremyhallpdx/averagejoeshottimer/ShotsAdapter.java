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
 * Created by Jeremy on 10/29/17.
 */

public class ShotsAdapter extends ArrayAdapter {

    private static final String TAG = "ShotsAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<String> shots;

    public ShotsAdapter(@NonNull Context context, int resource, List<String> shots) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.shots = shots;
    }

    @Override
    public int getCount() {
        return shots.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = layoutInflater.inflate(layoutResource, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        String shotText = "Shot " + (position + 1);
        String shotTime = shots.get(position);
        String shotSplit = "";

        viewHolder.recordShot.setText(shotText);
        viewHolder.recordShotTime.setText(shotTime);

        if (position > 0) {

            shotSplit = shots.get(position - 1);
        }

        viewHolder.recordSplit.setText(shotSplit);

        return convertView;
    }

    private class ViewHolder {

        final TextView recordShot;
        final TextView recordShotTime;
        final TextView recordSplit;

        ViewHolder (View v) {

            this.recordShot = (TextView) v.findViewById(R.id.list_record_shot);
            this.recordShotTime = (TextView) v.findViewById(R.id.list_record_shot_time);
            this.recordSplit = (TextView) v.findViewById(R.id.list_record_split);
        }
    }
}