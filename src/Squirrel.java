
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Melanie
 */
public class Squirrel extends Entity implements Movable {
    /*
    Inherited from the Entity abstract super class + implement Movable
    Squirrel shall be represented by the “@” symbol in the maze
    Squirrel shall be able to move up, down, left and right
    The initial location of the squirrel shall be determined by the user
    The program shall prompt the user to enter the starting row and column of the squirrel
    Squirrel cannot move over a wall 
    Squirrel can over move a nut. Once the squirrel moves over a nut, it eats the nut and collects points. Each type of nut carries different point.
    */
    
    // Attributes
    protected int pointsCollected;           // Provides the total points a squirrel has accumulated when eating nuts
    protected int totalNutsEaten;            // Provides the total number of nuts eaten thus far
    protected int totalPoisonousCashewEaten; // Provides the total number of poisonous nuts eaten thus far
    
    // Constructor
    public Squirrel()
    {
        super('@', 0, 0);
        this.pointsCollected = 0;
        this.totalNutsEaten = 0;
        this.totalPoisonousCashewEaten = 0;
    }
    
    public Squirrel(int row, int column)
    {
        super('@', row, column);
        this.pointsCollected = 0;
        this.totalNutsEaten = 0;
        this.totalPoisonousCashewEaten = 0;
    }
    
    // Method
    /**
     * This methods get the total of point collected
     * @return 
     */
    public int getPointsCollected()
    {
        return this.pointsCollected;
    }
    
    /**
     * Implementation of the abstract method in the Entity superclass
     * Prompts the user to enter the initial location of the squirrel in the maze
     * > make sure the location provided by the user is valid and available
     */
    @Override
    public void create() 
    {
        Scanner scanner = new Scanner(System.in);
        int row = -1;
        int col = -1;

        while (true) 
        {
            try 
            {
                System.out.print("Enter row for squirrel (0-"+Maze.Max_Maze_Row+"): ");
                row = scanner.nextInt();
                System.out.print("Enter column for squirrel (0-"+Maze.Max_Maze_Column+"): ");
                col = scanner.nextInt();

                // Validate the input location
                if (row >= 0 && row < Maze.Max_Maze_Row && col >= 0 && col < Maze.Max_Maze_Column && Maze.available(row, col)) 
                {
                    Entity replacedEntity = this.put(row, col); // Set the position if valid
                    break;
                } 
                else 
                {
                    System.out.println("Invalid or unavailable location. Please enter a new location.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integer values.");
                scanner.next(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Moves the squirrel one position to the direction specified
     * @param direction 
     */
    @Override
    public void move(char direction) 
    {
        // Actual location
        int newRow = this.row;
        int newCol = this.column;

        switch(direction) {
            case 'u' : // Move up
                newRow -= 1;
                break;
            case 'd': // Move down
                newRow += 1;
                break;
            case 'l': // Move left
                newCol -= 1;
                break;
            case 'r': // Move right
                newCol += 1;
                break;
            default : 
                System.out.println("Wrong direction. Please try again !");
                break;
        }

        // Check if the new position is available
        if (Maze.available(newRow, newCol)) 
        {
            Entity replacedEntity = this.put(newRow, newCol);
            if(replacedEntity instanceof Almond){
                this.pointsCollected += Almond.nutritionPoint;
                this.totalNutsEaten += 1;
                System.out.print("--------------------------------------------------\n");
                System.out.println("!!! Squirrel ate Almond and gained 5 points !!! (Total "+ this.pointsCollected+ " points)\n");
            }
            if(replacedEntity instanceof Peanut){
                this.pointsCollected += Peanut.nutritionPoint;
                this.totalNutsEaten += 1;
                System.out.print("--------------------------------------------------\n");
                System.out.println("!!! Squirrel ate Peanut and gained 10 points !!! (Total " + this.pointsCollected + " points)\n");
            }
            if(replacedEntity instanceof PoisonousCashew){
                this.pointsCollected += PoisonousCashew.nutritionPoint;
                this.totalPoisonousCashewEaten += 1;
                System.out.print("--------------------------------------------------\n");
                System.out.println("!!! OUCH ! Squirrel ate Poisonous Cashew and lost 15 points !!! (Total " + this.pointsCollected + " points)\n");
            }
        }
        else
        {
            System.out.println("!!! OUPSES ! Squirrel just hit a wall... Try again !!!\n");
        }
    }
    
}
