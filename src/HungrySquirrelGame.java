
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Melanie
 */
public class HungrySquirrelGame {
    
    /**
     * This method create as many as nuts as totalNuts and place it randomly in the maze
     */
    public static void createNuts() 
    {
        Random random = new Random();
        
        for (int n = 0; n < Nut.totalNuts; n++) 
        {
            Nut nut;
            if (random.nextBoolean()) 
            {
                nut = new Almond(); // Almond if true
            } 
            else 
            {
                nut = new Peanut(); // Peanut if false
            }
            nut.create(); // Randomly place the nut
        }
    }
    
    /**
     * This method create as many as nuts as totalPoisonousNuts and place it randomly in the maze
     */
    public static void createPoisonousNuts() 
    {
        for (int i = 0; i < Nut.totalPoisonousNuts; i++) 
        {
            Nut nut = new PoisonousCashew();
            nut.create(); // Randomly place the poisonous cashew
        }
    }
    
    public static void printScore(int totalPoint, int nutleft)
    {
        System.out.print("------------------- Info Score -------------------\n");
        System.out.println("Points collected : " + totalPoint);
        System.out.println("Nuts left : " + nutleft);
        System.out.print("--------------------------------------------------\n");
    }
    
    public static void main(String[] args)
    {
        System.out.print("##################################################\n");
        System.out.print("#################### WELCOME #####################\n");
        System.out.print("###################### TO ########################\n");
        System.out.print("############## HUNGRY SQUIRREL GAME ##############\n");
        System.out.print("##################################################\n");
        System.out.print("--------------------------------------------------\n");
        
        // 1 - Call the create method of the Maze class to create the maze.
        Maze maze = new Maze();
        maze.create("Maze.txt");
        maze.display();
        System.out.print("--------------------------------------------------\n");
        
        // 2 - Create a Squirrel object. This creates the squirrel and puts the squirrel in the maze based on the user input.
        Squirrel squirrel = new Squirrel();
        squirrel.create();
        System.out.print("--------------------------------------------------\n");
        maze.display();
        System.out.print("--------------------------------------------------\n");
        
        // 3 - Instantiate an array of Nut objects and determine and create the type of nut (almond or peanut).
        createNuts();
        createPoisonousNuts();
        printScore(squirrel.getPointsCollected(),Nut.totalNuts-squirrel.totalNutsEaten);
        
        // 4 - Display the maze with all the entities created.
        maze.display();
        System.out.print("--------------------------------------------------\n");
        
        // Let's play !
        boolean status = false;
        Scanner scanner = new Scanner(System.in);
        
        while (!status) {
            try {
                // 5 - Accept user input to move the squirrel.
                System.out.println("USER - Enter a move : U(Up), D(Down), L(Left), R(Right) then press ENTER: ");
                char move = scanner.next().charAt(0);

                // 6 - For every move the full maze with all the entities should be displayed.
                squirrel.move(move);
                printScore(squirrel.getPointsCollected(), Nut.totalNuts - squirrel.totalNutsEaten);
                maze.display();
                System.out.print("--------------------------------------------------\n");
                
                // 7 - Every time the squirrel eats a nut, program prints the points collected for the new nut 
                //      and total points collected thus far. 
                //      "!!! Squirrel ate Almond and gained 5 points (Total 15 points) !!!"
                // >> see Squirrel class
                
                // 8 - Once the squirrel collects all the nuts, a message must be displayed and the game is over.
                //      “Squirrel successfully collected all the nuts. Total points 30.”
                if ((squirrel.totalNutsEaten == Nut.totalNuts)) {
                    
                    System.out.println("Squirrel successfully collected all the nuts. Total points " + squirrel.getPointsCollected());
                    System.out.print("\n##################################################");
                    System.out.print("\n######## THANK YOU FOR PLAYING THIS GAME #########\n");
                    System.out.print("##################################################\n");
                    
                    status = true;
                    break;
                }
                //      “Thank you for playing this game”
                if(squirrel.getPointsCollected()<0){
                    
                    System.out.println("Squirrel a poisonous cashew and DIED !! Score : " + squirrel.getPointsCollected());
                    System.out.print("\n##################################################");
                    System.out.print("\n######## THANK YOU FOR PLAYING THIS GAME #########\n");
                    System.out.print("##################################################\n");
                    status = true;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid move (U/D/L/R).");
                scanner.next(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                // Optional: Decide whether to break the loop or continue
            }
        }
        scanner.close();
        
        /*
        System.out.println("################### TEST MAZE ####################\n");
        // Create Maze
        Maze maze = new Maze();
        System.out.println("Maze dimension sets on Row = " + Maze.Max_Maze_Row + " & Column = " + Maze.Max_Maze_Column + "\n");
        maze.create("Maze.txt");
        // Display Maze
        maze.display();
        // Test availibility
        System.out.println("\nrow 0 & col 10 available : " + maze.available(0, 10));
        System.out.println("row 4 & col 15 available : " + maze.available(4, 15));
        System.out.println("\n############## TEST MAZE SUCCESSFUL ##############");
        
        System.out.println("\n################# TEST ENTITIES ##################");
        // Create Squirrel
        Squirrel squirrel = new Squirrel();
        squirrel.create();
        maze.display();
        // Create Nuts
        Nut nut = new Nut();
        nut.create();
        maze.display();
        // Create Almond
        nut = new Almond();
        nut.create();
        maze.display();
        // Create Peanut
        nut = new Peanut();
        nut.create();
        maze.display();
        // Create Poisonous Cashew
        nut = new PoisonousCashew();
        nut.create();
        maze.display();
        // Test put method
        Entity replacedEntity = nut.put(10, 10);
        System.out.println("Replaced entity (10,10) = " + replacedEntity);
        maze.display();
        replacedEntity = nut.put(8, 8);
        System.out.println("Replaced entity (8,8) = " + replacedEntity);
        maze.display();
        System.out.println("\n############ TEST ENTITIES SUCCESSFUL ############");
        */
    }
}
