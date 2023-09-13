package Database;

public class CD extends Media {//所有类的最终父类就是根类Object，所有类都是由根类派生出来的，但是不需要声明extends Object
    //Object类包含toString() equals()等方法，可自建一个Object对象进行查看其成员
    private String artist;
    private int numOfTracks;

    public CD(String title, String artist, String comment, int numOfTracks, int playingTime) {
        super(title, comment, playingTime);
        this.artist = artist;
        this.numOfTracks = numOfTracks;
    }
    public void print(){
        System.out.print("CD:");
        super.print();
        System.out.println(":" + artist);
    }//子类和父类中出现名称和参数表完全相同的方法，则子类方法覆盖父类方法
}
