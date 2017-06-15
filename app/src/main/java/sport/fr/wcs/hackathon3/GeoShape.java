
package sport.fr.wcs.hackathon3;

import com.google.api.client.util.Key;

import java.util.List;

public class GeoShape {

    @Key
    private String type;
    @Key
    private List<List<List<Double>>> coordinates = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<List<Double>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<Double>>> coordinates) {
        this.coordinates = coordinates;
    }

}
