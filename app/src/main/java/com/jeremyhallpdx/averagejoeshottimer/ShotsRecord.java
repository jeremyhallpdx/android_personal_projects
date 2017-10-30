package com.jeremyhallpdx.averagejoeshottimer;

/**
 * Created by Jeremy on 10/29/17.
 */

public class ShotsRecord {

    private String shotRecordTitle;
    private String shotRecordTime;
    private String shotRecordSplit;

    public ShotsRecord(String shotRecordTitle, String shotRecordTime, String shotRecordSplit) {
        this.shotRecordTitle = shotRecordTitle;
        this.shotRecordTime = shotRecordTime;
        this.shotRecordSplit = shotRecordSplit;
    }

    public String getShotRecordTitle() {
        return shotRecordTitle;
    }

    public void setShotRecordTitle(String shotRecordTitle) {
        this.shotRecordTitle = shotRecordTitle;
    }

    public String getShotRecordTime() {
        return shotRecordTime;
    }

    public void setShotRecordTime(String shotRecordTime) {
        this.shotRecordTime = shotRecordTime;
    }

    public String getShotRecordSplit() {
        return shotRecordSplit;
    }

    public void setShotRecordSplit(String shotRecordSplit) {
        this.shotRecordSplit = shotRecordSplit;
    }
}
