//Adam Miller
//Solution to Programming Assignment #7 Fall 2012

/** An instance of this class can perform simple encryption and decryption
**  based upon a key provided at construction.
*/

public class EncrypterDecrypter {

   // instance variables
   // ------------------
   private String keyReg;
   private String keyScramb;


   // constructor
   // -----------

   /** Establishes the key that this object uses when performing encryption
   **  or decryption.  The key, which is described by the strings provided
   **  via the two arguments, is a mapping (i.e., function) from characters
   **  to characters.  If x and y are characters such that x maps to y, we
   **  refer to y as the "image" of x and to y as the "inverse image" of x.
   **  In encrypting a string, each character is replaced by its image.  In
   **  decrypting a string, each character is replaced by its inverse image.
   **  The mapping described by the two arguments is as follows:
   **  For each i in the range 0..from.length()-1, the character from.charAt(i)
   **  maps to to.charAt(i).  Any character not occurring in 'from' maps to
   **  itself.
   **  
   **  pre-condition:
   **    from.length() == to.length()  && 
   **    from.charAt(i) == from.charAt(j) implies i==j  &&
   **    to.charAt(i) == to.charAt(j) implies i==j  &&
   **    for every character x, occursIn(x, from) == occursIn(x, to)
   **
   **  In informal terms, the pre-condition says that the two strings provided
   **  as arguments must include the same set of characters, none appearing more
   **  than once.  This ensures that the mapping they describe is injective.
   */
   public EncrypterDecrypter(String from, String to)
   {
   keyReg = from;
   keyScramb = to;
   }

   // generators
   // ----------

   /** Returns the encrypted form of the given string (plaintext).
   **  Specifically, the string returned is obtained by replacing each
   **  character in plaintext by its image.
   */
   public String encrypt(String plainText)
   { 
      String result = "";
      for(int i = 0; i < plainText.length(); i++) {
         if(keyReg.indexOf(plainText.charAt(i)) >= 0) {
            result += keyScramb.charAt(keyReg.indexOf(plainText.charAt(i)));
         } else { result += plainText.charAt(i); }
      }
           
      return result;
   }



   /** Returns the decrypted form of the given string (ciphertext).
   **  Specifically, the string returned is obtained by replacing each
   **  character in ciphertext by its inverse image.
   */
   public String decrypt(String cipherText) 
   { 
      String result = "";
      for(int i = 0; i < cipherText.length(); i++) {
         if(keyScramb.indexOf(cipherText.charAt(i)) >= 0) {
            result += keyReg.charAt(keyScramb.indexOf(cipherText.charAt(i)));
         } else { result += cipherText.charAt(i); }
      }
      return result;
    }
}
