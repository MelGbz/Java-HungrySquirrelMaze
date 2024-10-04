/**
 *
 * @author Melanie
 */
public class PoisonousCashew extends Nut {
    // Attribute
    public static final int nutritionPoint = -15;
    
    // Constructor
    public PoisonousCashew()
    {
        super('X',0,0,"Cashew"); // Temporary position
        this.nutritionPoints=nutritionPoint;
    }
    
    public PoisonousCashew(int row, int col)
    {
        super('X',row,col,"Cashew");
        this.nutritionPoints=nutritionPoint;
    }
}
