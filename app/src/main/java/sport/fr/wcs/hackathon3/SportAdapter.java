package sport.fr.wcs.hackathon3;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by apprenti on 08/06/17.
 */

public class SportAdapter extends Firebaseadapter <Competition> {

    private TextView textViewName;
    private TextView textViewDate;
    private TextView textViewTime;
    private ImageView imageViewCheck;
    private ArrayList<Sport> mSports;
    private int mLayout;
    private Context mContext;
    private ImageView imageViewJoinUs;

    public SportAdapter(Query ref, Activity activity, int layout) {
        super(ref, Competition.class, layout, activity);

    }


    @Override
    protected void populateView(View view, Competition mCompetition) {


        if (mCompetition.getmDate() != null) {

            textViewName = (TextView) view.findViewById(R.id.nameTextView);
            textViewDate = (TextView) view.findViewById(R.id.dateTextView);
            textViewTime = (TextView) view.findViewById(R.id.timeTextView);




            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            String reportDate = df.format(mCompetition.getmDate());

            String time = mCompetition.getmHour() + " : " + mCompetition.getmMinute();


            textViewName.setText(mCompetition.getmSport());
            textViewDate.setText(reportDate);
            textViewTime.setText(time);

            final boolean[] flag = {false};
            imageViewJoinUs = (ImageView) view.findViewById(R.id.imageViewJoinUs);

            imageViewJoinUs.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (!flag[0]) {
                        imageViewJoinUs.setImageResource(R.drawable.ic_shortcut_rejoindre);
                        flag[0] = true;
                    } else {
                        imageViewJoinUs.setImageResource(R.drawable.check);
                        flag[0] = false;
                    }
                }
            });

        }


    }







}
