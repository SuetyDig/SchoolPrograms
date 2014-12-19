import java.io.*;

class StarPowerDriver 
{
   
   public static void main(String[] argv) throws IOException
   {
       StarPuzzle Star = new StarPuzzle();
       Star.initStarPuzzle("SampleInput.txt");
       Star.solve();
       Star.drawSolution();
       System.out.println(Star.toString());
   }
}