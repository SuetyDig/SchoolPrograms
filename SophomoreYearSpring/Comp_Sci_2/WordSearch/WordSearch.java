   import java.util.*;
   import java.io.*;
	
   import java.awt.*;
   import scranton.visual.*;

   public class WordSearch {
   
      public WSViewer F;
      //private int dx = 30;
      //private int dy = 30;
      private int timing = 10;
      private int matrixWidth;
      private int wrdCnt = 0;
      private int row = 1;
      private int deltaX;
      public String [][] mat;
   	
      ArrayList<String> word;
   	
      Hashtable<String, ArrayList<Coordinate>> H = new Hashtable<String, ArrayList<Coordinate>>();
    
      public WordSearch(WSViewer F){
         this.F = F;
      }
   
      public void initialize(int rows, int cols, String fileName) throws IOException {
         mat = new String[rows][cols];
         matrixWidth = cols;
         //deltaX = this.dx*(3+matrixWidth);
         BufferedReader buffer = new BufferedReader( new FileReader(fileName));
         String s;
         for (int i = 0; i < rows; i++) {
            s = buffer.readLine();
            for (int j = 0; j < cols; j++) {
               mat[i][j] = s.substring(j, j+1);
               if(!H.containsKey(mat[i][j])){
                  H.put(mat[i][j], new ArrayList<Coordinate>());
               }
               H.get(mat[i][j]).add(new Coordinate(i, j));
            }
         }
      }
   
      public void display(){
         for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat[i].length; j++){
               System.out.print(mat[i][j]+" ");
            	//changeCell(i, j, Color.white, mat[i][j], Color.black, 50);
					F.cellBack(i, j, Color.black, Color.white);
               //F.add(new JRectangle(dx*j, dy*i, dx, dy, Color.black, Color.white), timing);
					F.cellContent(i, j, mat[i][j], Color.black);
               //F.add(new JString(dx*j+5, dy*i+25, mat[i][j], Color.black), timing);
            }
            System.out.println();
            F.cellContent(i, 17, Integer.toString(i), Color.white);
         }
         for(int j=0; j<mat[0].length; j++){
            F.cellContent(17, j, Integer.toString(j), Color.white);
         }
      }
   
      public boolean match(int row, int col, String word, Direction d){
      
         String check = "" + word.charAt(0);
         int index = 1;
         while (index < word.length()) {
            if (mat[row + d.deltaRow][col + d.deltaCol].equals(word.substring(index, index+1)) ) {
               index++;
               check = check + mat[row + d.deltaRow][col + d.deltaCol];
               row = row + d.deltaRow;
               col = col + d.deltaCol;
            }             
            else {
               return false;
            };
         }
         if (check.equalsIgnoreCase(word)) {
            return true;
         } 
         else {
            return false;
         }
      }
/**   	
      public void changeCell(Integer row, Integer col, Color background, String symbol, Color foreground, Integer time){
         F.add(new JRectangle(dx*col, dy*row, dx, dy, Color.black, background), time);
         F.add(new JString(dx*col+5, dy*row+25, symbol, foreground), time);
      }
*/   
      public void displayWord(int row, int col, String word, Direction d, Color color){
      
			//F.cellBack(row, col, Color.black, color);
			//F.cellContent(row, col, mat[row][col], Color.black);
         for(int index = 0; index < word.length(); index++){
				F.cellBack(row, col, Color.black, color);
				F.cellContent(row, col, mat[row][col], Color.black);
            row = row + d.deltaRow;
            col = col + d.deltaCol;
         }
      	
         try {
            Thread.sleep(2000);
         } 
            catch (Exception e) {}
      	
         for(int index = 0; index < word.length(); index++){
            row = row - d.deltaRow;
            col = col - d.deltaCol;
				F.cellBack(row, col, Color.black, Color.white);
				F.cellContent(row, col, mat[row][col], Color.black);
         }
      
      }
   	
      public void getWords(String fileName) throws IOException {
         this.word = new ArrayList<String>();
         BufferedReader buf = new BufferedReader(new FileReader(fileName));
         while(buf.ready()){
            this.word.add(buf.readLine());
         }
      }
   	
      synchronized public String getWord(Color color){
      
         if(this.word.size() == 0) 
            return "";
         else {
/**         	
            F.add(new JString(deltaX, dy*row, this.word.get(0), color), timing);
            wrdCnt++;
            if(wrdCnt%2 == 0){
               deltaX = this.dx*(3+matrixWidth);
               row++;
            } 
            else {
               deltaX+=150;
            }
*/         	F.searchFor(this.word.get(0), color);
            return this.word.remove(0);
         }
      }
   
   }