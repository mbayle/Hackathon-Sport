package sport.fr.wcs.hackathon3;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.gson.GsonFactory;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

/**
 * Created by apprenti on 08/06/17.
 */

public class EspacesVertsRequest extends GoogleHttpClientSpiceRequest<EspaceVertModel> {

    private static final String TAG = "EspacesVertsRequest" ;
    private String baseUrl;

    public EspacesVertsRequest() {
        super( EspaceVertModel.class );
        this.baseUrl = String.format("https://data.toulouse-metropole.fr/api/records/1.0/search/?dataset=espaces-verts&rows=200");
    }

    @Override
    public EspaceVertModel loadDataFromNetwork() throws Exception {


        GenericUrl url = new GenericUrl(this.baseUrl);

        EspaceVertModel espacesVerts = getHttpRequestFactory()
                .buildGetRequest(url).setParser(new GsonFactory().createJsonObjectParser())
                .execute()
                .parseAs(getResultType());
        return espacesVerts;
    }
}
