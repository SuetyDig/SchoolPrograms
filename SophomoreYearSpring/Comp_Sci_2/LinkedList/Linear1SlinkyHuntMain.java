   import java.io.*;

/**
* Main program to drive the Linear2Hunt implementation of the Hunt interface.
*
* @author J. Beidler
*/
    class Linear1SlinkyHuntMain {
   
       public static void main(String[] argv) throws IOException{
         Hunt H = new Linear1SlinkyHunt();
         H.initialize("hunt_tokens.txt");
         System.out.println(H.toString());
         System.out.println(H.hunt());
      }
   
   }