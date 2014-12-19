import java.util.Random;

/** An instance of this class represents a coin that can be tossed.
**  The client can specify, via a constructor, the desired probability
**  with which "heads" should be the result of a toss.
*/

public class TossableCoin {

   /*   c o n s t a n t s   */

   private static int HEADS = 0;
   private static int TAILS = 1;


   /*   f i e l d s   */

   private int face;          // has value either HEADS or TAILS
   private double headsProb;  // probability of tossing heads
   private int headsCntr;     // # of times that heads have been tossed
   private int tailsCntr;     // # of times that tails have been tossed
   private Random rand;       // for generating pseudo-random toss results


   /***  c o n s t r u c t o r s  ***/

   /** Initializes a coin so that the likelihood of tossing heads
   **  is the specified value, which must be in the interval [0,1]
   **  (or else an IllegalArgumentException is thrown).
   */
   public TossableCoin(double probOfHeads)
   {
      if (0.0 <= probOfHeads  &&  probOfHeads <= 1.0) {
         headsProb = probOfHeads;
         headsCntr = 0;
         tailsCntr = 0;
         rand = new Random();
      }
      else {
         throw new IllegalArgumentException("probOfHeads must be in [0,1]");
      }
   }


   /** Initializes a coin so that the likelihood of tossing heads
   **  is one-half.
   */
   public TossableCoin() { this(0.5); }



   /***  o b s e r v e r s  ***/

   /** Returns either 'H' or 'T', according to whether the most recent
   **  toss resulted in heads or tails, respectively.
   */
   public char faceShowing()
   {
      if (face == HEADS) { return 'H'; }
      else { return 'T'; }
   }

   /** Returns the # of times that this coin has been tossed.
   */
   public int tossCounter() { return headsCntr + tailsCntr; }


   /** Returns the # of times that heads has been tossed on this coin.
   */
   public int headsCounter() { return headsCntr; }


   /** Returns the # of times that tails has been tossed on this coin.
   */
   public int tailsCounter() { return tailsCntr; }



   /***  m u t a t o r  ***/

   /** Tosses the coin, resulting in either "heads" or "tails".
   */
   public void toss()
   {
      double z = rand.nextDouble();
      if (z < headsProb) {
         face = HEADS;
         headsCntr = headsCntr + 1;
      }
      else {
         face = TAILS;
         tailsCntr = tailsCntr + 1;
      }
   }

}
