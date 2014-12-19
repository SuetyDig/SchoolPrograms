   import java.io.*;

/**
* Main program to drive the Linear2Hunt implementation of the Hunt interface.
*
* @author J. Beidler
*/
    class Linear2HuntMain {
   
       public static void main(String[] argv) throws IOException{
         Hunt H = new Linear2Hunt();
         H.initialize("hunt_tokens.txt");
         System.out.println(H.toString());
         System.out.println(H.hunt());
      }
   
   }