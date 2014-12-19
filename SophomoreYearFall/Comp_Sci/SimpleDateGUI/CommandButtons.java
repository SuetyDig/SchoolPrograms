   //import javax.swing.JOptionPane;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
    public class CommandButtons  extends JFrame {
    
      private final int LIMIT = 64;		      private int limit = LIMIT;
      private final int WIDTH = 450;			private int width = WIDTH;
      private final int HEIGHT = 175;			private int height = HEIGHT;
   	
      private int n = 0;
   	
      private String[] command;
      private Container container;
      private JLabel promptLabel;
      private JButton[] button = new JButton[limit];
      private ButtonHandler bh = new ButtonHandler();
      private int result;
   	
      private String prompt = "";
   
       public CommandButtons() {
         Initialize();
      }
   
       public CommandButtons(String[] Command, ActionListener ... AL) {
         Initialize(Command,AL);
      }
   
       public CommandButtons(String Prompt) {
         prompt = Prompt;
         Initialize();
      }
   	
       public CommandButtons(String Prompt, String[] Command, ActionListener ... AL) {
         prompt = Prompt;
         Initialize(Command,AL);
      }
   	
       private void Initialize() {
         command = new String[limit];
         container = getContentPane();
         container.setLayout(new FlowLayout());
         promptLabel = new JLabel(prompt);
         container.add(promptLabel);
      }
   	
       private void Initialize(String[] Command, ActionListener[] AL) {
         limit = Math.max(limit,Math.max(Command.length, AL.length));
         Initialize();
         for(int i=0; i<Math.min(Command.length, AL.length); i++) {
            AddCommand(Command[i],AL[i]);
         }
      }
   	
       private int Expand(int Oldlimit, int Newlimit) {
         int result = Oldlimit;
         if(Oldlimit < Newlimit) {
            result = Newlimit;
            String[] commandCOPY = new String[Newlimit];
            JButton[] buttonCOPY = new JButton[Newlimit];
            for(int i=0; i<Oldlimit; i++) {
               commandCOPY[i] = command[i];	buttonCOPY[i] = button[i];
            }
            command = new String[Newlimit];
            button = new JButton[Newlimit];
            for(int i=0; i<Oldlimit; i++) {
               command[i] = commandCOPY[i];	button[i] = buttonCOPY[i];
            }
         }
         return result;
      }
   	
       public boolean Show() {
         setSize(width,height);
         setVisible(true);
         setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
         return true;
      }
   	
       public boolean Show(int Width, int Height) {
         boolean result = false;
         if((Width > 0) && (Height > 0)) {
            result = true;
            width = Width;
            height = Height;
         }
         return Show();
      }
   	
       public int AddCommand(String Command, ActionListener AL) {
         int result = -1;
         Command = Command.trim();
         if(Command.length() > 0) {
            if(n >= limit) {
               limit = Expand(limit,(limit+LIMIT));
            }
            command[n] = Command;
            button[n] = new JButton(Command);
            container.add(button[n]);
            button[n].addActionListener(AL);
            result = n;
            n = n + 1;
         }
         return result;
      }
   	
       public int CommandInt(String Command) {
         int result = -1;
         int index;
         for(index=0; (result==-1) && (index<n); index++) {
            if(command[index].equals(Command)) {
               result = index;
            }
         }
         return result;
      }
   	
       public JButton CommandJButton(String Command) {
         JButton result = null;
         int index;
         for(index=0; (result==null) && (index<n); index++) {
            if(command[index].equals(Command)) {
               result = button[index];
            }
         }
         return result;
      }
   
   /*   	
       public int nextCommand() {
         result = -2;
         while(result == -2) {
         }
         System.out.println("(" + result + ")");
         return result;
      }
   */
   
    // private inner class event handler
       private class ButtonHandler implements ActionListener {
      // implement actionPerformed method
          public void actionPerformed( ActionEvent ae ) {
            int result = -1;
            int index;
            for(index=0; (result==-1) && (index<n); index++) {
               if(ae.getSource() == button[index]) {
                  result = index;
                  System.out.println(result);
               }
            }
         }
      
      }
   }