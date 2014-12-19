/**
* As the value of N increases the amount counter increases is linear
* so long as the value of N being changed is a power. Like in the 
* 5th to 14th case were they are all powers of two.
*
* Also I assumed you didn't want us to use the Math class since it
* has ways of dealing with powers without using recursion.
*/
    public class XN {
   
      private int counter;
   
       public XN(){
         this.counter = 0;
      }
   
       public void reset(){this.counter = 0;}
   
       public int viewCounter(){
         return this.counter;}
   
       public double XToTheN(double x, int n){
         this.counter++;  
         // Complete the two assertions correctly
         assert x!=0 || n!=0: "Indeterminant form, x = "+x+", n = "+n;
         assert x>=0: "'n' cannot be negative";
         double one = 1.0;
      	// Complete the recursive algorithm
         if(x == 0.0) 
            return 0.0;
         else if(n == 0) 
            return 1.0;
         else if( n == 1)
            return x;
         // Complete the algorithm
         if(n % 2 == 0)
            return this.XToTheN(x*x, n/2);
         else
            return this.XToTheN(x*x, n-1);
      }
     
   
   }