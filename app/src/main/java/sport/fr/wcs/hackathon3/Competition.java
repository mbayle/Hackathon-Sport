package sport.fr.wcs.hackathon3;

import java.util.Date;

/**
 * Created by apprenti on 09/06/17.
 */

public class Competition {

    private Date mDate;
    private int mHour;
    private int mMinute;
    private String mIntitule;
    private String mSport;

    private Competition () {
    }

    public Competition (Date date, int hour, int minute, String intitule, String sport){

        this.mDate = date;
        this.mHour = hour;
        this.mMinute = minute;
        this.mIntitule = intitule;
        this.mSport = sport;

    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public int getmHour() {
        return mHour;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public int getmMinute() {
        return mMinute;
    }

    public void setmMinute(int mMinute) {
        this.mMinute = mMinute;
    }

    public String getmIntitule() {
        return mIntitule;
    }

    public void setmIntitule(String mIntitule) {
        this.mIntitule = mIntitule;
    }

    public String getmSport() {
        return mSport;
    }

    public void setmSport(String mSport) {
        this.mSport = mSport;
    }

}
