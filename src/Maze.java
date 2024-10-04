/**
 *
 * @author Melanie
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    // Attributes
    public static final int Max_Maze_Row = 20;      // Defines the maximum number of rows in the maze
    public static final int Max_Maze_Column = 50 ;  // Defines the maximum number of columns in the maze
    private static Entity[][] maze;                 // Contains the full maze and the entities
    
    // Constructor
    public Maze()
    {
        maze = new Entity[Max_Maze_Row][Max_Maze_Column];
    }
    
    public Maze(String filename)
    {
        maze = new Entity[Max_Maze_Row][Max_Maze_Column];
        create(filename);
    }
    
    // Methods
    /**
     * This method returns the maze tab
     * @return Entity[][]
     */
    public static Entity[][] getMaze(){
        return maze;
    }
    
    /**
     * This method set an entity at a given location
     * @param row
     * @param col
     * @param e 
     */
    public static void setMaze(int row,int col,Entity e){
        maze[row][col]=e;
    }
    
    /**
     * This method reads the file passed to the method (Maze.txt) and initializes 
     * the 2-dimentional array with the maze content provided in the file.
     * @param filename 
     */
    public static void create(String filename)
    {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int row = 0;
            while (scanner.hasNextLine() && row < Max_Maze_Row) {
                String line = scanner.nextLine();
                for (int col = 0; col < line.length() && col < Max_Maze_Column; col++) {
                    if(line.charAt(col) == '*')
                    {
                        maze[row][col] = new Wall(row, col);
                    }
                    else
                    {
                        maze[row][col] = null;
                    }
                }
                row++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
    
    /**
     * This method displays the maze structure and the containing entities.
     */
    public static void display()
    {
        for (int row = 0; row < Max_Maze_Row; row++)
        {
            for (int col = 0; col < Max_Maze_Column; col++)
            {
                if(maze[row][col]==null)
                {
                    System.out.print(' ');
                }
                else
                {
                    System.out.print(maze[row][col]);
                }
            }
            
            // Displays line number
            if(row < 10) { System.out.print(" |  " + row); }
            else { System.out.print(" | " + row); }
            
            System.out.println();
        }
    }
    
    /**
     * This method takes a row and a column and determines if the location is a blank space. 
     * If it is, it returns true; otherwise, it returns false.
     * @param row
     * @param col
     * @return 
     */
    public static boolean available(int row, int col)
    {
        if (row >= 0 && row < Max_Maze_Row && col >= 0 && col < Max_Maze_Column) {
            // Check for Wall on the specified location. Nuts and Squirrel need to be checked separately
            return !(maze[row][col] instanceof Wall);
        }
        return false;
    }
}
