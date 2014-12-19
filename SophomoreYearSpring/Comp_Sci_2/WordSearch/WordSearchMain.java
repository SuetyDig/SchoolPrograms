   import java.io.*;
	import scranton.visual.*;
	import java.awt.*;

   public class WordSearchMain{
   
      public static void main(String [] argv) throws IOException {
         int rows = 17;
         int cols = 17;
         String [][] matrix = new String[rows][cols];
			WSViewer VWS = new WSViewer("Word Search");
         WordSearch W = new WordSearch(VWS);
      	
         W.initialize(rows, cols, "matrixB.txt");
      
         W.display();
			
			W.getWords("words.txt");
      
         WordFinder WF1 = new WordFinder(W, VWS, Color.red);
			WF1.start();
      
         WordFinder WF2 = new WordFinder(W, VWS, Color.pink);
			WF2.start();
      
         WordFinder WF3 = new WordFinder(W, VWS, Color.green);
			WF3.start();
      
         WordFinder WF4 = new WordFinder(W, VWS, Color.yellow);
			WF4.start();
      
         WordFinder WF5 = new WordFinder(W, VWS, Color.blue);
			WF5.start();
      }
   
   }