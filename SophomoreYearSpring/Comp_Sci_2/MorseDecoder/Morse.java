   import scranton.tree.*;
   import java.io.*;
   import java.util.*;

   public class Morse extends RecursiveBinaryTreeStandard<String>{
   
      public Morse(){super();}
   
      public Morse(String sym){
         super(sym);
      }
   	
      public Morse emptyTree(){
         return new Morse();
      }
   	
      public Morse leftSubtree(){
         return (Morse)super.leftSubtree();
      }
   	
      public Morse rightSubtree(){
         return (Morse)super.rightSubtree();
      }
   
      public void initialize(String fileName) throws IOException {
         BufferedReader buf = new BufferedReader(new FileReader(fileName));
         while(buf.ready()){
            String data = buf.readLine();
            String letter = data.substring(0,1);
            String code = data.substring(2, data.length());
            this.buildTree(letter, code);
         }
      }
   
      public void buildTree(String Letter, String Code){
         String blank = " ";
         if(this.isEmpty()) this.swap( new Morse(blank));
         if(Code.length()==0) this.setRoot(Letter);
         else if (Code.charAt(0)=='.') this.leftSubtree().buildTree(Letter, Code.substring(1, Code.length()));
         else if (Code.charAt(0)=='-') this.rightSubtree().buildTree(Letter, Code.substring(1, Code.length()));
         else assert false: "Unexpected symbol in '"+ Code+"'";
      }
   
      public void verify(String code){
         String blank = " ";
         if(!this.isEmpty()){
            if(!this.getRoot().equals(blank))System.out.println(this.getRoot()+": "+code);
            this.leftSubtree().verify(code+".");
            this.rightSubtree().verify(code+"-");
         }
      }
   	
      public String translate(String code){
         assert code.length()==0 || code.charAt(0)=='.' || code.charAt(0)=='-': "*** Illegal Morse code symbol = "+code.charAt(0);

         // Creates a new Morse tree called holder and set it equal to the left or right
         // Subtree depending on the starting character
         Morse holder = new Morse(); 
         if(code.substring(0,1).equals(".")) holder = this.leftSubtree();
         if(code.substring(0,1).equals("-")) holder = this.rightSubtree();
                    
         // Increments through the current code to find the proper character           
         for(int i = 1; i < code.length(); i++) {
            if(code.substring(i,i+1).equals(".")) holder = holder.leftSubtree();
            if(code.substring(i,i+1).equals("-")) holder = holder.rightSubtree();
         }
         return holder.getRoot();
      }
   
   }
