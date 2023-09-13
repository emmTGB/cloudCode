package noetepad;

import java.util.ArrayList;
import java.util.Arrays;

public class Notepad {
    private final ArrayList<String> notes = new ArrayList<>();
    public int getSize(){
        return notes.size();
    }
    public void addNote(String s){
        notes.add(s);
    }
    public String getNote(int index){
        if(index < notes.size()) return notes.get(index);
        else return "";
    }
    public void removeNote(int index){
        if(index < notes.size()) notes.remove(index);
    }
    public String[] getList(){
        String[] ret = new String[notes.size()];
        notes.toArray(ret);
        return ret;
    }
    public static void main(String[] args){
        Notepad n = new Notepad();
        n.addNote("123123");
        n.addNote("233233");
        System.out.println(Arrays.toString(n.getList()));
    }
}
