//Adam Miller
//Solution to Programming Assignment #8 Fall 2012

/** An instance of this class represents a polynomial with integer
**  coefficients.
*/
public class Polynomial {

   //  i n s t a n c e   v a r i a b l e s 
   //  -----------------------------------

   // coeff[i] is the coefficient of x^i in this polynomial.
   private final int[] coeff;


   //  c o n s t r u c t o r 
   //  ---------------------

   /** Establishes this polynomial to have coefficients corresponding to the
   **  values in the specified array (c[]).
   */
   public Polynomial(int[] c)
   {
      // Find the highest-numbered location in c[] containing a non-zero
      // value in order to determine the degree of this polynomial.
      int degree = c.length - 1;
      while (degree > 0  &&  c[degree] == 0)
         { degree = degree - 1; } 

      // Create the coefficient array to have just as many elements as needed.
      this.coeff = new int[degree+1];

      // Place the coeffiencients into coeff[].
      if (c.length == 0)  // For the odd case in which c[] has no elements
         { this.coeff[0] = 0; }
      else {
         for (int i=0; i != degree+1; i++) {   // copy the relevant elements
            this.coeff[i] = c[i];              // of c[] into coeff[]
         }
      }
   }


   /** Returns the degree of this polynomial.
   */
   public int degreeOf() { return this.coeff.length - 1; }


   /** Returns the coefficient of x^k in this polynomial.
   **  (If k > degreeOf(), zero is returned.)
   */
   public int coefficient(int k)
   { 
      int result;
      if(k > degreeOf()) { result = 0; } 
      else {
      result = coeff[k];
      }
      return result;
   }


   /** Returns the result of evaluating the polynomial at the specified
   **  value (x).
   */
   public double evaluateAt(double x)
   {
      double result = 0.0;
      for(int i = 0; i <= degreeOf(); i++) {
      result += coeff[i] * Math.pow(x, i);
      }     
      return result;
   }


   /** Returns a value that is within EPSILON units of a root (aka zero)
   **  of this polynomial.
   **  pre: low <= high  &&  this.evaluateAt(low) * this.evaluateAt(high) <= 0
   */
   public double rootOf(double low, double high, double EPSILON)
   {
      double mid = 0;
      while(low + EPSILON < high) 
      {
        mid = (low + high) / 2;
        double guess = evaluateAt(low) * evaluateAt(mid);
        if(guess > 0 ) { low = mid; }
        else { high = mid; }
      }
      return low;
    }


   /** Returns a string describing the polynomial. 
   **  Example: "3 + -4x^1 + 17x^2 + 0x^3 + 2x^4"
   */
   public String toString()
   {
      String result = coeff[0] + "";    
      for( int i = 1; i <= degreeOf(); i++) {
         result += " + " + coeff[i] + "x^" + i;
      }
      return result;
   }


   //  g e n e r a t o r s
   //  -------------------

   /** Returns the sum of this polynomial and the one given (p).
   */
   public Polynomial sum(Polynomial p)
   {
      int[] poly = properSize(p);
      for(int i = 0; i < poly.length; i++)
      {
         poly[i] = this.coefficient(i) + p.coefficient(i);
      }
   
      Polynomial sum = new Polynomial(poly);

      return sum;
   }


   /** Returns the difference of this polynomial and the one given (p).
   */
   public Polynomial difference(Polynomial p)
   {
      int[] poly = properSize(p);
      for(int i = 0; i < poly.length; i++)
      {
         poly[i] = this.coefficient(i) - p.coefficient(i);
      }
   
      Polynomial difference = new Polynomial(poly);

      return difference;
   }


   /** Returns the product of this polynomial and the given one (p).
   */
   public Polynomial product(Polynomial p)
   {
      int[] poly = new int[degreeOf() + p.degreeOf() +1];
      for(int i = 0; i <= degreeOf(); i++) 
      {
         for(int j = 0; j <= p.degreeOf(); j++) {
         poly[j+i] += this.coefficient(i) * p.coefficient(j);      
      }
   }
      Polynomial product = new Polynomial(poly);
      
      return product;  // STUB!!
   }
   
   private int[] properSize(Polynomial p) 
   {
      int polySize = 0;
      if (p.degreeOf() > this.degreeOf()) {
         polySize = p.degreeOf();
      } else {
         polySize = this.degreeOf();
      }
      int[] poly = new int[polySize + 1];
      return poly;
   }
}
