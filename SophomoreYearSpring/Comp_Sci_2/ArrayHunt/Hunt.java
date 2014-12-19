   import java.io.*;
/**
 * Interface containing the basic
 *
 */
    interface Hunt {
   
   /**
   * Read the hunt data from a file
   *
   * @param FileName Name of the file containing hunt data in the correct format, sym t\ move
   */
       public void initialize(String fileName)throws IOException;
   
   /**
   * Returns a string representation of the path
   *
   * @return A string representation of the path
   */
       public String toString();
   
   /**
   * Perform the hunt
   *
   * @return the message found through the hunt process
   */
       public String hunt();
   
   }