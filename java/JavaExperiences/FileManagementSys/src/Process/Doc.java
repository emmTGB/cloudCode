package Process;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Doc {
    private String ID;
    private String creator;
    private long timestamp;
    private String description;
    private String filename;

    Doc(String ID, String creator, long timestamp, String description, String filename) {
        super();
        this.ID = ID;
        this.creator = creator;
        this.timestamp = timestamp;
        this.description = description;
        this.filename = filename;
    }

    public String getID() {
        return ID;
    }

    public String getCreator() {
        return creator;
    }

    public String getTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        date.setTime(timestamp);
        return format.format(date);
    }

    public String getDescription() {
        return description;
    }

    public String getFilename() {
        return filename;
    }

    public void setID(String id) {
        ID = id;
    }

    public void setCreator(String Creator) {
        creator = Creator;
    }

    public void setTimestamp(long Timestamp) {
        timestamp = Timestamp;
    }

    public void setDescription(String Description) {
        description = Description;
    }

    public void setFilename(String Filename) {
        filename = Filename;
    }

    @Override
    public String toString() {
        return ID + "\n" + creator + "\n" + timestamp + "\n" + description + "\n" + filename + "\n";
    }
}
