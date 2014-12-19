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

/**
* An array implementation of the Hunt interface
*
*/
    public class ArrayListHunt implements Hunt{
      private ArrayList<SteppingStone> path = new ArrayList<SteppingStone>();
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
         	// Place the stone in the arraylist
            path.add(Stone);
         }
      }
   
       public String toString(){
         String answer = "";
      	// for each SteppingStone
         for(int i=0; i < this.path.size(); i++){
         	// Place string representation of first SteppingStone into the answer
            if(answer == "") answer = this.path.get(i).toString();
            // Append all other SteppingStones with a dash in between
            else answer += " - "+this.path.get(i).toString();
         }
      	// Return the string representation
         return answer;
      }
   
       public String hunt(){
          String answer = "";
         // Creates a check variable used to make sure the program has all Symbols
         int check = start;
         /* Used to append answer with all the symbols from stepping stone 
         ** check is there to make sure the program doesn't terminate early
         */ 
         while (path.get(start).getDirection() != 0 || check != start) {
            // Appends the symbol from SteppingStone into the answer
            answer += path.get(start).getSymbol();
            // Assigns the value of start to the check value
            check = start;
            // Changes the value of start to where the next symbol would be found
            start += path.get(start).getDirection();      
            }
         // Return the string representation 
         return answer;
      }
  
   }
