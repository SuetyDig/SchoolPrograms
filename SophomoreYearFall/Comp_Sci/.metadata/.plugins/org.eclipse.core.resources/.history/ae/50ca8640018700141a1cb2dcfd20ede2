/* Adam Miller
** Programming Assignment 2
** The only flaw that happens is if the user enters something that isn't an
** integer into the number of coins tossed part and essentially samething for when
** the user enters anything that isn't a double when the probability is asked for
** I didn't fix this because 
*/

import java.util.Scanner; // for reading input data

/** Java application that simulates the tossing of two coins, one of which
**  is fair and the other of which is biased so that each toss has a 75%
**  probability of resulting in heads.
**  The user enters, in response to a prompt, the number of times that each
**  coin is to be tossed.  The fair coin is tossed that many times, with
**  each toss's result being reported (by the display of either 'H' or 'T').
**  The program then displays a message indicating the percentage of tosses
**  that resulted in heads.  This procedure is then repeated with the biased
**  coin.
*/

public class TossableCoinApp {

   public static void main(String[] args)
   {
      // Establish Scanner object to interpret input entered at keyboard.
      Scanner keyboard = new Scanner(System.in);

      // Prompt user to enter input regarding # of tosses to make
      System.out.print("Enter # times to toss each coin: ");

      // Read user's response and store in a variable
      int numTosses = keyboard.nextInt();

      // Create a fair TossableCoin and store a reference to it in a variable.
      TossableCoin fairCoin = new TossableCoin();

      // Display message introducing output describing tosses of fair coin.
      System.out.println("Fair coin tosses: ");

      // Toss the fair coin the # of times specified by the user,
      // reporting the result ('H' or 'T') of each toss.
      for (int i=0; i != numTosses; i=i+1)
      {
         // Toss the coin.
         fairCoin.toss();

         // Display the result.
         System.out.print(fairCoin.faceShowing());
      }

      // Skip to next line;
      System.out.println();

      // Calculate and report percentage of tosses that resulted in heads
      double probabilityFairCoin = (double) fairCoin.headsCounter() / numTosses;
		System.out.println("The probability of getting heads is: "+ probabilityFairCoin);
	
      // Skip to next line;
      System.out.println();
		
		//Sets how biased you want the coin to be. 
		double coinBias = 0.5;

		System.out.print("Enter how biased you want the coin to be" 
										+"(in between 0 and 1; where 25% would be "
										+ "represented as .25): ");
		
		coinBias = keyboard.nextDouble();
		
		//If the user enters an invalid number, the user gets asked to try again
		do 
		{
			if (0 <= coinBias && coinBias <= 1); {
							} if(coinBias < 0 || coinBias > 1) {
				System.out.println("Wrong Value,(remember: the number should"
										+ " be in between 0 and "
										+ "1; where 25% would be represented as .25): ");
				coinBias = keyboard.nextDouble();
			}
		} while (coinBias < 0 || coinBias > 1); 
		 // Create a TossableCoin on which the probability of tossing
      TossableCoin biasedCoin = new TossableCoin(coinBias);

      // Display message introducing output describing tosses of biased coin.
      System.out.println("Biased coin tosses: ");

      // Toss the biased coin the # of times specified by the user,
      // reporting the result ('H' or 'T') of each toss.
      for (int i=0; i != numTosses; i=i+1)
      {
         // Toss the coin.
         biasedCoin.toss();

         // Display the result.
         System.out.print(biasedCoin.faceShowing());
      }

      // Skip to next line;
      System.out.println();

      // Calculate and report percentage of tosses that resulted in heads
      double probabilityBiasedCoin = (double) biasedCoin.headsCounter() / numTosses;
		System.out.println("The probability of getting heads is: " 
													+ probabilityBiasedCoin);

   }

}
										