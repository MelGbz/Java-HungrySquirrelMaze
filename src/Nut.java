/**
 * Abstract class that is inherited from Entity super class.
 * 2 types of nuts available: Almond and Peanut.
 * When a squirrel eats a nut, it gains points. A nut shall be removed from the maze once it is eaten.
 * There shall be total of 5 nuts in the maze.
 * The nuts locations shall be random and have to be placed in valid locations (not on *, @ or another nut).
 * The type of nut (peanut or almond) is random based on 50% probability
 * @author Melanie
 */
import java.util.Random;

public abstract class Nut extends Entity {
    // Attributes
    public static final int totalNuts = 5;  // Represents the total number of nuts that will be created for this game
    public static final int totalPoisonousNuts = 5;
    protected int nutritionPoints;          // Stores the nutrition points of a nut
    protected String name;                  // Name of the nut (“Almond” or “Peanut”)
    
    // Contructor
    public Nut()
    {
        super('N', 0, 0);
    }
    
    public Nut(char symbol, int row, int col, String name)
    {
        super(symbol,row,col);
        this.name=name;
    }
    
    // Method
    public String getName(){
        return this.name;
    }
    
    public void setName(String value){
        this.name=value;
    }
    
    public int getNutritionPoints(){
        return this.nutritionPoints;
    } 
    
    public void setNutritionPoints(int value){
       this.nutritionPoints=value;
    }
    
    /**
     * Randomly generated the location of the nut
     * > a nut cannot be placed over a wall (*), a squirrel (@) or a previously created nut : can only be placed where there is a blank space
     */
    @Override
    public void create() {
        Random random = new Random();
        int row;
        int col;

        while (true) {
            row = random.nextInt(Maze.Max_Maze_Row);
            col = random.nextInt(Maze.Max_Maze_Column);

            // Check if the generated position is available (not a wall, squirrel, or another nut)
            if (Maze.available(row, col)) 
            {
                Entity e = put(row, col);
                // If there was already a nut on the specified location, create a new one
                if(e instanceof Nut){
                    e.create();
                }
                // If there was already a squirrel on the specified location, create a new one
                if(e instanceof Squirrel){
                    e.create();
                }
                break;
            }
        }
    }
    
}
