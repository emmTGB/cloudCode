package Database;

public class DVD extends Media {
    private final String director;

    public DVD(String title, String director, String comment, int playingTime) {
        super(title, comment, playingTime);
        this.director = director;
    }
    @Override//表示重写，可当作注释，同时也可借此让编译器检查重写正确性
    public void print(){
        System.out.print("DVD:");
        super.print();
        System.out.print(":" + director);
    }
}
