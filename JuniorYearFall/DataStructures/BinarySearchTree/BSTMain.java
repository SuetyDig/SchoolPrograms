   import scranton.tree.*;
   import java.util.*;
   import java.io.*;
	//import scranton.*;

    public class BSTMain {
   
       public static String order(BinarySearchTree<Integer> T){
         if(T.isEmpty()){
            return "o";
         } 
         else {
            return "("+order(T.leftSubtree()) +"\t"+ T.getRoot().toString() + "\t" + order(T.rightSubtree())+")";
         }
      }
   
       public static String revOrder(BinarySearchTree<Integer> T){
         if(T.isEmpty()){
            return "";
         } 
         else {
            return revOrder(T.rightSubtree()) + T.getRoot().toString() + "\t" + revOrder(T.leftSubtree());
         }
      }
		   
       public static void main(String[] argv) throws IOException{
         Comparator<Integer> c = new AscendingInteger();
         Random r = new Random();
         BinarySearchTree<Integer> T = new BinarySearchTree<Integer>(c);
         for(int i=0; i<15; i++){
				Integer root = new Integer(1000+r.nextInt(1000));
				//System.out.print(root+"\t");				
            T.insert(root);
         	//System.out.println(order(T));
				//System.out.println(T.toString());
         }
			System.out.println();
         System.out.println(T.toString());
         //System.out.println(revOrder(T));
/* */
         System.out.println(T.sideways("", '*', 0));
         System.out.println("------------------------------------------------------");
         //System.out.println("\n\n"+T.toString());
         if(!(T.leftSubtree().isEmpty())
         && !(T.leftSubtree().rightSubtree().isEmpty())){
            System.out.println("lrPromote");
            T.lrPromote();
            System.out.println(T.sideways("", '*', 0));
            System.out.println("------------------------------------------------------");
            System.out.println("Clockwise");
            T.clockwiseRotate();
            System.out.println(T.sideways("", '*', 0));
            System.out.println("------------------------------------------------------");
         }
         if(!(T.rightSubtree().isEmpty())
         && !(T.rightSubtree().leftSubtree().isEmpty())){
            System.out.println("rlPromote");
            T.rlPromote();
            System.out.println(T.sideways("", '*', 0));
            System.out.println("------------------------------------------------------");
            System.out.println("Counter clockwise");
            T.counterClockwiseRotate();
            System.out.println(T.sideways("", '*', 0));
            System.out.println("------------------------------------------------------");
         }
/* */
      }
   }