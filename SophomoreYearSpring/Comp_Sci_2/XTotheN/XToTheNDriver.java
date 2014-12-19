   import java.io.*;

   import java.util.*;

    class XToTheNDriver{
   
       public static void main(String[] args) throws IOException{
         XN xn = new XN();
         double x;
         int n;
      
         x = 2.0;
         n = 8;
         xn.reset();
         System.out.print("x = "+x+"\tn = "+n+"\t"+xn.XToTheN(x, n));
         System.out.println("\tnumber of calls = "+xn.viewCounter());
      	
      
         x = 3.0;
         n = 9;
         xn.reset();
         System.out.print("x = "+x+"\tn = "+n+"\t"+xn.XToTheN(x, n));
         System.out.println("\tnumber of calls = "+xn.viewCounter());
         
         
         x = 3.0;
         n = 0;
         xn.reset();
         System.out.print("x = "+x+"\tn = "+n+"\t"+xn.XToTheN(x, n));
         System.out.println("\tnumber of calls = "+xn.viewCounter());
         
         x = 0;
         n = 9;
         xn.reset();
         System.out.print("x = "+x+"\tn = "+n+"\t"+xn.XToTheN(x, n));
         System.out.println("\tnumber of calls = "+xn.viewCounter());
         
         x = 5.0;
         n = 20;
         xn.reset();
         System.out.print("x = "+x+"\tn = "+n+"\t"+xn.XToTheN(x, n));
         System.out.println("\tnumber of calls = "+xn.viewCounter());
      
         x = 3.0;
         n = 30;
         xn.reset();
         System.out.print("x = "+x+"\tn = "+n+"\t"+xn.XToTheN(x, n));
         System.out.println("\tnumber of calls = "+xn.viewCounter());
      	// add some other test to check your your XN class
      
         x = 2.0;
         n = 1;
         for (int i=0; i<10; i++){
            xn.reset();
            System.out.print("x = "+x+"\tn = "+n+"\t"+xn.XToTheN(x, n));
            System.out.println("\tnumber of calls = "+xn.viewCounter());
            n*=2;
         }
      }
   
   }