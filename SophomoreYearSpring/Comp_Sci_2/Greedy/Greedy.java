   import java.io.*;
   import java.util.*;
   import java.awt.*;
   import javax.swing.*;
   import java.awt.event.*;
   import scranton.visual.*;

   public class Greedy{
   
      Grapher G;
      JMap M;
      VJFrame frame;
      Color[] C = {Color.white, Color.red, Color.yellow, Color.green, Color.blue};
      Stack<NodeInfo> Valid, Working;
   	
      public Greedy(Grapher G, JMap M){
         this.G = G;
         this.M = M;
         this.frame = new VJFrame("Greedy Algorithm on U.S. Map");
         Valid = new Stack<NodeInfo>();
         Working = new Stack<NodeInfo>();
      
         ArrayList<String> Seq = G.getSeq();
         for(Integer i = Seq.size()-1; i >= 0; i--){
            String id = Seq.get(i);
            System.out.println(id);
            NodeInfo N = G.refNode(id);
            Working.push(N);
            N.setColor(0);
            M.displayElement(N.getName(), C[N.getColor()], frame);
         }
      
      }
   	
      public void go(){
         Integer counter = 0;
         Integer backup = 0;
         
         //used to change color
         int color = 1;
         while(!Working.empty()){
            // Work on the item at the top of the working stack
            NodeInfo Current = Working.peek(); 
            
            // Checks if color is past the enumeration, if it is sets color back to 1(red)
            if(color > 4) color = 1;
            
            // Sets and displays the current color            
            Current.setColor(color);
            M.displayElement(Current.getName(), C[Current.getColor()], frame);
            
            // Loop used to cycle through all four colors 
            for(int inc = 0; inc < 4; inc++) {
               // Checks if the current states color is valid
               if(!Current.validColoring()) {
                  // Checks if color is past the enumeration, if it is sets color back to 1(red)
                  if(color > 4) color = 1;
                  
                  // Sets and displays the current color
                  Current.setColor(color);
                  M.displayElement(Current.getName(), C[Current.getColor()], frame);
                  
                  // Changes the current color
                  color++;
                  
                  // Used to get previous state if all four colors don't work
                  counter++;
                  
                  // Used to get the state two states before this one if all four colors don't work
                  // And the colors for the state found using counter don't work
                  backup++;
                  }
            }
            if(!Current.validColoring()) {
               // If the coloring isn't valid it pops a state off of the valid stack
               // And onto the working stack
                Working.push(Valid.pop());
                
                // Checks if the counter is greater then the amount of colors
                // If it is it pushes another state onto the working stack and resets the counter
                if(counter > 4) {
                  Working.push(Valid.pop());
                  counter = 0;                  
                  // Checks if backup is greater then the amount of colors for two states.
                  // If it is it pushes another state onto the working stack and resets the counter
                  if(backup > 8) {
                     Working.push(Valid.pop());
                     backup = 0;
                     }
                  }
               } 
            if(Current.validColoring()){     
                 // Pushes colored map off working and into the valid pile
                 Valid.push(Working.pop());
               }
            // Changes the current color          	
            color++;
          }
   	}
   }
