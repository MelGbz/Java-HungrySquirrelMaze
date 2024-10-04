/**
 * The Wall class is inherited from Entity super class.
 * Walls shall remain stationary and don’t move in the maze
 * @author Melanie
 */
public class Wall extends Entity {
    // Contructor
    public Wall(int row, int column)
    {
        super('*', row, column);
    }

    @Override
    public void create() {
        throw new UnsupportedOperationException("No create method supported for walls yet.");
    }
        
}
