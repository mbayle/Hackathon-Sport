package sport.fr.wcs.hackathon3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.octo.android.robospice.GsonGoogleHttpClientSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;


import java.util.ArrayList;
import java.util.List;

import static sport.fr.wcs.hackathon3.R.attr.icon;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LatLng mChoosenPositon;
    protected SpiceManager spiceManager = new SpiceManager(GsonGoogleHttpClientSpiceService.class);
    private BoulodromeRequest boulodromeRequest;
    private EspacesVertsRequest espacesVertsRequest;
    private Marker myMarker;
    private String intitule;
    private String title;
    private ArrayList<Sport> arraySport;
    private SportAdapter mAdapter;
    private Button mButtonCreate;
    private View mView;
    private Button mButtonAddSport;
    private DatabaseReference mDatabaseGameRef;
    private SportAdapter mGameListAdapter;
    private String intituleEspacesVerts;
    private ImageView imageViewCheck;
    private ImageView imageViewJoinUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        spiceManager.start(this);

        mButtonCreate = (Button) findViewById(R.id.button);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(14.0f);
        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(43.6, 1.4333), 11));


        boulodromeRequest();
        espaceVertRequest();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    public void boulodromeFilter(View view){
        mMap.clear();
        boulodromeRequest();
    }

    public void espacesVertFilter(View view){
        mMap.clear();
        espaceVertRequest();
    }

    public void allMarkersFilter(View view){
        boulodromeRequest();
        espaceVertRequest();
    }




    private void boulodromeRequest() {
        MapsActivity.this.setProgressBarIndeterminateVisibility(true);

        boulodromeRequest = new BoulodromeRequest();
        spiceManager.execute(boulodromeRequest, new BoulodromeRequestListener());


    }

    private void espaceVertRequest(){
        MapsActivity.this.setProgressBarIndeterminateVisibility(true);

        espacesVertsRequest = new EspacesVertsRequest();

        spiceManager.execute(espacesVertsRequest, new EspacesVertsRequestListener());
    }

    private class BoulodromeRequestListener implements RequestListener<BoulodromeModel> {

        @Override
        public void onRequestFailure(SpiceException e) {
            //update your UI

        }

        @Override
        public void onRequestSuccess(BoulodromeModel boulodromeModel) {

            if (boulodromeModel != null) {
                addMarkersBoulodrome(boulodromeModel);
            }

        }



    }

    private class EspacesVertsRequestListener implements RequestListener<EspaceVertModel> {

        @Override
        public void onRequestFailure(SpiceException e) {
            //update your UI

        }

        @Override
        public void onRequestSuccess(EspaceVertModel espacesVertsModel) {

            if (espacesVertsModel != null) {
                addMarkersEspaceVerts(espacesVertsModel);
            }


        }



    }

    private void addMarkersBoulodrome(BoulodromeModel boulodromeModel) {

        List<RecordBoulodrome> records = boulodromeModel.getRecords();
        for (int i = 0; i < records.size() ; i ++) {


            List<Double> listBoulodrome = records.get(i).getGeometry().getCoordinates();
            intitule = records.get(i).getFields().getIndex();



            double ln = listBoulodrome.get(0);
            double la = listBoulodrome.get(1);

            myMarker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(la, ln)).title(intitule).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.setOnMarkerClickListener(this);

                /*PicassoMarker pmarker = new Marker();
                Picasso.with(MapsActivity.this).setLoggingEnabled(true).fit().load(venueList.get(i).getMap_icon()).into(pmarker);
                hashMap.put(marker, i);*/

        }

    }

    private void addMarkersEspaceVerts(EspaceVertModel espacesVertsModel) {

        List<Record> records = espacesVertsModel.getRecords();
        for (int i = 0; i < records.size() ; i ++) {


            List<Double> listEspacesVerts = records.get(i).getFields().getGeoPoint2d();
            intituleEspacesVerts = records.get(i).getFields().getIntitule();

            if (listEspacesVerts != null ){
            double ln = listEspacesVerts.get(0);
            double la = listEspacesVerts.get(1);

            myMarker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(ln, la)).title(intituleEspacesVerts));


                mMap.setOnMarkerClickListener(this);

            }

                /*PicassoMarker pmarker = new Marker();
                Picasso.with(MapsActivity.this).setLoggingEnabled(true).fit().load(venueList.get(i).getMap_icon()).into(pmarker);
                hashMap.put(marker, i);*/

        }

    }
    @Override
    public boolean onMarkerClick(final Marker marker) {

        title = marker.getTitle();
        //handle click here

        AlertDialog.Builder mBuiler = new AlertDialog.Builder(MapsActivity.this);
        mView = getLayoutInflater().inflate(R.layout.dialog_sport, null);
        ListView mSportList = (ListView) mView.findViewById(R.id.sport_list);
        mBuiler.setTitle(title);
        mButtonAddSport = (Button) mView.findViewById(R.id.buttonAddSport);

        mButtonAddSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createSportIntent = new Intent(mView.getContext(), CreateSport.class);
                startActivity(createSportIntent);
            }
        }

        );


        mDatabaseGameRef = FirebaseDatabase.getInstance().getReference("Competition").child(title);

        mGameListAdapter = new SportAdapter(mDatabaseGameRef,MapsActivity.this, R.layout.sport_item); // APPELLE L'ADAPTER

        mSportList.setAdapter(mGameListAdapter); //FUSION LIST ET ADAPTER

        mGameListAdapter.notifyDataSetChanged();




        mSportList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(MapsActivity.this, "Merci de vous être inscrit", Toast.LENGTH_SHORT).show();



            }
        });



        mBuiler.setView(mView);

        mBuiler.show();

        mButtonAddSport = (Button) mView.findViewById(R.id.buttonAddSport);
        mButtonAddSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createSportIntent = new Intent(mView.getContext(), CreateSport.class);

                createSportIntent.putExtra("intitule", title);

                startActivity(createSportIntent);
            }
        });

        return false;
    }
}


