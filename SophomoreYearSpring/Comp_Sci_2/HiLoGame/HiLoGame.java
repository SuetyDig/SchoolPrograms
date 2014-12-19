   import java.util.*;
   import javax.swing.*;
	

    public class HiLoGame {
    
       public static void HiLo(){
         Random r = new Random();
         int Lo = 1;
         int Hi = 100;
         int Target = r.nextInt(Hi-Lo+1)+1; 
         System.out.println(Target);
         int NoOfGuesses = 0;
         int Guess = -1;
         String Title = "Hi Lo Game";
         String data = JOptionPane.showInputDialog(null, "The number is between "+Lo+" and "+Hi+"\nMake your guess.", Title, 1);
         Guess = new Integer(data);
         while((Guess!=Target)&&(NoOfGuesses!=8)){
            //Situation where the guess was below Lo, making it outside the bounds
            if(Guess != Target){
               if(Guess < Lo){
                  //check to change guesses to be either singular or plural
                  if (NoOfGuesses == 7)
                     data = JOptionPane.showInputDialog(null, "The number you entered was below "+Lo+". \nThe range is stil "+Lo+" to " +Hi+"\nYou have "+(8-NoOfGuesses)+
                     " guess remaining. Please try again", Title, 1);                  
                  else
                                          data = JOptionPane.showInputDialog(null, "The number you entered was below "+Lo+". \nThe range is stil "+Lo+" to " +Hi+"\nYou have "+(8-NoOfGuesses)+
                     " guesses remaining. Please try again", Title, 1);                  
                  
               } 
               //Situation where yhe guess was above Hi, making it outside the bounds
               else if (Guess > Hi){
               //check to change guesses to be either singular or plural
                  if (NoOfGuesses == 7)
                     data = JOptionPane.showInputDialog(null, "The number you entered was above "+Hi+".\nThe range is still "+Lo+" to " +Hi+"\nYou have "+(8-NoOfGuesses)+ 
                     " guess remaining. Please try again", Title, 1);
                  else
                     data = JOptionPane.showInputDialog(null, "The number you entered was above "+Hi+".\nThe range is still "+Lo+" to " +Hi+"\nYou have "+(8-NoOfGuesses)+ 
                     " guesses remaining. Please try again", Title, 1);

               } 
               //Situation where the guess was below the target but above Lo
               else if (Guess < Target){
                  Lo = Guess;                  
                  //check to change guesses to be either singular or plural
                  if(NoOfGuesses == 7) 
                     data = JOptionPane.showInputDialog(null, "The number you entered was below the target.\nThe range is now "+Lo+" to " +Hi+"\nYou have "+(8-NoOfGuesses)+
                     " guess remaining. Please try again", Title, 1);                  
                  else
                     data = JOptionPane.showInputDialog(null, "The number you entered was below the target.\nThe range is now "+Lo+" to " +Hi+"\nYou have "+(8-NoOfGuesses)+
                     " guesses remaining. Please try again", Title, 1);
                 
               }
               //Situation where the guess was above the target but below Hi
               else {
                  Hi = Guess;
                  //check to change guesses to be either singular or plural
                  if(NoOfGuesses == 7) 
                     data = JOptionPane.showInputDialog(null, "The number you entered was above the target.\nThe range is now "+Lo+" to " +Hi+"\nYou have "+(8-NoOfGuesses)+ 
                     " guess remaining. Please try again", Title, 1);
                  else
                     data = JOptionPane.showInputDialog(null, "The number you entered was above the target.\nThe range is now "+Lo+" to " +Hi+"\nYou have "+(8-NoOfGuesses)+ 
                     " guesses remaining. Please try again", Title, 1);
               }
            }
            NoOfGuesses++;
            Guess = new Integer(data);
         }
         if(Guess == Target) {
         NoOfGuesses++; //Incrementing because otherwise the game says you won in 1 less move
                  //check to change guesses to be either singular or plural
                  if (NoOfGuesses == 1) {
                     JOptionPane.showMessageDialog(null, "Congratulations, you won in "+NoOfGuesses+ " guess");
                  } else
                     JOptionPane.showMessageDialog(null, "Congratulations, you won in "+NoOfGuesses+ " guesses");                  
   
         } else
                  JOptionPane.showMessageDialog(null, "You have exceeded the number of guesses. The target was "+Target);


      }
   
       public static void main(String argv[]){
         String[] commands = {
               "Play Hi Lo",
               "Exit"
               };
         int choice = -1;
         String Title = "Play The Hi Lo Game";
         choice = JOptionPane.showOptionDialog(
            null,
            "Do you want to play Hi Lo against the computer?",
            Title,
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            commands,
            commands[commands.length-1]
            );
         while(choice != commands.length-1) {
            HiLo();
            choice = JOptionPane.showOptionDialog(
               null,
               "Do you want to play Hi Lo against the computer?",
               Title,
               JOptionPane.YES_NO_CANCEL_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               null,
               commands,
               commands[commands.length-1]
               );
         }
      }
   	 
   }
