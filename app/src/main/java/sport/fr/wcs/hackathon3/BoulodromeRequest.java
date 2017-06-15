package sport.fr.wcs.hackathon3;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.gson.GsonFactory;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;



public class BoulodromeRequest  extends GoogleHttpClientSpiceRequest<BoulodromeModel> {

    private static final String TAG = "BoulodromeRequest" ;
    private String baseUrl;


    public BoulodromeRequest() {
        super( BoulodromeModel.class );
        this.baseUrl = "https://data.toulouse-metropole.fr/api/records/1.0/search/?dataset=boulodromes&rows=1000";
    }

    @Override
    public BoulodromeModel loadDataFromNetwork() throws Exception {
        Log.d(TAG, "CurrentWeatherModel loaded");

        GenericUrl url = new GenericUrl(this.baseUrl);

        BoulodromeModel boulodrome = getHttpRequestFactory()
                .buildGetRequest(url).setParser(new GsonFactory().createJsonObjectParser())
                .execute()
                .parseAs(getResultType());
        return boulodrome;
    }
}
