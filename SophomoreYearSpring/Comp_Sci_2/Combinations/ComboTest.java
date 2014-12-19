import java.io.*;
import java.util.*;

public class ComboTest {

	public static void main(String[] arg) throws IOException{
		Combinations c = new Combinations();
		c.reset();
		System.out.println(c.Combo(10, 2)+"\t"+c.viewCounter());
		c.reset();
      System.out.println(c.Combo(6, 2)+"\t"+c.viewCounter());
		c.reset();
		System.out.println(c.Combo(10, 1)+"\t"+c.viewCounter());
		c.reset();
		System.out.println(c.Combo(52, 52)+"\t"+c.viewCounter());
		c.reset();
		System.out.println(c.Combo(52, 5)+"\t"+c.viewCounter());
	}
}