package WealthMachines.Architecture;

import java.util.ArrayList;

/**
 * Created by golad on 8/14/2020.
 */
public class Population {
    private ArrayList<Person> population = new ArrayList<>();
    private ArrayList<Person> infectedPop= new ArrayList<>();


    public Boolean addPerson(Person person){
        population.add(person);
        return true;
    }


    public Boolean removePerson(Person removedPerson){

        if (-1 != population.indexOf(removedPerson)){
            population.remove(removedPerson);
            return true;
        }
        else {
            System.out.println(" Person doesnt exist");
            return false;
        }
    }

    public void readOut(){
        for (int i = 0;i < population.size() ; i++){
            Person readPerson = population.get(i);
            readPerson.readOut();
        }
    }


    private void removePerson(int position){
        population.remove(position);
    }


    public Person getPerson(Person person){

        int searchPerson = findPerson(person);

        return population.get(searchPerson);
    }

    public ArrayList<Person> getInfectedIndex() {
        return infectedPop;
    }

    public Person getPerson(int position){
        return population.get(position);
    }

    public int getSize(){
        return population.size();
    }


    private int findPerson(Person searchPerson){
        return population.indexOf(searchPerson);
    }

    public void findInfected(){
        for (int i = 0;i < population.size() ; i++){
            boolean isInfected = population.get(i).getInfected();

            if(isInfected&&(!infectedPop.contains(population.get(i)))){
                this.infectedPop.add(population.get(i));
            }
        }

        System.out.println("Population infected:");
        for (int i = 0; i < infectedPop.size(); i++){
            infectedPop.get(i).readOut();
        }
    }

    public void checkInfection(){
        for (int i=0; i<infectedPop.size();i++){
            int infX = infectedPop.get(i).getPosition().getX();
            int infY = infectedPop.get(i).getPosition().getY();

            for (int j=0; j<population.size();j++){
                int popX = population.get(j).getPosition().getX();
                int popY = population.get(j).getPosition().getY();
                if ((infX==popX)&&(infY==popY)){
                    population.get(j).setInfected(true);
                }
            }
        }
    }

    public boolean allInfected(){
        if(infectedPop.size() == population.size()){
            return true;
        }
        else{
            return false;
        }
    }
}
