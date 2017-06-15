
package sport.fr.wcs.hackathon3;

import com.google.api.client.util.Key;

import java.util.List;

public class EspaceVertModel {


    @Key
    private List<Record> records = null;



    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

}
