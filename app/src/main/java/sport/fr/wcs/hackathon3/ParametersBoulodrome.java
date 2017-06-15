
package sport.fr.wcs.hackathon3;

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

public class ParametersBoulodrome {

    @Key
    private List<String> dataset = new ArrayList<String>();
    @Key
    private String timezone;
    @Key
    private Integer rows;
    @Key
    private String format;
    @Key
    private String lang;

    public List<String> getDataset() {
        return dataset;
    }

    public void setDataset(List<String> dataset) {
        this.dataset = dataset;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
