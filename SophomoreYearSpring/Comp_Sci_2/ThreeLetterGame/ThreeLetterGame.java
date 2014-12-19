   import java.io.*;
   import java.util.*;	
   import java.awt.*;

   public class ThreeLetterGame   {
   static ArrayList<String> wordsUsed = new ArrayList<String>();
      public static void main(String [] argv) throws IOException {      
         // Creates an array to hold all the valid words
         ArrayList<String> wordList = new ArrayList<String>();
         BufferedReader reader = new BufferedReader(new FileReader("threeLetterWords.txt"));
         while(reader.ready()) {
            wordList.add(reader.readLine());
         }
         
         // Creates a scanner to accept user input
         Scanner input = new Scanner(System.in);
         
         // Gets the starting word;
         System.out.print("Please enter the 3 letter word to start with: ");
         String start = input.nextLine();
         while(!validWord(wordList, start)) {
            System.out.println("Not a valid 3 letter word please try again");
            start = input.nextLine();
         }
         wordsUsed.add(start);
         
         System.out.print("Please enter the 3 letter word you want to end with: ");
         String end = input.nextLine();
         while(!validWord(wordList, end)) {
            System.out.println("Not a valid 3 letter word please try again");
            end = input.nextLine();
         }
         int move = 0;
         int counter = 0;
         String word;
         int didSwap = 0;
         while(!start.equalsIgnoreCase(end)) {
            move++;
            word = end.charAt(0) + start.substring(1, start.length());
            if(validWord(wordList, word) && !start.equalsIgnoreCase(word) && didSwap != 1) {
               start = word;
               wordsUsed.add(start); 
            } else {
               word = start.substring(0,1) + end.charAt(1) + start.charAt(2);
               if(validWord(wordList, word) && !start.equalsIgnoreCase(word) && didSwap != 2) {
                  start = word;
                  wordsUsed.add(start);
               } else {
                  word = start.substring(0, 2) + end.charAt(2);
                  if(validWord(wordList, word) && !start.equalsIgnoreCase(word)) {
                     start = word;
                     wordsUsed.add(start);
                  }
               }
            }
            if(counter == 3 && didSwap == 0) {
               didSwap++;
               start = shuffle(wordList, start, 1);
               wordsUsed.add(start);
            }else if(counter == 6) {
               didSwap++;
               start = shuffle(wordList, start, 2);
               wordsUsed.add(start);
            }else if(counter == 9) {
               didSwap = 0;
               counter = 0;
               start = shuffle(wordList, start, 3);
               wordsUsed.add(start);
            }
            counter++;
            System.out.println(start);
         }
         System.out.println("It got to " + end + " in " + move + " move(s)");
      }
      
      private static boolean validWord(ArrayList<String> wordList, String word) {
         for(int i = 0; i < wordList.size(); i++) {
            if(wordList.get(i).equalsIgnoreCase(word)) return true;
         }
         return false;
      }
      private static boolean isDone(String start, String end) {
         if(start.equalsIgnoreCase(end)) return true;
         else return false;
      }
      
      private static String shuffle(ArrayList<String> wordList, String shuffle, int place) {
         int cap = 0;
         int tries = 0;
         for(int i = 0; !validWord(wordList, shuffle) || wordsUsed.contains(shuffle); i++) {
            char c = shuffle.charAt(place - 1);
            if(c == 'z' || c == 'Z') c = 'a';
            else c++;
            cap++;
            if(place == 1) {
               shuffle = c + shuffle.substring(1,shuffle.length());
            } else if(place == 2) {
               shuffle = shuffle.substring(0,1) + c + shuffle.charAt(2);
            } else if(place == 3) {
               shuffle = shuffle.substring(0,2) + c;
            }
            if(cap >= 26) {
               if(place == 3) place = 1;
               else place++;
               cap = 0;
               tries++;
               if(tries == 3) {
                  System.out.println("Impossible to find word. Now exiting program");
                  System.exit(0);
               } 
            }
         }
         return shuffle;
      }
   }