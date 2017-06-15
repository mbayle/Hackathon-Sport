package sport.fr.wcs.hackathon3;

/**
 * Created by apprenti on 08/06/17.
 */

public class Sport {

    private String mName;
    private String mDate;
    private String mTime;

    public Sport(String name, String date, String time) {
        mName = name;
        mDate = date;
        mTime = time;
    }

    private Sport(){

    }

    public String getName() {
        return mName;
    }


    public String getDate() {
        return mDate;
    }

    public String getTime(){
        return mTime;
    }
}
