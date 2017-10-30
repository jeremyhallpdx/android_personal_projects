package com.jeremyhallpdx.averagejoeshottimer;

/**
 * Created by Jeremy on 10/30/17.
 */

public class ShotsRecord {

    private int minutes;
    private int seconds;
    private int milliseconds;
    private long updateTime;

    public ShotsRecord(int minutes, int seconds, int milliseconds, long updateTime) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.updateTime = updateTime;
    }

    public String getTime () {

        String time = "" + this.minutes + ":"
                + String.format("%02d", this.seconds) + "."
                + String.format("%03d", this.milliseconds);

        return time;
    }

    public String getSplit(ShotsRecord prevRecord) {

        long time = this.updateTime - prevRecord.updateTime;
        int minute;
        int second;
        int milli;

        second = (int) (time / 1000);
        minute = second / 60;
        second = second % 60;
        milli = (int) (time % 1000);

        String split = "" + minute + ":"
                + String.format("%02d", second) + "."
                + String.format("%03d", milli);

        return split;
    }
}
