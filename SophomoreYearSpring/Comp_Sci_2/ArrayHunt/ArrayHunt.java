/*
NAME: Adam Miller
*/
   import java.io.*;
   import java.util.*;

/**
* An array implementation of the Hunt interface
*
*/
    public class ArrayHunt implements Hunt{
      private SteppingStone[] path = new SteppingStone[150];
      private int size;
      private int start;
   	
       public void initialize(String fileName) throws IOException {
       	// Sets up a buffer and links the file to the buffer
         BufferedReader buffer = new BufferedReader(new FileReader(fileName));
      	// Reads the first line, which contains a number, translate the string to a number and places it in start
         this.start = Integer.valueOf(buffer.readLine()).intValue();
      	// index keep track of the array position being filled
         int index = 0;
      	// Loop until the buffer runs out of records
         while (buffer.ready()){
         	// Place the buffer contents into a string tokenizer using the tab token
            StringTokenizer token = new StringTokenizer(buffer.readLine(), "\t");
         	// first token is a char, second in an int, use them to construct a SteppingStone
           SteppingStone Stone = new SteppingStone(token.nextToken(), new Integer(token.nextToken()));
         	// Place the stone in the array
            this.path[index] = Stone;
         	// increment the array index
            index++;
         }
      	// index is the count of the number of SteppingStones placed in the path
         this.size = index;
      }
   
       public String toString(){
         String answer = "";
      	// for each SteppingStone
         for(int i=0; i < this.size; i++){
         	// Place string representation of first SteppingStone into the answer
            if(answer == "") answer = this.path[i].toString();
            // Append all other SteppingStones with a dash in between
            else answer += " - "+this.path[i].toString();
         }
      	// Return the string representation
         return answer;
      }
   
       public String hunt(){
         String answer = "";
         //Creates a check variable used to make sure the program has all Symbols
         int check = start;
         /* Used to append answer with all the symbols from stepping stone 
         ** check is there to make sure the program doesn't terminate early
         */ 
         while (path[start].getDirection() != 0 || check != start) {
            //Appends the symbol from SteppingStone into the answer
            answer += path[start].getSymbol();
            //Assigns the value of start to the check value
            check = start;
            //Changes the value of start to where the next symbol would be found
            start += path[start].getDirection();      
      }
         //Return the string representation 
         return answer;
      }
   
   }