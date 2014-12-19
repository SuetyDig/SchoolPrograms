   import javax.swing.JOptionPane;
	
    public class DialogBox {
    
      private String lastprompt = "";
      private int defaultInt = 0;
      private float defaultFloat = (float)0.0;
      private double defaultDouble = 0.0;
      private char defaultChar = ' ';
      private String defaultString = "";
   	
       public DialogBox() {
      }
   
       public DialogBox(String Prompt) {
         lastprompt = Prompt;
      }
   	
       public String nextLine() {
         String result;
         result = JOptionPane.showInputDialog(null,lastprompt);
         if(result == null) {
            result = defaultString;
         }
         return result;
      }
   
       public String nextLine(String Prompt) {
         lastprompt = Prompt;
         return nextLine();
      }
   	
       public int nextInt () {
         String input;
         boolean valid = false;
         int result = defaultInt;
         String savedprompt = lastprompt;
         do {
            input = nextLine();
            try {
               if(input != defaultString) result = Integer.parseInt(input);
               valid = true;
            }
                catch(NumberFormatException EXCEPTION) {
                  lastprompt = "REENTER: " + lastprompt;
               } 
         } while (!valid);
         lastprompt = savedprompt;
         return result;
      }
   
       public int nextInt (String Prompt) {
         lastprompt = Prompt;
         return nextInt();
      }
   
       public float nextFloat () {
         String input;
         boolean valid = false;
         float result = defaultFloat;
         String savedprompt = lastprompt;
         do {
            input = nextLine();
            try {
               if(input != defaultString) result = Float.parseFloat(input);
               valid = true;
            }
                catch(NumberFormatException EXCEPTION) {
                  lastprompt = "REENTER: " + lastprompt;
               } 
         } while (!valid);
         lastprompt = savedprompt;
         return result;
      }
   
       public float nextFloat (String Prompt) {
         lastprompt = Prompt;
         return nextFloat();
      }
   
       public double nextDouble () {
         String input;
         boolean valid = false;
         double result = defaultDouble;
         String savedprompt = lastprompt;
         do {
            input = nextLine();
            try {
               if(input != defaultString) result = Double.parseDouble(input);
               valid = true;
            }
                catch(NumberFormatException EXCEPTION) {
                  lastprompt = "REENTER: " + lastprompt;
               } 
         } while (!valid);
         lastprompt = savedprompt;
         return result;
      }
   
       public double nextDouble (String Prompt) {
         lastprompt = Prompt;
         return nextDouble();
      }
   
       public String getdefaultResponse() {
         return defaultString;
      }
   	
       public void setdefaultResponse(String S) {
         defaultString = S;
      }
      
       public int getdefaultInt() {
         return defaultInt;
      }
   	
       public void setdefaultInt(int I) {
         defaultInt = I;
      }
   
       public float getdefaultFloat() {
         return defaultFloat;
      }
   	
       public void setdefaultFloat(float F) {
         defaultFloat = F;
      }
   
       public double getdefaultDouble() {
         return defaultDouble;
      }
   	
       public void setdefaultDouble(double D) {
         defaultDouble = D;
      }
   
   
   }