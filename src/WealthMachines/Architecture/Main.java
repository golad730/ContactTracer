package WealthMachines.Architecture;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Population population = new Population();
    private static DayCounter dayCounter = new DayCounter();



    public static void main(String[] args) {
	// write your code here

        Person bob = new Person("Bob");
        Person bill = new Person("Bill");
        Person tom = new Person("Tom");
        Person tim = new Person("Tim");
        Person dan = new Person("dan");
        Person dav = new Person("dav");
        Person van = new Person("van");
        Person nik = new Person("nik");
        Person pat = new Person("pat");
        Person mat = new Person("mat");


        tim.setInfected(true);
        tom.setInfected(true);


/*        for (int i = 0; i < 10; i++){
            bob.readOut();
            bob.move();
        }*/


        Population newYork = new Population();
        DayCounter counter = new DayCounter();

        System.out.println(newYork.toString());

        newYork.addPerson(bob);
        newYork.addPerson(bill);
        newYork.addPerson(tom);
        newYork.addPerson(tim);
        newYork.addPerson(dan);
        newYork.addPerson(dav);
        newYork.addPerson(van);
        newYork.addPerson(nik);
        newYork.addPerson(pat);
        newYork.addPerson(mat);


        newYork.readOut();

        for (int i=0; i < newYork.getSize(); i++){
            counter.addObserver(newYork.getPerson(i));
            //System.out.println("Number of Observer: " + dayCounter.countObservers());
        }

        for (int i=0;i<40;i++){
            System.out.println("Iteration:" + i + ", Infected: " + newYork.getInfectedIndex().size());
            newYork.checkInfection();
            newYork.findInfected();
            counter.incrementDayCounter();
        }

        boolean quit = true;
        int choice = 0;

        welcomeString();
        printInstructions();
        while (quit){
            System.out.println("Please select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 0:
                    printInstructions();
                    break;
                case 1:
                    population.readOut();
                    break;
                case 2:
                    addPerson();
                    break;
                case 3:
                    modifyPerson();
                    break;
                case 4:
                    removePerson();
                    break;
                case 5:
                    runSimulation();
                    break;
                case 6:
                    //resetSimulation();
                    break;
                case 7:
                    //simulationParameters();
                    break;
                case 8:
                    quit = false;
                    break;
            }
        }
    }

    public static void runSimulation(){
        simulationString();
        printSimulationInstructions();

        try {
            int simMode = Integer.parseInt(scanner.nextLine());

            if (!((0 < simMode) && (simMode <= 2))){
                System.out.println("Invalid selection for simulation mode. Please enter 1 or 2");
            }
            else {
                switch (simMode){
                    case 1:
                        System.out.println("Absolute mode selected: " + simMode );
                        absoluteSimulation();

                        break;

                    case 2:
                        System.out.println("Abrupt mode selected: " + simMode );
                        break;
                }
            }

        } catch (InputMismatchException e){
            System.out.println("Input is invalid. Please enter integer. ");
        }


    }

    public static void absoluteSimulation(){

        int iteration = 0;

        for (int i=0; i < population.getSize(); i++){
            dayCounter.addObserver(population.getPerson(i));
            //System.out.println("Number of Observer: " + dayCounter.countObservers());
        }

        while (!population.allInfected()){
            System.out.println("Iteration:" + iteration + ", Infected: " + population.getInfectedIndex().size());
            population.checkInfection();
            population.findInfected();
            dayCounter.incrementDayCounter();
            iteration++;
        }
    }

    public static void addPerson(){
        System.out.println("Enter number of people to create in population: ");
        try {
            int peopleGenerated = scanner.nextInt();
            for (int i = 0; i < peopleGenerated; i++){
                Person newPerson = new Person();
                population.addPerson(newPerson);
            }
        } catch (InputMismatchException e){
            System.out.println(" Invalid input for persons created. Only real integer values excepted. ");
            printInstructions();
        }
    }

    public static void modifyPerson(){

        if (population.getSize() < 0){
            System.out.println("There are no individuals to modify.");
        }else {
            population.readOut();
            System.out.println("Please enter index of person to modify");

            try {
                int modifyIndex = scanner.nextInt();
                scanner.nextLine();

                boolean inRange = modifyIndex <= population.getSize();

                if (inRange){

                    int xPos = 0;
                    int yPos = 0;

                    boolean isInfected = false;


                    System.out.println("Enter name of person: ");
                    String name = scanner.nextLine();


        /*  Get infection status from users */
                    try {
                        System.out.println("Is infected? 1 for true, 2 for false: ");
                        int infected = Integer.parseInt(scanner.nextLine());

                        if (infected == 1){
                            // set change
                            isInfected = true;
                        }if (infected == 2){
                            isInfected = false;
                        }if ((infected != 1)&&(infected != 2)){
                            System.out.println("You did not enter 1 or 2");
                        }

                    } catch (InputMismatchException e){
                        System.out.println("Invalid input for infection status ");
                    }

        /*  Get x and y coordinates from users */
                    try {
                        System.out.println("Enter  X coordinate in range of (0,10):");
                        int xCoord = Integer.parseInt(scanner.nextLine());
                        xPos = xCoord;

                        if (!((0 < xCoord) && (xCoord <= 10))){
                            System.out.println("Invalid input range for X coordinate. Defaults to 0.");
                            xPos = 0;
                        }
                        else {
                            System.out.println("X coordinate set:" + xCoord );
                        }

                    } catch (InputMismatchException e){
                        System.out.println("Input is invalid. Please enter integer. ");
                    }

                    try {
                        System.out.println("Enter  Y coordinate in range of (0,10):");
                        int yCoord = Integer.parseInt(scanner.nextLine());
                        yPos = yCoord;

                        if (!((0 < yCoord) && (yCoord <= 10))){
                            System.out.println("Invalid input range for Y coordinate. Defaults to 0.");
                            yPos = 0;
                        }
                        else {
                            System.out.println("Y coordinate set:" + yCoord);
                        }

                    } catch (InputMismatchException e){
                        System.out.println("Input is invalid. Please enter integer. ");
                    }

                    // Get person from population using index and replace.

                    Person modifyPerson = population.getPerson(modifyIndex-1);
                    Coordinates modifyCoordinates = new Coordinates(xPos, yPos);

                    modifyPerson.setName(name);
                    modifyPerson.setInfected(isInfected);
                    modifyPerson.setPosition(modifyCoordinates);

                    modifyPerson.readOut();

                }else{
                    System.out.println("Your index is out of range");
                }

            }catch (InputMismatchException e){
                System.out.println(" Invalid input for index. Only real integer values excepted. ");
            }
        }
    }



    public static void removePerson(){

        //population.readOut();

        try {
            System.out.println("Please enter index of person to remove");
            int removeIndex = scanner.nextInt();
            scanner.nextLine();

            boolean inRange = removeIndex <= population.getSize();

            if (inRange){
                population.getPerson(removeIndex).readOut();
                population.removePerson(population.getPerson(removeIndex));
            }

        } catch (InputMismatchException e){
            System.out.println("Input is invalid. Please enter integer. ");
        }
    }

    public static void printSimulationInstructions(){
        System.out.println("\n Please enter ");
        System.out.println("\t 1 - Absolute Mode Simulation. ");
        System.out.println("\t 2 - Abrupt Mode Simulation. ");
    }

    public static void simulationString(){
        System.out.println("Simulations run in 1 of 2 modes, Absolute-mode, and Abrupt-mode. Descriptions are below. " +
                "\nAbsolute-mode simulation: takes the initial frame of the population set before hand, and run the day counter until all members are infected. " +
                "\nif No member of the population is infected in the initial frame, then a member will be selected randomly to start the simulation. " +
                "\nAbrupt-mode simulation: Allows user to pause simulation at specified points.");
    }

    public static void printInstructions(){

        System.out.println("\n Please enter ");
        System.out.println("\t 0 - To print choice options. ");
        System.out.println("\t 1 - To print individuals in population. ");
        System.out.println("\t 2 - To generate individuals in population. ");
        System.out.println("\t 3 - To modify an individual in the population. ");
        System.out.println("\t 4 - To remove an individual from the population. ");
        System.out.println("\t 5 - To run simulation. ");
        System.out.println("\t 6 - To reset the application. ");
        System.out.println("\t 7 - To set simulation parameters.");
        System.out.println("\t 8 - To quit the application. ");
    }

    public static void welcomeString(){
        System.out.println("Welcome to Population Contact Tracing. " +
                "\nThis application uses random variables to simulate movement of individuals in a population on a daily time scale." +
                "\nMembers of the population are either infected or not based on initialized conditions and transmission through simulation." +
                "\nTransmission is simulated by individuals sharing coordinates with infected members. Positioning is based on the positive cartesian coordinate system.");

    }
}
