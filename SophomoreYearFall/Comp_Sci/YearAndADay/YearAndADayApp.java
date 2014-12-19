
/*Adam Miller
**Solution to Programming Assignment 3
*/

import java.util.Scanner;      // for reading input data
import java.io.File;           // for connecting to a data file
import java.io.IOException;    // in case file does not exist

/** Java application that reads a sequence of date values from a file, and
**  for each value determines the date one full year plus one additional day
**  later (i.e., that would be 366 or 367 days later) and then prints this
**  revised date.  Essentially then, this  program reads in a value, modifies
**  it in a specific way, and then prints the resultant value.  
**
**  The dates in the file are assumed to be in a form exemplified by
**
**             February 13, 1853
**
**  That is, the date is given by the name of the month, followed by a
**  space, followed by a one- or two-digit numeral indicating the day,
**  followed by a comma and a space, and, finally, a numeral indicating
**  the year.
**
**  The revised date is displayed in the DD-MMM-YYYY form, exemplified by
**
**             13-Feb-1853
**
**  In particular, the month is always indicated by an abbreviation
**  consisting of its first three characters.
*/

public class YearAndADayApp {
   
   public static void main(String[] args) throws IOException {
      
      // Establish a Scanner that will read from the data file.
      Scanner input = makeScanner(args);

      // Loop to read each line of data from the data file and process it.
      while(input.hasNextLine())
      {
         // Read the date on the next line of the data file, removing any
         // leading or trailing blanks.
         String dateStr = input.nextLine().trim();

         // Display the date given as input.
         System.out.print(dateStr + " advances to ");
            
         // Create a SimpleDate object representing the date described
         // by the input string.
         SimpleDate date = parseDate(dateStr);

         /*The next two statements are for debugging purposes only. */
         //System.out.print("The SimpleDate returned from parseDate() is ");
         //System.out.println(date.toString());
         
         // Advance the date by one full year
   		date.setYear(date.getYear() + 1);    
			
			//Advances the date by a day  
			date.nextDay();
			
         /*The next two statements are for debugging purposes only. */
         //System.out.print("After advancing the SimpleDate object, it is ");
         //System.out.println(date.toString());
         
         // Print the resultant value in the desired form
         System.out.println(asDDMMMYYYY(date));
      }
   }
   
   //String constants useful in the two methods that follow.
   private static final String MONTHS = "xxxJanFebMarAprMayJunJulAugSepOctNovDec";
   private static final String BLANK = " ";
   private static final String COMMA = ",";
   private static final String DASH  = "-";
      

   /** Returns a SimpleDate object that represents the date value
   **  expressed by the given String argument.
   **  @param dateAsStr  A string assumed to be a valid date in the 
   **  <monthName> <day>, <year> form (e.g., "April 25, 1965").
   */      
   private static SimpleDate parseDate(String dateAsStr)
   {
  		//Gets the month out of the date
      String strMonth = dateAsStr.substring(0, 3);
		
		//Converts the strMonth to an int
		int intMonth = MONTHS.indexOf(strMonth) / 3;
		
		//Gets the day out of the date
		String strDay = dateAsStr.substring(dateAsStr.indexOf(" ") + 1, 
														dateAsStr.indexOf(","));
		
		//Converts strDay to an int
		int intDay = Integer.parseInt(strDay);
		
		//Gets the year out of the date
		String strYear = dateAsStr.substring(dateAsStr.lastIndexOf(" ") + 1, 
														 dateAsStr.length());
														 
		//Converts strYear to an int
		int intYear = Integer.parseInt(strYear);
		
		SimpleDate result = new SimpleDate(intMonth, intDay, intYear); 

      return result;
   }

   
   /** Returns a String that expresses the date value represented by the 
   **  given SimpleDate object.  The String's format should be DD-Mon-YYYY,
   **  where DD is the day number, Mon is the 3-letter month abbreviation,
   **  and YYYY is the year number.  Examples: "26-Apr-1966", "2-Feb-1962".
   **  @param date The given date value.
   */      
   static String asDDMMMYYYY(SimpleDate date)
   {
    	String strMonth = MONTHS.substring(date.getMonth() * 3, date.getMonth() * 3 + 3);
	
		String result = date.getDay() + "-" + strMonth + "-" + date.getYear();

      return result;
	}


   /** Returns a Scanner that is connected to a file.
   **  If the given array has at least one element, the 0th one is
   **  taken to be the name of the file to which to connect.
   **  Otherwise, the user is prompted to enter the file name.
   */
   private static Scanner makeScanner(String[] commandLineArgs) throws IOException
   {
      String fileName;

      // Establish name of data file; if a command-line arg is present, 
      // use it, otherwise prompt user to enter it at the keyboard
      if (commandLineArgs.length > 0) {
         fileName = commandLineArgs[0];
      }
      else {
         Scanner keyboard = new Scanner(System.in);
         System.out.print("Enter name of data file: ");
         fileName = keyboard.nextLine();
      }
      return new Scanner(new File(fileName)); 
   }

}
