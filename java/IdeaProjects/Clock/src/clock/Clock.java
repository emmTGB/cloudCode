package clock;

//处于其他包的非public单位可以用import 包名.类名 的方式引入类使其在此编译单元可用，可将包视作文件夹使用

public class Clock {//public修饰的类、方法、变量可以跨包访问，每个编译单元只能有一个与编译单元文件同名的public类
    private final Display hour;
    private final Display minute;

    public Clock(){
        hour = new Display(24);
        minute = new Display(60);
    }

    public void tik(){
        minute.increase();
        if(minute.getValue() == 0){
            hour.increase();
        }
        System.out.printf("%02d:%02d\n", hour.getValue(), minute.getValue());//printf()带格式输出
    }

    public static void main(String[] args){
        Clock c = new Clock();
        while(true){
            c.tik();
        }
    }
}
