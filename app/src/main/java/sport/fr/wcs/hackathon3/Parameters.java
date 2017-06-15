
package sport.fr.wcs.hackathon3;

import com.google.api.client.util.Key;

import java.util.List;

public class Parameters {

    @Key
    private List<String> dataset = null;
    @Key
    private String timezone;
    @Key
    private Integer rows;
    @Key
    private String format;

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

}
