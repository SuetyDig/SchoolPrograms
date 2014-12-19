   import java.io.*;

    public class Student {
   
      protected String RID;
      protected String Name;
      protected String email;
      protected String UnivClass;
      protected String major;
   
       public Student(String RID, String Name, String email, String UnivClass, String major){
         this.RID = RID;
         this.Name = Name;
         this.email = email;
         this.UnivClass = UnivClass;
         this.major = major;
      }
   
       public String getRID(){
         return this.RID;}
   	
       public String getName(){
         return this.Name;}
   	
       public String getEmail(){
         return this.email;}
   	
       public String getUnivClass(){
         return this.UnivClass;}
   	
       public String getMajor(){
         return this.major;}
   	
       public String toString(){
         return this.getRID()+"\t"+this.getName()+"\t"+this.getEmail()+"\t"+this.getUnivClass()+"\t"+this.getMajor();
      }
   	
   }