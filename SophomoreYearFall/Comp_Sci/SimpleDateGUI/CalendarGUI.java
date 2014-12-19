   import java.util.Scanner;
   import java.io.File;
   import java.io.IOException;    
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
/*
   Application that presents a Graphical User Interface for exercising the
   mutator methods of the augmented SimpleDate class.
   
   P.M.J., November 2012 
*/
   public class CalendarGUI {
   
      static DialogBox Input = new DialogBox("CalendarGUI...");
   
   //-------------------------------------------------------------------------
   // 
   // ActionListeners
   // 
   //-------------------------------------------------------------------------
      public static class prevDay_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.prevDay();
            display(PREVDAY);
         }
      }
      static prevDay_CommandListener prevDay_CommandHandler = 
         new prevDay_CommandListener();   
   
      public static class prevDayN_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.prevDay(Input.nextInt("N:"));
            display(PREVDAYN);
         }
      }
      static prevDayN_CommandListener prevDayN_CommandHandler = 
         new prevDayN_CommandListener();   
      
      public static class nextDay_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.nextDay();
            display(NEXTDAY);
         }
      }
      static nextDay_CommandListener nextDay_CommandHandler = 
         new nextDay_CommandListener();   
   
      public static class nextDayN_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.nextDay(Input.nextInt("N:"));
            display(NEXTDAYN);
         }
      }
      static nextDayN_CommandListener nextDayN_CommandHandler = 
         new nextDayN_CommandListener();   
      
   //-------------------------------------------------------------------------
      public static class prevWeek_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.prevWeek();
            display(PREVWEEK);
         }
      }
      static prevWeek_CommandListener prevWeek_CommandHandler = 
         new prevWeek_CommandListener();   
   
      public static class prevWeekN_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.prevWeek(Input.nextInt("N:"));
            display(PREVWEEKN);
         }
      }
      static prevWeekN_CommandListener prevWeekN_CommandHandler = 
         new prevWeekN_CommandListener();   
      
      public static class nextWeek_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.nextWeek();
            display(NEXTWEEK);
         }
      }
      static nextWeek_CommandListener nextWeek_CommandHandler = 
         new nextWeek_CommandListener();   
   
      public static class nextWeekN_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.nextWeek(Input.nextInt("N:"));
            display(NEXTWEEKN);
         }
      }
      static nextWeekN_CommandListener nextWeekN_CommandHandler = 
         new nextWeekN_CommandListener();   
      
   //--------------------------------------------------------------------------
      public static class prevMonth_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.prevMonth();
            display(PREVMONTH);
         }
      }
      static prevMonth_CommandListener prevMonth_CommandHandler = 
         new prevMonth_CommandListener();   
   
      public static class prevMonthN_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.prevMonth(Input.nextInt("N:"));
            display(PREVMONTHN);
         }
      }
      static prevMonthN_CommandListener prevMonthN_CommandHandler = 
         new prevMonthN_CommandListener();   
      
      public static class nextMonth_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.nextMonth();
            display(NEXTMONTH);
         }
      }
      static nextMonth_CommandListener nextMonth_CommandHandler = 
         new nextMonth_CommandListener();   
   
      public static class nextMonthN_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.nextMonth(Input.nextInt("N:"));
            display(NEXTMONTHN);
         }
      }
      static nextMonthN_CommandListener nextMonthN_CommandHandler = 
         new nextMonthN_CommandListener();   
      
   //--------------------------------------------------------------------------
      public static class prevYear_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.prevYear();
            display(PREVYEAR);
         }
      }
      static prevYear_CommandListener prevYear_CommandHandler =
         new prevYear_CommandListener();   
   
      public static class prevYearN_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.prevYear(Input.nextInt("N:"));
            display(PREVYEARN);
         }
      }
      static prevYearN_CommandListener prevYearN_CommandHandler = 
         new prevYearN_CommandListener();   
      
      public static class nextYear_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.nextYear();
            display(NEXTYEAR);
         }
      }
      static nextYear_CommandListener nextYear_CommandHandler = 
         new nextYear_CommandListener();   
   
      public static class nextYearN_CommandListener implements ActionListener {
         public void actionPerformed( ActionEvent ae ) {
            update();
            Current.nextYear(Input.nextInt("N:"));
            display(NEXTYEARN);
         }
      }
      static nextYearN_CommandListener nextYearN_CommandHandler = 
         new nextYearN_CommandListener();   
      
      public static void update() {
         Previous = new SimpleDate(Current.getMonth(),
                                   Current.getDay(),
                                   Current.getYear());
      }


   //--------------------------------------------------------------------------
   //   
   // T a b l e 
   //   
   //--------------------------------------------------------------------------
      public static class MonthTable extends JFrame {
      // Instance attributes used in this example 
         private JPanel topPanel; 
         private JTable table; 
         private JScrollPane scrollPane; 
         private String columnNames[] = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"}; 
         
      // Constructor of main frame 
         public MonthTable(String preface, SimpleDate sd, int xLoc, int yLoc) { 
         // Set the frame characteristics 
            setTitle(preface+MonthNames[sd.getMonth()]+" "+sd.getYear()); 
            setLocation (xLoc,yLoc); 
            setSize( 500, 160 ); 
            setBackground( Color.gray ); 
         // Create a panel to hold all other components 
            topPanel = new JPanel(); 
            topPanel.setLayout( new BorderLayout() ); 
            getContentPane().add( topPanel ); 
         // Create some data 
            String dataValues[][] = contentSequence(sd); 
         // Create a new table instance 
            table = new JTable( dataValues, columnNames ); 
         // Add the table to a scrolling pane 
            scrollPane = new JScrollPane( table ); 
            table.setFillsViewportHeight(true);
            topPanel.add( scrollPane, BorderLayout.CENTER );
         } 
          
         public void doUpdate(String preface, SimpleDate sd) {
            setTitle(preface+MonthNames[sd.getMonth()]+" "+sd.getYear()); 
            table = new JTable( contentSequence(sd), columnNames ); 
         // Add the table to a scrolling pane 
            scrollPane = new JScrollPane( table ); 
            table.setFillsViewportHeight(true);
            topPanel.removeAll();
            topPanel.add(scrollPane, BorderLayout.CENTER);
          //topPanel.revalidate();
         }
      }
   
 
   
   //-------------------------------------------------------------------------
   //   
   // A p p l i c a t i o n
   //   
   //-------------------------------------------------------------------------
      public static MonthTable MTCurrent;
      public static MonthTable MTPrevious;
      
      public static void display(String Operation) {
         MTPrevious.doUpdate("PREVIOUS: ",Previous);
         MTCurrent.doUpdate("CURRENT: ",Current);
         MTCurrent.setVisible(true);  MTPrevious.setVisible(true);
         System.out.print(Operation+": ");
         System.out.print("Previous is "+Previous+", ");
         System.out.print("Current is "+Current+"; and ");
         int relative = Current.compareTo(Previous);
         if(relative > 0) {
            System.out.print("Current is now later than Previous");
         } 
         else if(relative < 0) {
            System.out.print("Current is now earlier than Previous");
         } 
         else {
            System.out.print("Current and Previous are now the same");
         }
         System.out.println(".");
      }
   
      public static String[][] contentSequence(SimpleDate date) {
         String[][] result = new String[6][7];
         SimpleDate sd = new SimpleDate(date.getMonth(),1,date.getYear());
         final String EMPTY = "    ";
         //Fill empty part of first week
         int weekIndex = 0;
         int dofwIndex = 0;
         int sdDayOfWeek = sd.getDayOfWeek();
         for(int dof=sd.SUNDAY; dof<sdDayOfWeek; dof++) {
            result[weekIndex][dofwIndex] = EMPTY;
            dofwIndex = dofwIndex + 1;
         }
         //Fill in known dates for the rest of the month
         int month = sd.getMonth();
         while(sd.getMonth() == month) {
            if(dofwIndex == sd.SATURDAY) {
               weekIndex = weekIndex + 1;
               dofwIndex = 0;
            }
            //System.out.println(sd.SATURDAY+"::"+weekIndex+","+
            //dofwIndex+" <<"+intToString2(sd.getDay())+">>");
            String content = intToString2(sd.getDay());
            if(sd.equals(date)) {
               content = "((("+content+")))";
            }
            result[weekIndex][dofwIndex] = content;
            dofwIndex = dofwIndex + 1;
            sd.nextDay();
         }
         //Fill empty part of the rest of the month
         while((weekIndex <= 5) && (dofwIndex < sd.SATURDAY)) {
            if(dofwIndex == sd.SATURDAY) {
               weekIndex = weekIndex + 1;
               dofwIndex = 0;
            }
            result[weekIndex][dofwIndex] = EMPTY;
            dofwIndex = dofwIndex + 1;         
         }
         return result;
      }
   
      public static String intToString2(int i) {
         String fmt = "%2d";
         //DecimalFormat nf = new DecimalFormat("##");
         return String.format(fmt,i);
      }
   
      /* Functional Method that is passed the command line arguments and the 
         index of the argument desired. If that argument exists then it is 
         returned as the value of this function; if not then the user is
         prompted to enter a string value, which as then returned as the
         result of this function.
      */
      static String getString(String[] commandLineArgs, int i) {
         String result;
         if (commandLineArgs.length > i) {
            result = commandLineArgs[i];
         }
         else {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter date #"+i+":");
            result = keyboard.nextLine();
         }
         return result; 
      }
   	
      static final String PREVDAY = "prevDay()";
      static final String PREVDAYN = "prevDay(n)";
      static final String NEXTDAY = "nextDay()";
      static final String NEXTDAYN = "nextDay(n)";
      static final String PREVWEEK = "prevWeek()";
      static final String PREVWEEKN = "prevWeek(n)";
      static final String NEXTWEEK = "nextWeek()";
      static final String NEXTWEEKN = "nextWeek(n)";
      static final String PREVMONTH = "prevMonth()";
      static final String PREVMONTHN = "prevMonth(n)";
      static final String NEXTMONTH = "nextMonth()";
      static final String NEXTMONTHN = "nextMonth(n)";
      static final String PREVYEAR = "prevYear()";
      static final String PREVYEARN = "prevYear(n)";
      static final String NEXTYEAR = "nextYear()";
      static final String NEXTYEARN = "nextYear(n)";
         	
      static final String[] COMMANDS = 
         {PREVDAY, PREVDAYN, NEXTDAY, NEXTDAYN, 
          PREVWEEK, PREVWEEKN, NEXTWEEK, NEXTWEEKN,
          PREVMONTH, PREVMONTHN, NEXTMONTH, NEXTMONTHN, 
          PREVYEAR, PREVYEARN, NEXTYEAR, NEXTYEARN, 
         };
      static CommandButtons CBs = 
         new CommandButtons("", COMMANDS, prevDay_CommandHandler,
                            prevDayN_CommandHandler,nextDay_CommandHandler,
                            nextDayN_CommandHandler, prevWeek_CommandHandler,
                            prevWeekN_CommandHandler,nextWeek_CommandHandler,
                            nextWeekN_CommandHandler, prevMonth_CommandHandler,
                            prevMonthN_CommandHandler,nextMonth_CommandHandler,
                            nextMonthN_CommandHandler, prevYear_CommandHandler,
                            prevYearN_CommandHandler,nextYear_CommandHandler,
                            nextYearN_CommandHandler
                           );
   
      static SimpleDate Previous;
      static SimpleDate Current;
   
      static final String[] MonthNames = 
         {"","January","February","March","April","May","June",
             "July","August","September","October","November","December"};

   // main method of program
      public static void main (String[] args) throws IOException {
         System.out.println("CalendarGUI ... ");
         Previous = new SimpleDate();
         Current = new SimpleDate(getString(args,0));
         MTPrevious = new MonthTable("PREVIOUS: ",Previous,200,100);
         MTCurrent = new MonthTable("CURRENT: ",Current,200,300);
         display("Initially");
         CBs.Show();
      }
   }
