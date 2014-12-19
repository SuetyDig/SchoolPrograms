
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;

/** An an object of this class represents a calendar date.
*   It is based upon the SimpleDate class found in the text by
*   Anderson and Franceschi, but a few extra capabilities have been
*   added.
*/

public class SimpleDate2
{
   /***  fields  ***/

   private int month;
   private int day;
   private int year;


   /***  constructors  ***/

   /** Initializes the new date object to represent the date January 1, 2000.
   */
   public SimpleDate2( )
   {
     setDate( 1, 1, 2000 );
   }

   /** Initializes the new date object to represent the date described by 
   *   the arguments (mm for month, dd for day, and yyyy for year).
   *  @param mm    initial value for month
   *  @param dd    initial value for day
   *  @param yyyy  initial value for year
   */
   public SimpleDate2( int mm, int dd, int yyyy )
   {
      setDate(mm, dd, yyyy);
   }

   /** Initializes the new date object to represent the date described
   *   by the given string, which is assumed to be in a m/d/y format
   *   (e.g., "5/16/1943") and to describe a legitimate calendar date.
   *   @param date a string in m/d/y format describing a calendar date
   */
   public SimpleDate2(String date)
   {
      int index1 = date.indexOf("/");
      int index2 = date.lastIndexOf("/");
      if((index1 >= 0) && (index2 >= 0) && (index1 != index2))
      {
         int m = Integer.parseInt(date.substring(0,index1));
         int d = Integer.parseInt(date.substring((index1+1),index2));
         int y = Integer.parseInt(date.substring(index2+1));
         setDate(m,d,y);
      } 
      else {
         setDate(1,1,2000);
      }
   }


   /***  accessor/observer methods  ***/

   /** Returns the month number (e.g., 4 for April) of the date
   *   represented by this date object.
   */
   public int getMonth( ) { return month; }


   /** Returns the day number of the date represented by this date object.
   */
   public int getDay( )   { return day; }


   /** Returns the year number of the date represented by this date object.
   */
   public int getYear( )  { return year; }


   /** Returns a number (1 for Sunday, 2 for Monday, etc.) indicating the
   *   day of the week on which falls the date represented by this date
   *   object.
   */
   public int getDayOfWeek()
   {
      Calendar c = Calendar.getInstance(); 
      c.setTime(new Date((year-1900),(month-1),day));
      return c.get(Calendar.DAY_OF_WEEK);
   }


   /** Returns a string describing the state of this date object in
   *   mm/dd/yyyy format.
   */
   public String toString( )
   {
      String fmt = "%02d";
      DecimalFormat nf = new DecimalFormat("##");
      return String.format(fmt,month) + "/" + 
             String.format(fmt,day) + "/" + year;
   }


   /** Reports whether or not the given object is equal to this date object.
   *  @param   d  Object to compare to this object
   *  @return  true if d is equal to this object
   *           false, otherwise
   */
   public boolean equals( Object d )
   {
      boolean result;
      if ( !( d instanceof SimpleDate2 ) ) {
         result = false;
      }
      else {
         SimpleDate2 d1 = (SimpleDate2)d;
         result = month == d1.month  &&  day == d1.day  &&  year == d1.year;
      }
      return result;
   }


   /***  mutator methods  ***/

   /** Sets the month value of this date object to that of the argument (mm)
   *   unless mm is not in the range 1..12, in which case the month value
   *   is set to 1 (January).
   *   @param mm new value for month
   */
   public void setMonth( int mm )
   {
      month = ( mm >= 1 && mm <= 12 ? mm : 1 );
   }

   /** Sets the day value of this date object to that of the argument (dd)
   *   unless dd is not in the proper range (e.g., 1..30 for April,
   *   (1..31 for May)), in which case the day value is set to 1.
   *   @param dd new value for day
   */
   public void setDay( int dd )
   {
      day = ( dd >= 1 && isValidDay( dd ) ? dd : 1 );
   }

   /** Sets the year value of this date object to that of the argument (yyyy).
   *   @param yyyy new value for year
   */
   public void setYear( int yyyy )
   {
      year = yyyy;
   }


   /** Sets the month, day, and year of this date object to the values
   *   specified by the arguments.
   *   @param mm    new value for month
   *   @param dd    new value for day
   *   @param yyyy  new value for year
   */
   public void setDate( int mm, int dd, int yyyy )
   {
      setYear( yyyy );  // set year first (could be leap year)
      setMonth( mm );   // set month next
      setDay( dd );     // set day
   }

   /** Advances this date object to the next day.
   */
   public void nextDay( )
   {
      day = day + 1;
      if ( !isValidDay( day ) )
      {
         day = 1;
         month = month + 1;
         if ( month > 12 )
         {
            month = 1;
            year = year + 1;
         }
      }
   }


   /* private methods */

   /* Reports whether or not (getYear(), getMonth(), newDay) describes
   *  a valid date.
   */
   private boolean isValidDay( int newDay )
   {
      int [] daysInMonth = { 0, 31, 28, 31, 30, 31, 30, 
                            31, 31, 30, 31, 30, 31 };

      boolean result;
      if ( newDay > daysInMonth[month] )
      {
         result =  month == 2 && isLeapYear( ) && newDay == 29; 
      }
      else
      {
         result = true;
      }
      return result;
   }


   /* Reports whether this date object represents a date lying in a leap year.
   */
   private boolean isLeapYear( )
   {
      return year % 400 == 0  ||  (year % 4 == 0  &&  year % 100 != 0);
   }


}
