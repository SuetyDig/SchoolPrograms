   import java.io.*;

/**
* Main program to drive the ArrayHunt implementation of the Hunt interface.
*
* @author J. Beidler
*/
    class ArrayHuntMain {
   
       public static void main(String[] argv) throws IOException{
         Hunt H = new ArrayHunt();
         H.initialize("hunt_tokens.txt");
         System.out.println(H.toString());
         System.out.println(H.hunt());
      }
   
   }