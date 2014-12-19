   import java.io.*;
   import java.util.*;

    public class StudentArraySupport {
   
      Integer amount; 
   
       public Student[] constructArray(String fileName)throws IOException{
      
         Student[] ans = new Student[10];
         Scanner Sc = new Scanner(new File(fileName));
			Sc.useDelimiter("\t|\n");
         this.amount = 0;
         while(Sc.hasNext()){
            if(this.amount == ans.length){
               ans = doubler(ans);
            }
				String ID = Sc.next();
				String SName = Sc.next();
				String Email = Sc.next();
				String SClass = Sc.next();
				String major = Sc.next();
            ans[this.amount] = new Student(ID, SName, Email, SClass, major);
				//System.out.println(amount+": "+ans[this.amount].toString());
				amount++;
         }
         return ans;
      }
   
       public Integer getAmount(){
         return this.amount;}
   
       private Student[] doubler(Student[] a){
         Student[] newArray = new Student[2*(a.length)];
         for(int i = 0; i<a.length; i++){
            newArray[i] = a[i];
         }
         return newArray;
      }
   }