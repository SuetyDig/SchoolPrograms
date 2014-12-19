import java.io.*;
import java.util.*;

public class StudentArrayTest {

	public static void main(String argv[]) throws IOException {
		Comparator<Student> sc = new ByClassAlpha();
		StudentArraySupport ss = new StudentArraySupport();
		Student[] ST = ss.constructArray("StudentData.txt");
		Arrays.sort(ST, 0, ss.getAmount(), sc);
		Integer actualSize = ss.getAmount();
		for(int i = 0; i<actualSize; i++){
			System.out.println(ST[i].toString());
		}
	}
}