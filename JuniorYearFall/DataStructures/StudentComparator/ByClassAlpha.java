import java.util.*;

public class ByClassAlpha implements Comparator<Student>{

	public int compare(Student left, Student right){
      int leftYear = 0;
      int rightYear = 0;
      
      //Sets the left year as an int instead of a String
		if(left.getUnivClass().toUpperCase().compareTo("FRESHMAN") == 0) leftYear = 1;
      else if(left.getUnivClass().toUpperCase().compareTo("SOPHOMORE") == 0) leftYear = 2;
      else if(left.getUnivClass().toUpperCase().compareTo("JUNIOR") == 0) leftYear = 3;
      else if(left.getUnivClass().toUpperCase().compareTo("SENIOR") == 0) leftYear = 4;
      else if(left.getUnivClass().toUpperCase().compareTo("GRADUATE") == 0) leftYear = 5;
      
      //Sets the right year as an int instead of a String
      if(right.getUnivClass().toUpperCase().compareTo("FRESHMAN") == 0) rightYear = 1;
      else if(right.getUnivClass().toUpperCase().compareTo("SOPHOMORE") == 0) rightYear = 2;
      else if(right.getUnivClass().toUpperCase().compareTo("JUNIOR") == 0) rightYear = 3;
      else if(right.getUnivClass().toUpperCase().compareTo("SENIOR") == 0) rightYear = 4;
      else if(right.getUnivClass().toUpperCase().compareTo("GRADUATE") == 0) rightYear = 5;
      
      //Compares the two years returns -1 if the left is in a lower grade then the right
      //And 1 if the left is in a higher grade then the right
      if(leftYear < rightYear) return -1;
      else if(leftYear > rightYear) return 1;  
      //If the two years are the same then it compares the two lexicographically
      else
      {
         return left.getName().compareTo(right.getName());
      }     
	}
}