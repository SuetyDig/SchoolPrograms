/** Adam Miller
**  Programming Asignment 4 Solution
**  The programming picks up if two holiday's are on the same day and adds
**  the appropriate comma and space but if someone was too add another
**  holiday that would overlap with another day they'd have to make an
**  if statement exception like I did
*/

import java.util.Scanner;

/** Application program that produces a list of "known holidays" for a given
**  calendar interval.  The beginning date value and ending date value are
**  either given as command line argument values or the user is prompted to
**  enter them.  In either case, the dates must be given in the form 
**  mm/dd/yyyy.  Furthermore, the first date value MUST NOT represent a
**  date later than the second date value.
**
**  @author P.M.J., Fall 2012
*/

public class HolidaysApp {
   
   static Scanner input = new Scanner(System.in);

   public static void main(String[] args)
   {
      // Initialize the starting and ending date values
      SimpleDate2 start = new SimpleDate2(getString(args,0).trim());
      SimpleDate2 stop  = new SimpleDate2(getString(args,1).trim());
      SimpleDate2 temp = new SimpleDate2();
      if (start.getYear() == stop.getYear()) {
         if (start.getMonth() == stop.getMonth()) {
            if (start.getDay() > stop.getDay()) {
         
            temp.setDay(start.getDay());
         
            start.setDay(stop.getDay());
         
            stop.setDay(temp.getDay());
            }
        } else if (start.getMonth() > stop.getMonth()) {
         temp.setDay(start.getDay());
         temp.setMonth(start.getMonth());

         start.setDay(stop.getDay());
         start.setMonth(stop.getMonth());
         
         stop.setDay(temp.getDay());
         stop.setMonth(temp.getMonth());        
        }    
      } else if (start.getYear() > stop.getYear()) {
         temp.setDay(start.getDay());
         temp.setMonth(start.getMonth());
         temp.setYear(start.getYear());

         start.setDay(stop.getDay());
         start.setMonth(stop.getMonth());
         start.setYear(stop.getYear());  
         
         stop.setDay(temp.getDay());
         stop.setMonth(temp.getMonth());        
         stop.setYear(temp.getYear());       
      }

      // Loop to iterate over the interval
      while(!start.equals(stop))
      {
         // Process the current date
         String result = holidaysOf(start);
         if(result.length() > 0) {   //Check for non-empty string result
            System.out.println(dayOfWeekAbbreviation(start.getDayOfWeek()) +
                               " " + start + " " + result);
         }
         start.nextDay();      //Advance to the next date
      }
   }
      

   /** Returns a string expressing the name (or names) of the holidays
   **  occuring on the given date.  If no holiday occurs on the 
   **  given date then the empty string is returned.
   */
   static String holidaysOf(SimpleDate2 date)
   {
      String result = "";
      //January Holidays
      result += asNewYearsDay(date);
      result += asMLKDay(date);
   
      //February Holidays
      result += asGroundHogsDay(date);
      result += asValentinesDay(date);
      result += asPresidentsDay(date);
      
      //March Holidays
      result += asDaylightSavingsStart(date);
      result += asStPatricksDay(date);
      
      //April Holidays
      result += asAprilFoolsDay(date);
      result += asArborDay(date);
  
      //May Holidays
      result += asMothersDay(date);
      result += asMemorialDay(date);
   
      //June Holidays
      result += asFathersDay(date);
      
      //July Holidays
      result += asFourthOfJuly(date);
      result += asFourthOfJulyObserved(date);
      //August Holidays
      
      //September Holidays
      result += asLaborDay(date);
      
      //October Holidays
      result += asHalloween(date);
        
      //November Holidays
      result += asAllSaints(date);
      result += asDaylightSavingsEnd(date);
      result += asThanksgiving(date);
      
      //December Holidays
      result += asPearlHarbor(date);
      result += asChristmas(date);
      result += asNewYearsEve(date); 
      return result;  
   }

   /** Returns "New Year's Day" if the given date corresponds to the
   **  date New Year's Day would fall on, or the empty string if it
   **  does not.
   */
   static String asNewYearsDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 1) && date.getDay() == 1) {
          result = "New Year's Day";
         }
      return result;
   }   
   /** Returns "Martin Luther King Day" if the given date corresponds to the
   **  date Martin Luther King Day would fall on, or the empty string if it
   **  does not.
   */

   static String asMLKDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 1) && (date.getDay() >= 15) && (date.getDay() < 22)
         && (date.getDayOfWeek() == MON)) {
          result = "Martin Luther King Day";
         }
      return result;
   }

   /** Returns "Ground Hog's Day" if the given date corresponds to the
   **  date Ground Hog's Day would fall on, or the empty string if it
   **  does not.
   */
   static String asGroundHogsDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 2) && date.getDay() == 2) {
          result = "Ground Hog's Day";
         }
      return result;
   }
   
   /** Returns "Valentine's Day" if the given date corresponds to the
   **  date Valentine's Day would fall on, or the empty string if it
   **  does not.
   */
   static String asValentinesDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 2) && date.getDay() == 14) {
          result = "Valentine's Day";
         }
      return result;
   }
   
   /** Returns "President's Day" if the given date corresponds to the
   **  date President's Day would fall on, or the empty string if it
   **  does not.
   */
   static String asPresidentsDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 2) && (date.getDay() >= 15) && (date.getDay() < 22)
         && (date.getDayOfWeek() == MON)) {
          result = "President's Day";
         }
      return result;
   }
      
   /** Returns "Saint Patrick's Day" if the given date corresponds to the
   **  date Saint Patrick's Day would fall on, or the empty string if it
   **  does not.
   */
   static String asStPatricksDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 3) && date.getDay() == 17) {
          result = "Saint Patrick's Day";
         }
      return result;
   }
   
   /** Returns "Daylight Savings Starts" if the given date corresponds to the
   **  date Daylight Savings Starts would fall on, or the empty string if it
   **  does not.
   */
   static String asDaylightSavingsStart(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 3) && (date.getDay() >= 8) && (date.getDay() < 15)
         && (date.getDayOfWeek() == SUN)) {
          result = "Daylight Savings Starts";
         }
      return result;
   }

   /** Returns "April Fool's Day" if the given date corresponds to the date
   **  April Fool's Day would fall on, or the empty string if it does not.
   */
   static String asAprilFoolsDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 4) && date.getDay() == 1) {
         result = "April Fool's Day";
      }
      return result;
   }
   /** Returns "Arbor Day" if the given date corresponds to the date
   **  Arbor Day would fall on, or the empty string if it does not.
   */
   static String asArborDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 4) && (date.getDay() >= 24)
       && (date.getDay() <= 31) && (date.getDayOfWeek() == FRI)) {
         if(date.getDay() == 24) {
         date.setDay(date.getDay() + 6);
         date.nextDay();
         if(date.getDay() != 31) result = "Arbor Day";
         } result = "Arbor Day";
      }
      return result;
   }


   /** Returns "Mother's Day" if the given date corresponds to the date
   **  Mother's Day would fall on, or the empty string if it does not.
   */
   static String asMothersDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 5) && (date.getDay() >= 8) && (date.getDay() < 15)
         && (date.getDayOfWeek() == SUN)) {
         result = "Mother's Day";
      }
      return result;
   }

   /** Returns "Memorial Day" if the given date corresponds to the date
   **  Memorial Day would fall on, or the empty string if it does not.
   */
   static String asMemorialDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 5) && (date.getDay() >= 24)
       && (date.getDay() <= 31) && (date.getDayOfWeek() == MON)) {
         if(date.getDay() == 24) {
         date.setDay(date.getDay() + 6);
         date.nextDay();
         if(date.getDay() != 31) result = "Memorial Day";
         } result = "Memorial Day";
      }
      return result;
   }
   
   /** Returns "Father's Day" if the given date corresponds to the date
   **  Father's Day would fall on, or the empty string if it does not.
   */
   static String asFathersDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 6) && (date.getDay() >= 15) && (date.getDay() < 22)
         && (date.getDayOfWeek() == SUN)) {
         result = "Father's Day";
      }
      return result;
   }

   
   /** Returns "Independence Day Observed" if the given date corresponds
   **  to the day Independence Day Obeserved falls on, or the empty string
   **  if it does not.
   */
   static String asFourthOfJulyObserved(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 7) && (date.getDay() == 4)
          && (date.getDayOfWeek() != SAT && date.getDayOfWeek() != SUN)) {
         result = ", Independence Day Observed";
         }
      if((date.getMonth() == 7) && (date.getDay() + 1) == 4 && 
          date.getDayOfWeek() == FRI) {
         result = "Independence Day Observed";
         }      
      if((date.getMonth() == 7) && (date.getDay() - 1) == 4 && 
          date.getDayOfWeek() == MON) {
         result = "Independence Day Observed";
         }

      return result; 
   }
   
   /** Returns "Independence Day" if the given date corresponds to the day
   **  July 4th, or the empty string if it does not.
   */
   
   static String asFourthOfJuly(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 7) && (date.getDay() == 4)) {
         result = "Independence Day";
      }
      return result;
   }
   
   /** Returns "Labor Day" if the given date corresponds to the date
   **  Labor Day would fall on, or the empty string if it does not.
   */
   static String asLaborDay(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 9) && (date.getDay() >=1) && (date.getDay() < 8)
         && (date.getDayOfWeek() == MON)) {
         result = "Labor Day";
      }
      return result;
   }
   
   /** Returns "Halloween" if the given date corresponds to the day
   **  Halloween, or the empty string if it does not.
   */
   static String asHalloween(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 10) && (date.getDay() == 31)) {
         result = "Halloween";
      }
      return result;
   }
   
   /** Returns "All Saint's Day" if the given date corresponds to the day
   **  All Saint's Day, or the empty string if it does not.
   */
   static String asAllSaints(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 11) && (date.getDay() == 1)) {
         result = "All Saint's Day";
      }
      return result;
   }
   
   /** Returns "Daylight Savings Ends" if the given date corresponds to the date
   **  Daylight Savings Ends would fall on, or the empty string if it does not.
   */
   static String asDaylightSavingsEnd(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 11) && (date.getDay() >=1) && (date.getDay() < 8)
         && (date.getDayOfWeek() == SUN)) {
         if(date.getDay() == 1) {
         result = ", Daylight Savings Ends";
         } else result = "Daylight Savings Ends";
         }
      return result;
   }
   
   /** Returns "Daylight Savings" if the given date corresponds to the date
   **  Daylight Savings would fall on, or the empty string if it does not.
   */
   static String asThanksgiving(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 11) && (date.getDay() >= 22) && (date.getDay() <29)
         && (date.getDayOfWeek() == THU)) {
         result = "Thanksgiving";
      }
      return result;
   }
   
   /** Returns "Christmas" if the given date corresponds to the
   **  Christmas Holiday, or the empty string if it does not.
   */
   static String asChristmas(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 12) && (date.getDay() == 25)) {
         result = "Christmas";
      }
      return result;
   }
   
   /** Returns "Pear Harbor Rememberance Day" if the given date corresponds to
   **  the day Pear Harbor Rememberance Day, or the empty string if it does not
   */
   static String asPearlHarbor(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 12) && (date.getDay() == 7)) {
         result = "Pearl Harbor Rememberance Day";
      }
      return result;
   }
   
   /** Returns "New Year's Eve" if the given date corresponds to the day
   **  New Year's Eve, or the empty string if it does not.
   */
   static String asNewYearsEve(SimpleDate2 date)
   {
      String result = "";
      if((date.getMonth() == 12) && (date.getDay() == 31)) {
         result = "New Year's Eve";
      }
      return result;
   }


   //Constants useful with SimpleDate2's getDayOfWeek() method
   static final int SUN = 1;
   static final int MON = 2;
   static final int TUE = 3;
   static final int WED = 4;
   static final int THU = 5;
   static final int FRI = 6;
   static final int SAT = 7;
      

   /** Returns the abbreviation for the given "day of the week value",
   **  where, 1 == "Sun", 2 == "Mon", etc.
   */
   static String dayOfWeekAbbreviation(int dow)
   {
      String result = "???";
      if(dow == SUN) {
         result = "Sun";
      } 
      else if(dow == MON) {
         result = "Mon";
      } 
      else if(dow == TUE) {
         result = "Tue";
      } 
      else if(dow == WED) {
         result = "Wed";
      } 
      else if(dow == THU) {
         result = "Thu";
      } 
      else if(dow == FRI) {
         result = "Fri";
      } 
      else if(dow == SAT) {
         result = "Sat";
      }
      return result;
   }
      

   /** Functional Method that as passed the command line arguments and the 
   **  index of the argument desired. If that argument exists then it as 
   **  returned as the value of this function; if not then the user as
   **  prompted to enter a string value, which as then returned as the result
   **  of this function.
   */
   static String getString(String[] commandLineArgs, int i)
   {
      String result;
      if (commandLineArgs.length > i) {
         result = commandLineArgs[i];
      }
      else {
         Scanner keyboard = new Scanner(System.in);
         System.out.print("Enter "+i+"(in dd/mm/yyyy format):");
         result = keyboard.nextLine();
      }
      return result; 
   }
      
}

