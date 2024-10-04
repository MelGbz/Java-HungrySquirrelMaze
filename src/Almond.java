/**
 * Inherited from the Nut super class.
 * Almond shall be represented by the character symbol ‘A’ in the maze and carries 5 nutritional points.
 * @author Melanie
 */
public class Almond extends Nut {
    // Attribute
    public static final int nutritionPoint = 5; // Represents the nutrition points an almond carries
    
    // Contructor
    public Almond()
    {
        super('A',0,0,"Almond"); // Temporary position
        this.name="Almond";
        this.nutritionPoints=nutritionPoint;
    }
    
    public Almond(int row, int col){
        super('A',row,col,"Almond");
        this.name="Almond";
        this.nutritionPoints=nutritionPoint;
    }
}
