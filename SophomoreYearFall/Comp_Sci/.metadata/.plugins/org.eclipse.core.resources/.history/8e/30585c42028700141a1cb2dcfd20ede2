//Adam Miller
//Programming Assignment 5 Solution

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/** Java application that allows the user to play a simplified version of
**  TV's popular Wheel of Fortune gameshow.  An input file name is
**  specified, either via a command line argument or, absent that, by the
**  user in response to a program prompt.  The program assumes that each
**  line in the input file has a clue, followed by a colon and a space,
**  and then the phrase to be identified by the user.  (Each such phrase
**  is assumed to include only letters and spaces.)  For example, 
**
**  Movie Title: JURASSIC PARK
**  
**  For each line in the input file, the user gets to play a game.
**
**  A game proceeds as follows:
**
**  Initially, the phrase to be identified is displayed with each letter
**  replaced by an asterisk (*).  On each turn, the user gets to choose a
**  letter; the phrase is then displayed with each occurrence of that and
**  any previously-chosen letters shown.  Occurrences of not-yet-chosen
**  letters are masked as asterisks.
**
**  When the user has chosen every letter that appears in the phrase, the
**  complete phrase is displayed and the game has ended.  The program
**  reports how many turns the user needed to reveal the entire phrase.
*/

public class WheelOfFortuneR {

   private static final char COLON = ':';

   // for reading user input
   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      // Establish a Scanner that will read from the data file.
      Scanner input = makeScanner(args);

      // Each line in the input file contains a clue, followed by a 
      // colon, a space, and the phrase to be identified by the user
      // (e.g., "Song Title: STRANGERS IN THE NIGHT"). 
      while (input.hasNext())
      {
         String line = input.nextLine();  // read a line of input
         String clue = clueOf(line);      // extract the clue
         String phrase = phraseOf(line);  // extract the phrase
         play(clue, phrase);              // play a game
      }
   }

   /* Returns the prefix of the given string (s) up to but not including
   *  the first occurrence of a colon.  (This is the part of an input
   *  string that contains the "clue".)
   */
   private static String clueOf(String s)
   {
      String result = s.substring(0, s.indexOf(":"));  
      
      return result;
    }

   /* Returns the suffix of the given string (s) that begins two positions
   *  following the first occurrence of a colon.  (This is the part of an
   *  input string that contains the phrase to be discovered by the user.)
   */
   private static String phraseOf(String s)
   {
      String result = s.substring(s.indexOf(":") + 2, s.length()); 
    
      return result;
   }


   /* Plays a game where the clue and the phrase are as provided via the
   *  two arguments.
   */
   private static void play(String clue, String phrase)
   {
      final char ASTERISK = '*';
      final char SPACE = ' ';

      String charsToShow = "";
      int turnCntr = 0;

      print("\nNew game...\n");
      String maskedPhrase = mask(phrase, charsToShow+SPACE, ASTERISK);

      while (!phrase.equals(maskedPhrase))
      {
         print(clue + ": " + maskedPhrase + "\n");
         print("Enter new letter to expose (" + charsToShow + "): ");
         String guess = keyboard.nextLine();
         if(guess.length() >=2) {
         charsToShow += guessWord(phrase, guess);
         } else if (charsToShow.indexOf(guess) == -1) {
            charsToShow = guess + charsToShow;
         }
         else {
            print("You just wasted your turn, idiot!\n\n");
         }
         maskedPhrase = mask(phrase, charsToShow+SPACE, ASTERISK);
         turnCntr = turnCntr + 1;
      }
      print(clue + ": " + maskedPhrase + "\n\n");
      print("You solved it in " + turnCntr + " turns.\n");
   }


   /** Returns the String obtained from the given String (s) by replacing
   **  each occurrence of any character not occurring in visibleChars by
   **  hideChar.  For example, suppose that s is "MISSISSIPPI", visibleChars
   **  is "MRSQ", and hideChar is '*'.  Then the returned String will be
   **  "M*SS*SS****".  That is, each occurrence of any character
   **  other than 'M', 'R', 'S', and 'Q' was replaced by '*'.
   */
   private static String mask(String s, String visibleChars, char hideChar)
   {
      String result = "";
      visibleChars = visibleChars.toUpperCase() + visibleChars.toLowerCase();

      for(int i = 0; i < s.length(); i++)
      {
         char ch = s.charAt(i);
         
         int check = visibleChars.indexOf(ch);
         
         if(ch < 'A' || (ch > 'Z' && ch < 'a') || ch > 'z') {
         result += ch;
 
         } else if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')){         
         if(check == -1) {
         
            result += "*";
         
         } else if(check >= 0) {
 
            result += ch;
         
            }          
         }
      }

      return result;
   }
 
   //Checks to see if the users guess is right or not 
   private static String guessWord(String s, String guess) 
   {
   
   s = s.toUpperCase();
   guess = guess.toUpperCase();
   
   if (s.equals(guess)) { return s;}
   else { return "";}
   }


   /** Returns a Scanner that is connected to a file.
   **  If the given array has at least one element, the 0th one is
   **  taken to be the name of the file to which to connect.
   **  Otherwise, the user is prompted to enter the file name.
   */
   private static Scanner makeScanner(String[] commandLineArgs) throws IOException
   {
      String fileName;

      // Establish name of data file; if a command-line argument is present, 
      // use it, otherwise prompt user to enter it at the keyboard
      if (commandLineArgs.length > 0) {
         fileName = commandLineArgs[0];
      }
      else {
         print("Enter name of data file: ");
         fileName = keyboard.nextLine();
      }
      return new Scanner(new File(fileName)); 
   }


   /** This method is a surrogate for System.out.print().
   */
   private static void print(String s) {
      System.out.print(s);
   }

}
