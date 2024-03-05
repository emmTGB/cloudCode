package noetepad;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HashTableExample {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int amount = in.nextInt();
        Coin c = new Coin();
        String name = c.getName(amount);
        System.out.println(name);
    }
}
class Coin{
    private final HashMap<Integer, String> coinNames = new HashMap<>();//HashMap值对类型均应为对象
    public Coin(){
        coinNames.put(1, "penny");
        coinNames.put(1, "一毛");//key值唯一，如果再次出现同样的key值，则可看作更新该key对应的value
    }
    public String getName(int amount){
        return coinNames.get(amount);//若未找到则返回null
    }
}