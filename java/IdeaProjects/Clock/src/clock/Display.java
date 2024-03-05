package clock;

public class Display {
    private int value;
    private final int limit;//final一经赋值不可改变
    public static int step = 1;//static为所有同类对象所公用，可直接由类名进行访问

    public Display(int limit){
        value = 0;
        this.limit = limit;
    }

    public void increase(){
        value += step;
        if(value == limit){
            value = 0;
        }
    }

    public int getValue(){
        return value;
    }

    public static void isStatic(){//static方法为所有同类对象公用，其操作不涉及本类中非static成员，可以直接用类名进行调用
        System.out.println("this is a static method!");
    }

    public static void main(String[] args){
        Display d = new Display(24);
        isStatic();//static成员之间可以互相调用，而static成员不能调用非static成员
        while(true){
            d.increase();
            System.out.println(d.getValue());
        }
    }
}
