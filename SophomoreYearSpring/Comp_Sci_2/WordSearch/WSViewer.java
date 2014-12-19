   import java.io.*;
   import scranton.visual.*;
   import java.awt.*;

   public class WSViewer extends VJFrame {
   
      private int dx = 30;
      private int dy = 30;
      private int timing = 10;
      private int matrixWidth;
      private int rows = 17;
      private int cols = 17;

		private int wrow = 2;   
   	private int deltaX = dx*(20);
		private int wrdCnt = 0;
		
      public WSViewer (String title){
         super (title);
         matrixWidth = cols;
      }
   	
      public void cellBack(int row, int col, Color border, Color fore){
         add(new JRectangle(dx*col, dy*row, dx, dy, border, fore), timing);
      }
   	
      public void cellContent(int row, int col, String info, Color fore){
         add(new JString(dx*col+5, dy*row+25, info, fore), timing);
      }
		
		public void searchFor(String word, Color color){

            this.add(new JString(deltaX, dy*wrow, word, color), timing);
            wrdCnt++;
            if(wrdCnt%2 == 0){
               deltaX = this.dx*(3+matrixWidth);
               wrow++;
            } 
            else {
               deltaX+=150;
            }

		}
   		
   }