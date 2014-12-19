/**
 * A simple class that holds a pair of attributes, a character and an integer
 *
 * @author J. Beidler
 */
    public class SteppingStone {
   
   /**
   * The attribute that holds the symbol, a char
   */
      private String symbol;
   
   /**
   * The attribute that holds the symbol, a char
   */
      private Integer direction;
   
   /**
   * SteppingStone constructor.  Sets all attributes.
   *
   * @param sym The SteppingStone symbol attribute
   * @param dir The SteppingStone direction attribute
   */
       public SteppingStone(String sym, Integer dir){
         this.symbol = sym; 
         this.direction = dir;
      }
   
   /**
   * Returns the symbol attribute
   *
   * @return The symbol attribute
   */
       public String getSymbol(){
         return this.symbol;
      }
   
   /**
   * Returns the direction attribute
   *
   * @return The direction attribute
   */
       public Integer getDirection(){
         return this.direction;
      }
   
   /**
   * Returns the string representation of the Object
   * @return A string representation of the SteppingStone.  The format is {'char'\tint}
   */
       public String toString(){
         return "('"+symbol+"', "+Integer.toString(direction)+")";
      }
   
   }