   import scranton.visual.*;
   import java.awt.*;
   import javax.swing.*;
   import java.awt.event.*;

   public class SignalFlagTest {
   
      public static void main(String[] argv){
         VJFrame fr = new VJFrame("Signal Flag Test");
         SignalFlag flag = new SignalFlag(fr);

         flag.Y(20, 450, 25);
         flag.Y(80, 450, 50);
         flag.Y(160, 450, 75);
         flag.Y(280, 450, 100);
      
         flag.P(20, 300, 100);
         flag.P(140, 300, 75);
         flag.P(220, 300, 50);
         flag.P(280, 300, 25);
      	
         flag.G(450, 20, 100);
         flag.G(450, 140, 75);
         flag.G(450, 220, 50);
         flag.G(450, 280, 25);
      }
   
   }
