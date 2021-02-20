package WealthMachines.Architecture;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Created by golad on 8/11/2020.
 */
public class Person implements Observer{

    private String name;
    private Boolean infected;
    private Coordinates position;
    private int size;
    private static int personIDtracker = 0;
    private int personID;

    public Person() {
        this.name = "John";
        this.infected = false;
        this.position = new Coordinates();
        this.size = 10;
        this.personID = ++personIDtracker;
    }

    public Person(String name) {
        this.name = name;
        this.infected = false;
        this.position = new Coordinates();
        this.size = 10;
        this.personID = ++personIDtracker;
    }

    public Person(String name, Boolean infected, Coordinates position, int size) {
        this.name = name;
        this.infected = infected;
        this.position = position;
        this.size = size;
        this.personID = ++personIDtracker;
    }

    public void move (){
        Random rand = new Random();

        int X = rand.nextInt(this.size);
        int Y = rand.nextInt(this.size);

        this.position = new Coordinates(X,Y);
    }

    public void setInfected(Boolean infected) {
        this.infected = infected;
    }

    public Boolean getInfected() {
        return infected;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public void move (int X, int Y){
        if ((X <= this.size)&&(Y <= this.size)){
            this.position = new Coordinates(X,Y);
        }
        else {
            System.out.println(" X or Y values out of range. ");
        }
    }

    public void readOut(){
        System.out.println("name:" + name + ", infected?:" + infected.toString() + ", position:" + position.getX() +"," + position.getY() + ", Person ID:" + personID);
    }

    @Override
    public void update(Observable o, Object arg) {
        move();

        readOut();

    }
}
