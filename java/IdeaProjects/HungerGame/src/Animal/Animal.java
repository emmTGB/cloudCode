package Animal;

import field.Location;

import java.util.ArrayList;
import java.util.Locale;
import java.util.SimpleTimeZone;

public abstract class Animal {
    private int age;
    private int ageLimit;
    private final int breedableAge;
    private boolean isAlive;

    public Animal(int ageLimit, int breedableAge){
        this.ageLimit = ageLimit;
        this.breedableAge = breedableAge;
        age = 0;
        isAlive = true;
    }

    protected int getAge(){return age;}
    protected double getAgePercent(){return (double) age / (double) ageLimit;}
    protected void longerLife(int inc){
        ageLimit += inc;
    }

    public abstract Animal breed();
    public void grow(){
        age++;
        if(age < 0 || age >= ageLimit){
            die();
        }
    }
    public void die(){
        isAlive = false;
    }
    public boolean isAlive(){
        return isAlive;
    }
    public boolean isBreedable(){
        return (age >= breedableAge);
    }
    public Location move(Location[] freeAdj){
        Location ret = null;
        if (freeAdj.length > 0 && Math.random() < 0.2) {
            ret = freeAdj[(int)(Math.random() * freeAdj.length)];
        }
        return ret;
    }

    public Animal feed(ArrayList<Animal> neighbor){
        return null;
    }
    @Override
    public String toString(){
        return "" + age + ":" + (isAlive ? "alive" : "dead");
    }
}
