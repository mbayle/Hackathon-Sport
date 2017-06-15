
package sport.fr.wcs.hackathon3;

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

public class FieldsBoulodrome {

    @Key
    private GeoShapeBoulodrome geoShape;
    @Key
    private String couvert;
    @Key
    private List<Double> geoPoint2d = new ArrayList<Double>();
    @Key
    private String index;

    public GeoShapeBoulodrome getGeoShape() {
        return geoShape;
    }

    public void setGeoShape(GeoShapeBoulodrome geoShape) {
        this.geoShape = geoShape;
    }

    public String getCouvert() {
        return couvert;
    }

    public void setCouvert(String couvert) {
        this.couvert = couvert;
    }

    public List<Double> getGeoPoint2d() {
        return geoPoint2d;
    }

    public void setGeoPoint2d(List<Double> geoPoint2d) {
        this.geoPoint2d = geoPoint2d;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
