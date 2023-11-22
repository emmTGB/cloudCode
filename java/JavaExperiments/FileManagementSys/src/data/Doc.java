package data;

import java.sql.Timestamp;

public class Doc {
    private final String ID;
    private final String creator;
    private final Timestamp timestamp;
    private final String description;
    private final String fileName;

    public Doc(String ID, String creator, Timestamp timestamp, String description, String fileName) {
        super();
        this.ID = ID;
        this.creator = creator;
        this.timestamp = timestamp;
        this.description = description;
        this.fileName = fileName;
    }

    public String getID() {
        return ID;
    }

    public String getCreator() {
        return creator;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName;
    }
}
