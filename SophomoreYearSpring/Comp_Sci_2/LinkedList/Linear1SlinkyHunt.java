/*
NAME:
If you worked with someone:
	List individuals:
	
	What was your contribution:


If you helped someone, for each person, list who and how you helped.




If someone helped you, for each person, list who and how you helped.




*/
   import java.io.*;
   import java.util.*;
   import scranton.*;

/**
* An array implementation of the Hunt interface
*
*/
   public class Linear1SlinkyHunt implements Hunt{
      private Linear1<SteppingStone> left, right; // Two null Linear1 objects
      private int start;
   	
      public void initialize(String fileName) throws IOException {
         BufferedReader buffer = new BufferedReader(new FileReader(fileName));
         this.start = Integer.valueOf(buffer.readLine()).intValue();
         while (buffer.ready()){ //Build the slinky into the left object
            StringTokenizer token = new StringTokenizer(buffer.readLine(), "\t");
            SteppingStone Stone = new SteppingStone(token.nextToken(), new Integer(token.nextToken()));
            this.left = (new Linear1<SteppingStone>(Stone, this.left));  // attach new Linear2 object
            System.out.print(this.left.getData().toString());
         }
         System.out.println();
         startSlinky();
      }
   	
      public void startSlinky(){ // Slinky from left to right
         while(this.left != null){
            Linear1<SteppingStone> temp = this.left;
            this.left = this.left.getLink();
            temp.setLink(this.right);
            this.right = temp;
         }
      }

      public String toString(){ // Display the left side
         return this.right.toString();
      }
      
         
      public String hunt(){
         String answer = "";
         int move = this.start; // Get the first move
         boolean swap = false; // Used to check if switching to positive to negative
         while(move!=0){
            if (swap) { // If swap is true it executes method roadSwap
               roadSwap(move);
            } else { // Otherwise it uses the regular road method
               road(move);
            }
            // Get next move
            if(move>0){ // If move is positive it uses right to get symbol and direction
               move = this.right.getData().getDirection();
               answer+=this.right.getData().getSymbol();
               // Checks to see if the next move switches the direction to negative. If it does
               // swap becomes true, if it doesn't swap becomes false
               if(move<0) swap = true;
               else swap = false;
               } else { // If negative it uses left to get the symbol and direction
               move = this.left.getData().getDirection();
               answer+=this.left.getData().getSymbol();
               // Checks to see if the next move switches the direction to positive. If it does
               // swap becomes true, if it doesn't swap becomes false
               if(move>0) swap = true;
               else swap = false;
               }
         }
         return answer;
      }
      
   // Used when swap is true. The reason it increases/decreases move by 1 is to correct an
   // off by one error that occurs as a result of switching between the left and right sides
   private void roadSwap(int move) {
      // Checks to see if move is positive or negative
      if(move > 0) {
         // Loops through links putting the ones it passes in reverse order and stopping at move
         for(int i = 0; i < move-1; i++) {
            Linear1<SteppingStone> temp = this.right;
            this.right = this.right.getLink();
            temp.setLink(this.left);
            this.left = temp;
            }
         } else {
         // Loops through links putting the ones it passes in reverse order and stopping at move
         for(int i = 0; i > move+1; i--) {
            Linear1<SteppingStone> temp = this.left;
            this.left = this.left.getLink();
            temp.setLink(this.right);
            this.right = temp;
            }
        }
      }
   private void road(int move) {
      // Check to see if move is positive or negative
      if(move > 0) {
         //loops through links putting the ones it passes in reverse order and stopping at move      
         for(int i = 0; i < move; i++) {
            Linear1<SteppingStone> temp = this.right;
            this.right = this.right.getLink();
            temp.setLink(this.left);
            this.left = temp;
            }
         } else {         
         //loops through links putting the ones it passes in reverse order and stopping at move
         for(int i = 0; i > move; i--) {
            Linear1<SteppingStone> temp = this.left;
            this.left = this.left.getLink();
            temp.setLink(this.right);
            this.right = temp;
            }
        }
      }   
   }
