/**
 *
 * @author Melanie
 */
public abstract class Entity {
    // Attributes
    protected char symbol;      // Character symbol by which an entity is identified on the Maze
    protected int row;          // Row position of the entity in the maze
    protected int column;       // Column position of the entity in the maze
    
    // Constructor
    public Entity(char symbol, int row, int column)
    {
        this.symbol = symbol;
        this.row = row;
        this.column = column;
    }
    
    // Methods
    /**
     * Simple get method to access symbol, row and column from any class
     * @return symbol, row or column
     */
    public char getSymbol() { return symbol; }
    public int getRow() { return row; }
    public int getCol() { return column; }
    
    /**
     * Abstract method that will be override in Nuts, Squirrel, Wall
     */
    abstract public void create();
    
    /**
     * This method puts an entity at location (newRow, newCol) in the maze.
     * @param newRow
     * @param newCol
     * @return This method returns an object that was replaced in the maze
     */
    public Entity put(int newRow, int newCol) 
    {
        Entity replacedEntity = Maze.getMaze()[newRow][newCol];
        Maze.setMaze(this.row,this.column,null); // Delete previous position
        this.column=newCol;
        this.row=newRow;
        Maze.setMaze(newRow,newCol,this);
        return replacedEntity;
    }
    
    @Override
    public String toString()
    {
        return symbol+"";
    }
}