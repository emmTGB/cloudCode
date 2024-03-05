package Database;

public class Media {
    private final String title;
    private final String comment;
    private final int playingTime;
    private final boolean gotIt = false;

    public Media(String title, String comment, int playingTime) {
        this.title = title;
        this.comment = comment;
        this.playingTime = playingTime;
    }
    public void print(){
        System.out.print(title);
    }
}