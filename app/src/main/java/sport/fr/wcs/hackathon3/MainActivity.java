package sport.fr.wcs.hackathon3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editTextName;
    User mUser;
    private String userName;
    private Button button;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextName = (EditText) findViewById(R.id.editTextName);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference("User");


    }
    


    public void maps(View view) {


        button = (Button) findViewById(R.id.button);

        Intent intentMaps = new Intent(this, MapsActivity.class);
        startActivity(intentMaps);

        userName = editTextName.getText().toString().trim();
        mUser = new User(userName);
        mDatabase.push().setValue(mUser);

    }

    public void onClick(View v){
        if(button == v){

            if (editTextName.length() == 0){
                Toast.makeText(this, R.string.ajout_pseudo, Toast.LENGTH_SHORT).show();

            } else {
                update();
                Intent intentToActivity = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intentToActivity);
            }
        }
    }

    private void update() {
        userName = editTextName.getText().toString().trim();
        mUser = new User(userName);
        mDatabase.push().setValue(mUser);
    }
}