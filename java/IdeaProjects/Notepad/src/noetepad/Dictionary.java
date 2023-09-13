package noetepad;

import java.sql.Struct;
import java.util.HashSet;

public class Dictionary {
    private final HashSet<String> dict = new HashSet<>();//哈希集合，内部完全无序
    public void addDict(String s){
        dict.add(s);
    }
}
class Value1{
    private int i;
    public void setI(int i){this.i = i;}
    public int getI(){return i;}
}
class Value2{
    private int i;
    public void setI(int i){this.i = i;}
    public int getI(){return i;}
    public String toString(){return String.valueOf(i);}//类似一种预留函数，有了这个函数可以将该类对象通过System.out.print等函数正常输出
}
class Main{
    public static void main(String[] args){
        Value1 v1 = new Value1();
        v1.setI(10);
        System.out.println(v1);
        Value2 v2 = new Value2();
        v2.setI(10);
        System.out.println(v2);//主动调用toString
    }
}