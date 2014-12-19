/* Adam Miller
	Programming Assignment #1
	Converts Farenheit to Celsious then back to Farenheit
*/

public class TemperatureConversion
{
   public static void main( String [] args )
   {	
	//declares temperature in Fahrenheit as an int
	int farenheit = 212;
	
	//***** 3. calculate equivalent Celsius temperature	
	double celsious = (farenheit - 32) * 5.0 / 9.0;

	//prints the temperature in Celsius
	System.out.println(celsious);
	
	//converts the Celsius temperature back to Fahrenheit
	double farenheit2 = (9.0 * celsious/ 5.0)  + 32;
	
	//prints the temperature in Farenheit 
	System.out.println(farenheit2);					
   }
} 