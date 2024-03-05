import java.util.HashMap;

public class Room {
    public static String[] directions = {"East", "South", "West", "North"};
    private final String roomName;
    private final HashMap<String, Room> exit;

    public Room(String roomName) {
        this.roomName = roomName;
        exit = new HashMap<>();
    }
    public void setExit(String dir, Room rm){
        exit.put(dir, rm);
    }
    public String showExits(){
        StringBuilder sb = new StringBuilder();
        for(String dir : exit.keySet()){
            sb.append(dir);
            sb.append(" ");
        }
        return sb.toString();
    }
    public Room getExit(String dir){
        return exit.get(dir);
    }

    @Override
    public String toString(){
        return roomName;
    }
}
