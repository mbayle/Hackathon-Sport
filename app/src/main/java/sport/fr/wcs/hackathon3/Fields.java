
package sport.fr.wcs.hackathon3;

import com.google.api.client.util.Key;

import java.util.List;

public class Fields {

    @Key
    private String categorie;
    @Key
    private String adresse;
    @Key
    private List<Double> geo_point_2d = null;
    @Key
    private Integer numero;
    @Key
    private String intitule;


    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Double> getGeoPoint2d() {
        return geo_point_2d;
    }

    public void setGeoPoint2d(List<Double> geoPoint2d) {
        this.geo_point_2d = geoPoint2d;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }


}
