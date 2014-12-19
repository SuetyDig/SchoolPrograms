// Adam Miller
// Solution to Programming Assignment 6b Fall 2012 

import java.util.*;
import java.text.*;

/** A "less than simple" date class, obtained by augmenting and refactoring
**  the version of SimpleDate provided by Anderson and Franceschi in their
**  textbook "Java Illuminated".
**  Developed by P.M.J. and R.W.M., October 28, 2012
*/

public class SimpleDate {

   //Default values for instance variables describing default date of 1/1/2000.
   private static final int MONTH_DEFAULT = 1;
   private static final int DAY_DEFAULT =   1;
   private static final int YEAR_DEFAULT =  2000;

   //Instance Variables
   private int month = MONTH_DEFAULT; // 1 for January, 2 for February, etc.
   private int day = DAY_DEFAULT; // day of the month (e.g., 14 for the 14th)
   private int year = YEAR_DEFAULT;;
      
   //Public Constants (for the days of the week)
   public final int SUNDAY    = 1;
   public final int MONDAY    = 2;
   public final int TUESDAY   = 3;
   public final int WEDNESDAY = 4;
   public final int THURSDAY  = 5;
   public final int FRIDAY    = 6;
   public final int SATURDAY  = 7;
      
   ///////////////////////////////////////////////////////////////////////////
   // C o n s t r u c t o r   M e t h o d s
   ///////////////////////////////////////////////////////////////////////////
   
   /** Initializes this date object to the "default", which is January 1, 2000.
   */
   public SimpleDate( ) { }
   

   /** Initializes this date object so that it represents the date described
   **  by the three arguments.  (If the arguments do not describe a valid
   **  date, the object is initialized to represent the default date.)
   **  @param mm    initial value for month
   **  @param dd    initial value for day
   **  @param yyyy  initial value for year
   */
   public SimpleDate(int mm, int dd, int yyyy) { setDate(mm, dd, yyyy); }
   

   /** Initializes this date object so that it represents the date described
   **  by the argument, which can be in either the mm/dd/yyyy or mm-dd-yyyy
   **  form (e.g., "04/15/1945" or "04-15-1945").  (If the argument does not
   **  describe a valid date, the object is initialized to represent the
   **  default date.)
   **  @param date  expressed in either mm/dd/yyyy or mm-dd-yyyy form
   */
   public SimpleDate(String dateStr) { setDate(dateStr); }
      

   ////////////////////////////////////////////////////////////////////////////
   // A c c e s s o r / O b s e r v e r   M e t h o d s
   ////////////////////////////////////////////////////////////////////////////

   /** Returns this date's month component (e.g., 3 for March).
   */
   public int getMonth( ) { return month; }

   /** Returns this date's day component (e.g., 15 for the 15th
   **  day of the month).
   */
   public int getDay( ) { return day; }

   /** Returns this date's year component (e.g., 2012).
   */
   public int getYear( )  { return year; }
   

   /** Returns the day of the week on which this date falls using an
   **  int code consistent with the constants SUNDAY, MONDAY, etc.
   */
   public int getDayOfWeek() {
      Calendar c = Calendar.getInstance(); 
      c.setTime(new Date((year-1900),(month-1),day));
      return c.get(Calendar.DAY_OF_WEEK); //1=sunday, 2=monday, etc.
   }
   

   ////////////////////////////////////////////////////////////////////////////
   // M u t a t o r   M e t h o d s
   ////////////////////////////////////////////////////////////////////////////
   
   /** Sets this date object to represent the date described by the
   **  arguments, unless the arguments do not describe a valid date, 
   **  in which case this date object's state is not changed.
   **  @param mm    new value for month
   **  @param dd    new value for day
   **  @param yyyy  new value for year
   */
   public void setDate(int mm, int dd, int yyyy) {
      if(isValidDay(mm,dd,yyyy)) {
         year = yyyy;
         month = mm;
         day = dd;
      }
   }
      

   /** Sets this date object to represent the date described by the
   **  argument, unless the argument does not describe a valid date, in
   **  which case this date object's state is not changed.
   **  The argument is a string that can be in either the mm/dd/yyyy
   **  or the mm-dd-yyyy format.
   **  @param date  expressed in either mm/dd/yyyy or mm-dd-yyyy form
   */
   public void setDate(String dateStr) {
      SimpleDate sd = parseDate(dateStr);
      if (sd != null) {
         month = sd.getMonth();
         day   = sd.getDay();
         year  = sd.getYear();
      }
   }


   /** Sets this date's month to the value specified by the argument.  If
   **  the argument's value is invalid (e.g., 17), this date is not changed.
   **  Even if the argument value is valid, this date's day value will be
   **  "rolled back", if necessary, in order to arrive at a valid date
   **  (e.g., from 31 to 30 if the month is changed from July to April).
   **  @param mm new value for month
   */
   public void setMonth(int mm) {
      if(isValidMonth(mm)) {
         month = mm;
         rollBack();
      }
   }
   

   /** Sets this date's day to the value specified by the argument unless,
   **  by doing so, the resulting date is invalid, in which case this date
   **  is not changed.
   **  @param dd new value for day
   */
   public void setDay(int dd) {
      if(isValidDay(month,dd,year)) {
         day = dd;
      }
   }
   
   /** Sets this date's year to the value specified by the argument.
   **  Note that the day value of the resultant date will be "rolled back"
   **  (e.g., from 29 to 28 if the month is February and the year
   **  is changed from a leap year to a non-leap year) if it is
   **  necessary to arrive at a valid date.
   **  @param yyyy new value for year
   */
   public void setYear(int yyyy) {
      year = yyyy;
      rollBack();
   }
   

   //------------------------------------------------------------------------ 
   
   /** Moves this date one day backwards in time.
   **  (E.g., If the method is applied to an object representing May 4, 2000,
   **  afterwards the object represents May 3, 2000.)
   */
   public void prevDay( ) {
      setDay(getDay() - 1);
      if (!isValidDay(month,day,year)) {
      setMonth(getMonth() - 1);
         if (!isValidMonth(month - 1)) {
            setMonth(12);
            setYear(getYear() - 1);
         }
         day = monthDays(month, year);
      }

   }

   /** Moves this date one day forwards in time.
   **  (E.g., If the method is applied to an object representing May 4, 2000,
   **  afterwards the object represents May 5, 2000.)
   */
   public void nextDay( ) {
      day = day + 1;
      if (!isValidDay(month,day,year)) {
         day = 1;
         month = month + 1;
         if (!isValidMonth(month)) {
            month = 1;
            year = year + 1;
         }
      }
   }
      

   /** Moves this date the specified number (n) of days backwards in time.
   **  @param n the specified number of days to move backwards in time
   **  (must be >= 0).
   */
   public void prevDay(int n) {
      for(int i = 0; i < n; i++) { prevDay(); }
   }

   /** Moves this date the specified number (n) of days forwards in time.
   **  @param n the specified number of days to move forwards in time
   **  (must be >= 0).
   */
   public void nextDay(int n) {
      for(int i = 0; i < n; i++) { nextDay(); }
   }
       
   //------------------------------------------------------------------------- 
   
   /** Moves this date one week (i.e., seven days) backwards in time.
   **  (E.g., If the method is applied to an object representing May 4, 2000,
   **  afterwards the object represents April 27, 2000.)
   */
   public void prevWeek( ) {
      prevDay(7);
   }
 

   /** Moves this date one week (i.e., seven days) forwards in time.
   **  (E.g., If the method is applied to an object representing May 4, 2000,
   **  afterwards the object represents May 11, 2000.)
   */
   public void nextWeek( ) {
      nextDay(7); 
   }
      

   /** Moves this date the specified number (n) of weeks backwards in time.
   **  @param n the specified number of days to move backwards in time
   **  (must be >= 0).
   */
   public void prevWeek(int n) {
      for(int i = 0; i < n; i++) { prevWeek(); }
   }
      

   /** Moves this date the specified number (n) of weeks forwards in time.
   **  @param n the specified number of days to move backwards in time
   **  (must be >= 0).
   */
   public void nextWeek(int n) {
      for(int i = 0; i < n; i++) { nextWeek(); }
   }
       
   //------------------------------------------------------------------------- 
   
   /** Moves this date one month backwards in time (to the "corresponding"
   **  day in the previous month).  (E.g., If the method is applied to an
   **  object representing either May 30 or 31, 2000, afterwards the object
   **  represents April 30, 2000.)
   */
   public void prevMonth( ) { 
      prevMonth(1);    
   }
      
   /** Moves this date one month forwards in time (to the "corresponding"
   **  day in the following month).  (E.g., If the method is applied to an 
   **  object representing either May 30 or 31, 2000, afterwards the object
   **  represents June 30, 2000.)
   */
   public void nextMonth( ) {
     nextMonth(1);
   }      
      

   /** Moves this date the specified number (n) of months backwards in time.
   **  (E.g., if the method is applied to an object representing January 31
   **  (or 30) 1987 and the argument's value is 21, afterwards the object 
   **  represents April 30, 1986.)
   **  @param n the specified number of months to move backwards in time
   **  (must be >= 0).
   */
   public void prevMonth(int n) {
      for(int i = 0; i < n; i++) {
         if(getMonth() - 1 == 0) { 
            setMonth(12); 
            setYear(getYear() - 1); 
         } else { setMonth(getMonth() - 1); }
      }
      rollBack();
   }      
   /** Moves this date the specified number (n) of months forwards in time.
   **  (E.g., if the method is applied to an object representing January 31
   **  (or 30) 1987 and the argument's value is 27, afterwards the object
   **  represents April 30, 1989.)
   **  @param n the specified number of months to move forwards in time
   **  (must be >= 0).
   */
   public void nextMonth(int n) {
      for(int i = 0; i < n; i++) {
         if(getMonth() + 1 == 13) { 
            setMonth(1); 
            setYear(getYear() + 1); 
         } else { setMonth(getMonth() + 1); }
      }
      rollBack();
   }      
     
   //-------------------------------------------------------------------------
   
   /** Moves this date one year backwards in time (to the "corresponding"
   **  day in the previous year).  (E.g., If the method is applied to an
   **  object representing either February 28 or 29, 2000, afterwards the
   **  object represents February 28, 1999.)
   */
   public void prevYear( ) {
       prevYear(1);
   }
      
   /** Moves this date one year forwards in time (to the "corresponding"
   **  day in the following year).  (E.g., If the method is applied to an
   **  object representing either February 28 or 29, 2000, afterwards the
   **  object represents February 28, 2001.)
   */
   public void nextYear( ) {
      nextYear(1);
   }
      

   /** Moves this date the specified number (n) of years backwards in time.
   **  @param n the specified number of years to move backwards in time
   **  (must be >= 0).
   */
   public void prevYear(int n) {
      setYear(getYear() - n);
      rollBack();
   }

      
   /** Moves this date the specified number (n) of years forwards in time.
   **  @param n the specified number of years to move forwards in time
   **  (must be >= 0).
   */
   public void nextYear(int n) {
      setYear(getYear() + n);
      rollBack();
   }
       

   //------------------------------------------------------------------------- 
   
   /////////////////////////////////////////////////////////////////////////// 
   // S t a n d a r d / C o n v e n t i o n a l   M e t h o d s
   ///////////////////////////////////////////////////////////////////////////
   
   /** Returns a string representing this date object's current value.
   **  @return an expression of the date value in mm/dd/yyyy format
   */
   public String toString( ) {
      String fmt = "%02d";
      return String.format(fmt,month) + "/" + 
             String.format(fmt,day) + "/" + year;
   }
   
   /** Compares this date to the given date for equality.
   **  @param anotherDate the date being compared to this date
   **  @return  true iff this date and the given date fall on the same day
   */
   public boolean equals(SimpleDate anotherDate)
   {
      return this.getMonth() == anotherDate.getMonth()  &&
             this.getDay() == anotherDate.getDay() &&
             this.getYear() == anotherDate.getYear();
   }
      
   /** Reports whether this date comes before, after, or on the same day
   **  as the given date by returning, respectively, a negative value, a
   **  positive value, or zero.
   **  @param   anotherDate the date being compared to this date
   **  @return  a negative value if this date comes before the given one,
   **           a positive value if this date comes after the given one,
   **           zero if they fall on the same day
   */
   public int compareTo(SimpleDate anotherDate) {
      int result = 0;
      if(getYear() == anotherDate.getYear()) {
         if(getMonth() == anotherDate.getMonth()) {
            if(getDay() == anotherDate.getDay()) { result = 0; }
            else if(getDay() >= anotherDate.getDay()) { result = 1; }
            else { result = -1; }       
         } else if(getMonth () > anotherDate.getMonth()) { result = 1; }
         else { result = -1; }
      } else if(getYear() > anotherDate.getYear()) { result = 1; }
      else { result = -1; }
    
      return result;
   }


   ///////////////////////////////////////////////////////////////////////////
   // P r i v a t e   M e t h o d s
   ///////////////////////////////////////////////////////////////////////////
   

   /* Returns a SimpleDate object representing the date value expressed by
   ** the given string argument, which can be in either of the mm/dd/yyyy or
   ** mm-dd-yyyy forms.  If the string is mal-formed or does not describe a
   ** valid date, the returned object represents the default date.
   */
   private SimpleDate parseDate(String date)
   {
      SimpleDate result = parseDate_mmddyyyy(date,'/');
      if (result == null) {
         result = parseDate_mmddyyyy(date,'-');
      }
      return result;
   }
            

   /* Returns a SimpleDate object representing the date value expressed by
   ** the given string argument, which is expected to be in a mm*dd*yyyy
   ** form, where * is equal to the second argument.  (Hence, this method
   ** can be used to recognize date values in either the mm/dd/yyyy or
   ** mm-dd-yyyy forms.)  If the string is mal-formed, null is returned.
   ** If the string is syntactically valid but describes an invalid date
   ** (e.g., "17/3/2012"), the object returned represents the default date.
   */
   private SimpleDate parseDate_mmddyyyy(String date, char delimiter) {
      SimpleDate result = null;
      int m, d, y;
      int index1 = date.indexOf(delimiter);
      int index2 = date.lastIndexOf(delimiter);
      if((index1 >= 0) && (index2 > index1)) {
         try {
            m = Integer.parseInt(date.substring(0,index1));
            d = Integer.parseInt(date.substring((index1+1),index2));
            y = Integer.parseInt(date.substring(index2+1));
            result = new SimpleDate(m,d,y);         
         }               
         catch (NumberFormatException e)
         {  //No remedial action necessary;  result will equal null
         }
      } 
      return result;
   }
   

   /* In case this date is invalid due to 'day' being larger than the
   *  number of days in the month, 'day' is reduced to the latter number.
   */
   private void rollBack() {
      int daysInMonth = monthDays(month, year);
      if (day > daysInMonth) {
         day = daysInMonth; 
      }
   }
   

   /* Returns true iff the given triplet corresponds to a valid date value.
   */
   private boolean isValidDay(int month, int day, int year) {
      boolean result = isValidMonth(month) && 
                       ((day >= 1) && (day <= monthDays(month,year)));
      return result;
   }
   

   /* Returns true iff the given month value is valid (i.e., in the range
   ** 1..12).
   */
   private boolean isValidMonth(int month) {
      return ((month >= 1) && (month <= 12));
   }

      
   /* Returns, with respect to the given year, the number of days in
   ** the given month. 
   ** pre-condition: 1 <= month <= 12
   */
   private int monthDays(int month, int year) {
      int result;
      //  Assert: (month >= 1) && (month <=12)
      if ((month==4) || (month==6) || (month==9) || (month==11)) {
         result = 30;
      } 
      else if(month == 2) {
         if (isLeapYear(year)) {
            result = 29;
         } 
         else {
            result = 28;
         }
      } 
      else {           // month==1 || month==3 || month==5 || month==7 ||
         result = 31;  // month==8 || month==10 || month==12
      }
      return  result;
   }
      

   /* Returns true iff the given year value qualifies as a leap year.
   */
   private boolean isLeapYear(int year) {
      return (year % 400 == 0)  || (year % 4 == 0  &&  year % 100 != 0);
   }
   
}
