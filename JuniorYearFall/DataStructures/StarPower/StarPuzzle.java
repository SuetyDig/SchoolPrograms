import java.io.*;
import java.util.*;
import java.awt.*;
import scranton.visual.*;


public class StarPuzzle 
{
   ArrayList<String> possibleWords = new ArrayList<String>();
   String[] solvedWords = new String[6];
   int rowHint;
   int positionHint;
   char letterHint;
   boolean solved = false;
      
   //Creates the StarPuzzle
   public void initStarPuzzle(String fileName) throws IOException
   {
      // Sets up a buffer and links the file to the buffer
      BufferedReader buffer = new BufferedReader(new FileReader(fileName));      
      int i = -1;
      String line1 = "";
      while (buffer.ready()) 
        {
            if(i < 0) 
            {
               line1 = buffer.readLine();
            } else if(i >= 0)
            {
               possibleWords.add(buffer.readLine());
            }
            i++;
        }
        rowHint = line1.charAt(0) - 48;        
        positionHint = line1.charAt(2) - 48; 
        letterHint = line1.charAt(4);
   }
   
   //Solves the StarPuzzle
   public void solve()
   { 
     for(int h = 0; !solved; h++) 
     {
         solvedWords[0] = possibleWords.get(h);
         for(int i = 0; i < possibleWords.size() && !solved; i++)
         {
            ArrayList<String> possibleWords1 = new ArrayList<String>(possibleWords);
            for(int a = 0; a < possibleWords1.size(); a++) if(possibleWords1.get(a) == solvedWords[0]) possibleWords1.remove(a);

            solvedWords[1] = possibleWords1.get(i);
            for(int j = 0; j < possibleWords1.size() - 1 && !solved; j++)
            {
               ArrayList<String> possibleWords2 = new ArrayList<String>(possibleWords1);
               
               for(int a = 0; a < possibleWords2.size(); a++) if(possibleWords2.get(a) == solvedWords[1]) possibleWords2.remove(a);

               solvedWords[2] = possibleWords2.get(j);
               for(int k = 0; k < possibleWords2.size() - 1 && !solved; k++)
               {
                  ArrayList<String> possibleWords3 = new ArrayList<String>(possibleWords2);
                  
                  for(int a = 0; a < possibleWords3.size(); a++) if(possibleWords3.get(a) == solvedWords[2]) possibleWords3.remove(a);

                  solvedWords[3] = possibleWords3.get(k);
                  for(int l = 0; l < possibleWords3.size() - 1 && !solved; l++)
                  {
                     ArrayList<String> possibleWords4 = new ArrayList<String>(possibleWords3);
                     
                     for(int a = 0; a < possibleWords4.size(); a++) if(possibleWords4.get(a) == solvedWords[3]) possibleWords4.remove(a);

                     solvedWords[4] = possibleWords4.get(l);
                     for(int m = 0; m < possibleWords4.size() - 1 && !solved; m++)
                     {
                        ArrayList<String> possibleWords5 = new ArrayList<String>(possibleWords4);
                        
                        for(int a = 0; a < possibleWords5.size(); a++) if(possibleWords5.get(a) == solvedWords[4]) possibleWords5.remove(a);

                        solvedWords[5] = possibleWords5.get(m);
                        solved = solution(); 
                     } 
                  } 
               } 
            }
         }
      }
   }
   
   private ArrayList<String> possibleStartingWords(int pos, char let)
   {
      ArrayList<String> possWords = new ArrayList<String>();
      for(int i = 0; i < possibleWords.size(); i++)
      {
         if(possibleWords.get(i).charAt(pos - 1) == let) possWords.add(possibleWords.get(i));
      }
      return possWords;
   }
   
   private boolean solution()
   {
      boolean answer = false;
   
      if(solvedWords[0].charAt(0) == solvedWords[4].charAt(6) &&
         solvedWords[0].charAt(2) == solvedWords[2].charAt(4) &&
         solvedWords[0].charAt(4) == solvedWords[5].charAt(4) &&
         solvedWords[0].charAt(6) == solvedWords[3].charAt(6) &&
         solvedWords[1].charAt(0) == solvedWords[2].charAt(0) &&
         solvedWords[1].charAt(2) == solvedWords[4].charAt(2) &&
         solvedWords[1].charAt(4) == solvedWords[3].charAt(2) &&
         solvedWords[1].charAt(6) == solvedWords[5].charAt(0) &&
         solvedWords[2].charAt(2) == solvedWords[4].charAt(4) &&
         solvedWords[2].charAt(6) == solvedWords[5].charAt(6) &&
         solvedWords[3].charAt(0) == solvedWords[4].charAt(0) &&
         solvedWords[3].charAt(4) == solvedWords[5].charAt(2) &&  
         solvedWords[rowHint-1].charAt(positionHint-1) == letterHint) answer = true;
   
      return answer;
   }
   
   public String toString()
   {
      String print = "";
      
      for(int i = 0; i < 6; i++)
      {
         print += solvedWords[i] + "\n";
      }
      
      return print;
   }
   
   public void drawSolution()
   {
      VJFrame frame = new VJFrame("Star Power");
      
      //Draws rectangles
      for(int i = 0; i < solvedWords[0].length(); i++)
      {
         JRectangle rect = new JRectangle(205 + i*30, 36 + i*47, 40, 40, Color.black, Color.white);
         frame.add(rect, 0);
      }
      for(int i = 0; i < solvedWords[1].length(); i++)
      {
         JRectangle rect = new JRectangle(25 + i*30, 130 + i*47, 40, 40, Color.black, Color.white);
         frame.add(rect, 0);
      }      
      for(int i = 0; i < solvedWords[2].length(); i++)
      {
         JRectangle rect = new JRectangle(25 + i*60, 130, 40, 40, Color.black, Color.white);
         frame.add(rect, 0); 
      }
      for(int i = 0; i < solvedWords[3].length(); i++)
      {
         JRectangle rect = new JRectangle(25 + i*60, 318, 40, 40, Color.black, Color.white);
         frame.add(rect, 0); 
      }
      for(int i = 0; i < solvedWords[4].length(); i++)
      {
         JRectangle rect = new JRectangle(25 + i*30, 318 - i*47, 40, 40, Color.black, Color.white);
         frame.add(rect, 0); 
      }
      for(int i = 0; i < solvedWords[5].length(); i++)
      {
         JRectangle rect = new JRectangle(205 + i*30, 412 - i*47, 40, 40, Color.black, Color.white);
         frame.add(rect, 0);
      }
      
      //Draws Words
      for(int i = 0; i < solvedWords[0].length(); i++)
      {
         JString string = new JString(218 + i*30, 65 + i*47, solvedWords[0].substring(i,i+1), Color.black);
         frame.add(string, 0);
      }
      for(int i = 0; i < solvedWords[1].length(); i++)
      {
         JString string = new JString(38 + i*30, 159 + i*47, solvedWords[1].substring(i,i+1), Color.black);
         frame.add(string, 0);
      }      
      for(int i = 0; i < solvedWords[2].length(); i++)
      {
         JString string = new JString(38 + i*60, 159, solvedWords[2].substring(i,i+1), Color.black);
         frame.add(string, 0); 
      }
      for(int i = 0; i < solvedWords[3].length(); i++)
      {
         JString string = new JString(38 + i*60, 347, solvedWords[3].substring(i,i+1), Color.black);
         frame.add(string, 0); 
      }
      for(int i = 0; i < solvedWords[4].length(); i++)
      {
         JString string = new JString(38 + i*30, 347 - i*47, solvedWords[4].substring(i,i+1), Color.black);
         frame.add(string, 0); 
      }
      for(int i = 0; i < solvedWords[5].length(); i++)
      {
         JString string = new JString(218 + i*30, 441 - i*47, solvedWords[5].substring(i,i+1), Color.black);
         frame.add(string, 0);
      }

   }
}