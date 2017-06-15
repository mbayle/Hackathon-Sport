
package sport.fr.wcs.hackathon3;



import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

public class BoulodromeModel {

    @Key
    private Integer nhits;
    @Key
    private ParametersBoulodrome parameters;
    @Key
    private List<RecordBoulodrome> records= new ArrayList<RecordBoulodrome>();

    public Integer getNhits() {
        return nhits;
    }

    public void setNhits(Integer nhits) {
        this.nhits = nhits;
    }

    public ParametersBoulodrome getParameters() {
        return parameters;
    }

    public void setParameters(ParametersBoulodrome parameters) {
        this.parameters = parameters;
    }

    public List<RecordBoulodrome> getRecords() {
        return records;
    }

    public void setRecords(List<RecordBoulodrome> records) {
        this.records = records;
    }

}
