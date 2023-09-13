package Database;

import java.util.ArrayList;

public class Database {
    private final ArrayList<Media> listCD = new ArrayList<Media>();
    public void add(Media it){
        listCD.add(it);
    }
    public void list(){
        for(Media it : listCD){
            it.print();//函数调用的绑定，此处为动态绑定
            //静态绑定：根据变量声明类型进行绑定
            //动态绑定：根据变量动态类型进行绑定
            //在成员函数中调用其他成员函数也是通过this对象变量进行调用的
        }
    }
    public static void main(String[] args){
        Database db = new Database();
        db.add(new CD("abc", "csn", "no future", 0, 100));
        db.add(new DVD("abd", "ssn", "no future", 100));
        //将子类赋值给父类是可以正常过编译的，称为向上造型，这一过程是安全的，也是类的多态的体现
        db.list();
        Media m = new CD("abc", "csn", "no future", 0, 100);
        CD c = new CD("abc", "csn", "no future", 0, 100);
        c = (CD)m;//强制将父类对象转化为子类对象，将其称为造型，其与强制类型转换不同的是，其并没有改变父类对象的类型
        //该操作并不安全，若父类对象指向一个与目标子类不同类的对象，其运行会出错
    }
}
