package WealthMachines.Architecture;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by golad on 8/15/2020.
 */
public class DayCounter extends Observable {
    private int day;


    public int getDay() {
        return day = 0;
    }



    public void nextDay(){
        this.day = this.day++;
    }

    public void incrementDayCounter(){
        nextDay();
        setChanged();
        notifyObservers();
    }

    public void readOut(){
        System.out.println("Day counter: " + this.day);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public synchronized int countObservers() {
        return super.countObservers();
    }
}
