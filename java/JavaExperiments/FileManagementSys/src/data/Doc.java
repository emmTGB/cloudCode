package data;

import java.sql.Timestamp;

public record Doc(String ID, String creator, Timestamp timestamp, String description, String fileName) {
}
