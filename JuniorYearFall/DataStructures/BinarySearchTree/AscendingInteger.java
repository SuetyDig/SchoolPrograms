   //package scranton;

   import java.util.Comparator;
	
/**
 * A comparator for ordering Integer objects in their natural order, their cmparable order.
 */
    class AscendingInteger implements Comparator<Integer> {
   
   /**
   * The comparator's compare method
   * @param Left The first object in the comparison
   * @param Right The second object in the comparison
   * @return returns left - Right
   */
       public int compare(Integer Left, Integer Right){
         return Left.compareTo(Right);
      }
   }