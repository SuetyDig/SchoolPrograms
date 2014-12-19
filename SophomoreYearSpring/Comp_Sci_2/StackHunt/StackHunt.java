/*
NAME:
If you worked with someone:
	List individuals:
	
	What was your contribution:


If you helped someone, for each person, list who and how you helped them.




If someone helped you, for each person, list who and how they helped you.




*/
import java.io.*;
import java.util.*;

public class StackHunt implements Hunt {
	private Stack<SteppingStone> left = new Stack<SteppingStone>();
	private Stack<SteppingStone> right = new Stack<SteppingStone>();
	private int start;
	
	public void initialize(String fileName) throws IOException {
      // Sets up a buffer and links the file to the buffer
      BufferedReader buffer = new BufferedReader(new FileReader(fileName));
      // Reads the first line, which contains a number, translate the string to a number and places it in start
      this.start = Integer.valueOf(buffer.readLine()).intValue();
      // Loop until the buffer runs out of records
      while (buffer.ready()){
         // Place the buffer contents into a string tokenizer using the tab token
         StringTokenizer token = new StringTokenizer(buffer.readLine(), "\t");
         // first token is a char, second in an int, use them to construct a SteppingStone
         SteppingStone Stone = new SteppingStone(token.nextToken(), new Integer(token.nextToken()));
         // Place the stone in Stack left
         left.push(Stone);
      }
      // Slinky the stones from the left stack to the right stack.
      System.out.println();
      startSlinky();
	}
   
   public void startSlinky() {
      while(!this.left.isEmpty()) {
         this.right.push(left.pop());
      }
   }
	
	public String toString(){
		int count = 0;
		while(!this.left.empty()){
			this.right.push(this.left.pop());
			count++;
		}
		String ans = this.right.toString();
		while(count!=0){
			this.left.push(this.right.pop());
			count--;
		}
		return ans;
	}
	
   public String hunt(){
		String answer = "";
      int move = this.start;
      boolean swap = false; // Used to check if switching to positive to negative
      while(move!=0){
         if (swap) { // If swap is true it executes method roadSwap
            roadSwap(move);
         } else { // Otherwise it uses the regular road method
            road(move);
         }
         // Get next move
         if(move>0){ // If move is positive it uses right to get symbol and direction
            move = this.right.peek().getDirection();
            answer+=this.right.peek().getSymbol();
            // Checks to see if the next move switches the direction to negative. If it does
            // swap becomes true, if it doesn't swap becomes false
            if(move<0) {
               swap = true;
            } else {
             swap = false;
            }
          } else { // If negative it uses left to get the symbol and direction
            move = this.left.peek().getDirection();
            answer+=this.left.peek().getSymbol();
            // Checks to see if the next move switches the direction to positive. If it does
            // swap becomes true, if it doesn't swap becomes false
            if(move>0) {
            swap = true;
            } else { 
            swap = false;
            }
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
            this.left.push(this.right.pop());
         }
      } else {
         // Loops through links putting the ones it passes in reverse order and stopping at move
         for(int i = 0; i > move+1; i--) {
            this.right.push(this.left.pop());         
         }
      }
   }
   private void road(int move) {
      // Check to see if move is positive or negative
      if(move > 0) {
         //loops through links putting the ones it passes in reverse order and stopping at move      
         for(int i = 0; i < move; i++) {
            this.left.push(this.right.pop());
         }
      } else {         
         //loops through links putting the ones it passes in reverse order and stopping at move
         for(int i = 0; i > move; i--) {
            this.right.push(this.left.pop());
         }
      }
   }   
}
