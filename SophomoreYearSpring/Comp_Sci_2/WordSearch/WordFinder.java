   import java.util.*;
   import java.io.*;
	
   import java.awt.*;
   import scranton.visual.*;

   public class WordFinder extends Thread{
   
      WordSearch W;
		WSViewer F;
      Color color;
   
      public WordFinder(WordSearch W, WSViewer F, Color c){
         this.W = W;
			this.F = F;
         this.color = c;
      }
   	
      public void run(){
         String word = W.getWord(color);
         while (!word.equals("")){
         
            boolean found = false;
            Direction d;
         	
            ArrayList<Coordinate> L = W.H.get(word.substring(0,1));
         	
            for(int c=0; (c<L.size()) && (!found); c++){
               int i = L.get(c).r;
               int j = L.get(c).c;
            	
            
               for(Directions Dir=new Directions();(!found) && (Dir.hasNext());){
                  d = (Direction)Dir.next();
                  found = W.match(i, j, word, d);
                  if(found){
                     W.displayWord(i, j, word, d,color);
                     System.out.println(word+" found at ("+i+","+j+") heading "+d.getName());
                  }
               }
            	//displays the word
               F.cellBack(i, j, Color.black, color);
               //undisplays the word
               F.cellContent(i, j, W.mat[i][j], Color.black);
            }
            
            
            if(!found)
               System.out.println(word+" not found");
         
            word = W.getWord(color);
         }
      }
   	
   
   }
