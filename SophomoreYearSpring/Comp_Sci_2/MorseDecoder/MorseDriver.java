   import java.io.*;
   import java.util.*;

    public class MorseDriver{
   
       public static void main(String[] argv) throws IOException{
         Morse M = new Morse();
         M.initialize("morse.txt");
         M.verify("");
      	
         BufferedReader buf = new BufferedReader(new FileReader("MorseMessage.txt"));
         while(buf.ready()){
            StringTokenizer message = new StringTokenizer(buf.readLine(), "/");
            while(message.hasMoreTokens()){
               String code = message.nextToken();
            	//System.out.println(code);
               if(code.equals(" ")) System.out.print(" ");
               else System.out.print(M.translate(code));
            }
            System.out.println();
         }
      	
      }
   }