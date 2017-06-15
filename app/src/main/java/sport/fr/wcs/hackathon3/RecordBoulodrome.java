
package sport.fr.wcs.hackathon3;


import com.google.api.client.util.Key;

public class RecordBoulodrome {

    @Key
    private String datasetid;
    @Key
    private String recordid;
    @Key
    private FieldsBoulodrome fields;
    @Key
    private GeometryBoulodrome geometry;
    @Key
    private String recordTimestamp;

    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public FieldsBoulodrome getFields() {
        return fields;
    }

    public void setFields(FieldsBoulodrome fields) {
        this.fields = fields;
    }

    public GeometryBoulodrome getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryBoulodrome geometry) {
        this.geometry = geometry;
    }

    public String getRecordTimestamp() {
        return recordTimestamp;
    }

    public void setRecordTimestamp(String recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }

}
