public class Father {
    public Father(){
        System.out.println("无参父");
    }
    public Father(String arg){
        System.out.println("有参父");
    }

    public static void main(String args[]){
        Son s = new Son();
    }
}

class Son extends Father{
    public Son(){
        this("ddd");
        System.out.println("无参子");
    }
    public Son(String arg){
        super("ddd");
        System.out.println("有参子");
    }
}
