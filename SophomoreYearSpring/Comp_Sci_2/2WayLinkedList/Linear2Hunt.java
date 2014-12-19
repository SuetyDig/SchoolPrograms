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
   public class Linear2Hunt implements Hunt{
      private Linear2<SteppingStone> path;
      private int start;
   	
      public void initialize(String fileName) throws IOException {
         BufferedReader buffer = new BufferedReader(new FileReader(fileName));
         this.start = Integer.valueOf(buffer.readLine()).intValue();
         this.path = new Linear2<SteppingStone>();
         Linear2<SteppingStone> current = path;
         while (buffer.ready()){
            StringTokenizer token = new StringTokenizer(buffer.readLine(), "\t");
            SteppingStone Stone = new SteppingStone(token.nextToken(), new Integer(token.nextToken()));
            current = (new Linear2<SteppingStone>(current, Stone, null));  // attach new Linear2 object
            System.out.print(current.getData().toString());
         }
         System.out.println();
      }
   
      public String toString(){
         return this.path.toString();
      }
   
      public String hunt(){
         String answer = "";
         path = path.getNext();
         while(start !=0) {
            //Check to see if the direction is positive            
            if(start > 0) {
               //Moves forward through the references, stopping at the stepping stone 
               for(int i = 0; i < start; i++) {
                  path = path.getNext();
                           
                  }
            //Check to see if the direction is negative
            } else if(start < 0) {
               //Moves backward through the references, stopping at the stepping stone
               for(int i = 0; i > start; i--) {
                  path = path.getPrevious();
               
                  }
               }
            //Changes start to the new direction
            start = path.getData().getDirection();
            
            //Appends the symbol in the current SteppingStone to answer 
         	if(path.getData().getSymbol() != null) {
               answer += path.getData().getSymbol();
               }
            }
         //returns the string representation
         return answer;
      }
   
   }
