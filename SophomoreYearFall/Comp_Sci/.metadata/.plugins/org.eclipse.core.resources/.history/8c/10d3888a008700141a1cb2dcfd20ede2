import java.text.DecimalFormat;

/* For the purpose of testing the Polynomial class.
*/

public class PolynomialTester {

   public static void main(String[] args) {

      // for formatting real numbers when they are displayed
      DecimalFormat df = new DecimalFormat("#####0.0########");

      // Create polynomials p and q.
      Polynomial p = new Polynomial(new int[] {-1, 3, 4, 0, 2});
      Polynomial q = new Polynomial(new int[] {5, 0, -3, -1});

      // Display the polynomials (which tests the toString() method).
      System.out.println("p, of degree " + p.degreeOf() + ", is " + p);

      System.out.println("q, of degree " + q.degreeOf() + ", is " + q);

      // Display the sum, difference, and product of p and q.
      System.out.println("p+q is " + p.sum(q));
      System.out.println("p-q is " + p.difference(q));
      System.out.println("p*q is " + p.product(q));

      // Evaluate p and q at a particular value of x.
      double x = 5.4;
      System.out.println("p(" + x + ") is " + df.format(p.evaluateAt(x)));
      System.out.println("q(" + x + ") is " + df.format(q.evaluateAt(x)));

      // Establish interval in which to search for roots/zeros of p and q.
      double xLow = -0.5, xHigh = 5.0;
      double eps = 0.000005;  // for epsilon

      // Search for root of p and display result.
      double z = p.rootOf(xLow, xHigh, eps);
      System.out.println("p(" + z + ") is " + df.format(p.evaluateAt(z)));

      // Search for root of q and display result.
      z = q.rootOf(xLow, xHigh, eps);
      System.out.println("q(" + z + ") is " + df.format(q.evaluateAt(z)));
   }

}
