package sport.fr.wcs.hackathon3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateSport extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private ImageButton createDate;
    private ImageButton createHour;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Calendar dateCalendar;
    private Calendar timeCalendar;
    private String date_time;
    private Date date_time_object;
    private TextView dateView;
    private TextView hour;
    private Spinner spinner;
    private List<String> sportList;
    private String sport;
    private DateFormat formatDateTime;
    private Button buttonUpdate;
    private String intitule;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sport);

        mDatabase = FirebaseDatabase.getInstance().getReference("Competition");

        createDate = (ImageButton) findViewById(R.id.imageButtonChooseDate);
        createDate.setOnClickListener(this);
        createHour = (ImageButton) findViewById(R.id.imageButtonHour);
        createHour.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        buttonUpdate =  (Button) findViewById(R.id.buttonUpdateCompetition);
        buttonUpdate.setOnClickListener(this);
        dateView = (TextView) findViewById(R.id.textViewDate);
        hour = (TextView) findViewById(R.id.textViewHour);

        formatDateTime = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);

        sportList = new ArrayList<String>();
        sportList.add("Pétanque");
        sportList.add("SlackLine");
        sportList.add("Molki");

        addItemSelector();
        updateTextLabelDate();


    }

    public void addItemSelector() {
        ArrayAdapter<String> dataAdapterAway = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sportList);
        dataAdapterAway.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapterAway);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        sport = parent.getItemAtPosition(position).toString();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void updateDate(){
        // Get Current Date
        dateCalendar = Calendar.getInstance();
        mYear = dateCalendar.get(Calendar.YEAR);
        mMonth = dateCalendar.get(Calendar.MONTH);
        mDay = dateCalendar.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int date = view.getDayOfMonth();
                        int month = view.getMonth()+1;
                        int years = view.getYear();

                        date_time = String.valueOf(date) + "/" + String.valueOf(month) + "/" + String.valueOf(years);
                        date_time_object = new Date(years, view.getMonth(), date);
                        DateFormat df = new SimpleDateFormat("dd/MM/yy");
                        String reportDate = df.format(date_time_object);

                        dateView.setText(reportDate);
                        //*************Call Time Picker Here ********************

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void updateTime(){
        // Get Current Time
        timeCalendar = Calendar.getInstance();
        mHour = timeCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = timeCalendar.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        hour.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void updateTextLabelDate(){
    }

    private void updateCompetition(){
        intitule = getIntent().getStringExtra("intitule");

        Competition competition = new Competition(date_time_object,mHour,mMinute, intitule, sport);
        mDatabase.child(intitule).push().setValue(competition);

    }


    public void onClick(View view){

        if (view == createDate){
            updateDate();
        }
        if (view == createHour){
            updateTime();
        }
        if (view == buttonUpdate){
            updateCompetition();
            Intent intentMaps = new Intent(this, MapsActivity.class);
            startActivity(intentMaps);
        }
    }
}
