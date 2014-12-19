
   public class Combinations {
   
      private int counter;
   
      public Combinations(){
         this.counter = 0;
      }
   
      public void reset(){this.counter = 0;}
   
      public int viewCounter(){
         return this.counter;}
   
      public Integer Combo(Integer n, Integer r){
         this.counter++;  
         Integer upper = 1;
         Integer lower = 1;
         Integer diff = n-r;
         if(n < r) 
            return -1;
         //check to see if n-r is bigger then r, so that it can be done in less
         //computations
			else if (diff == 0) {
         while(n>r) {
            counter++;
            
            counter++;
            upper *= n;
            n--;            
            }
         } else if(diff> r) {
            while(n>diff) {
               counter++;
               
               counter++;
               upper*=n;
               
               counter++;
               lower *=r;
               
               n--;
               r--;
               }    
           } else {
            while(n>r) {
               counter++;
               
               counter++;
               upper*=n;
               
               lower*=diff;
               
               n--;
               diff--;
               }
           
           }
           counter++;
           return upper / lower;
      }
            
            
         
           
      public Integer factorial(Integer n, Integer stop) {
      //counter to account for any recursive use of this method
      counter++;
      if(n == stop) return 1;
      else {
         //counter to account for the multiplication used during the return
         counter++;
         return n * factorial(n-1, stop);   
         }
      }

   }