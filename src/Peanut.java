/**
 * Inherited from the Nut super class.
 * Peanut shall be represented by the character symbol ‘P’ in the maze and carries 10 nutritional points.
 * @author Melanie
 */
public class Peanut extends Nut {
    // Attribute
    public static final int nutritionPoint = 10; // Represents the nutrition points a peanut carries
    
    // Contructor
    public Peanut()
    {
        super('P',0,0,"Peanut"); // Temporary position
        this.name="Peanut";
        this.nutritionPoints = nutritionPoint;
    }
    
    public Peanut(int row, int col)
    {
        super('P',row,col,"Peanut"); // Temporary position
        this.name="Peanut";
        this.nutritionPoints = nutritionPoint;
    }
}
