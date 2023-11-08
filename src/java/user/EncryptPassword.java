
package user;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;

public class EncryptPassword
{
  public static byte[] Encrypt(String opname, String sessionPassword) throws Exception
  {       
     
      PBEKeySpec pbeKeySpec;
      PBEParameterSpec pbeParamSpec;
      SecretKeyFactory keyFac;

      byte[] salt = {(byte)0x9f, (byte)0x33, (byte)0x4e, (byte)0xfe, (byte)0xd4, (byte)0xee, (byte)0x12, (byte)0x54};

   
      int count = 17;


        pbeParamSpec = new PBEParameterSpec(salt, count);


        char[] password = sessionPassword.toCharArray();
        
     
      pbeKeySpec = new PBEKeySpec(password);
      keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
      SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

      Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

      // Initialize PBE Cipher with key and parameters
      pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
      
      System.out.println("Reading cleartext file and encrypting...");
      
  
          // Read the next chunk of bytes...
          byte[] cleartextBytes = opname.getBytes();
           // Now, encrypt them and write them to the encrypted file...
        byte[] encryptedBytes = pbeCipher.update(cleartextBytes);
        byte[] c1=pbeCipher.doFinal();
  System.out.println("length 2 is " + c1.length);
  byte[] ret = new byte[encryptedBytes.length + c1.length];
  System.arraycopy(encryptedBytes, 0, ret, 0, encryptedBytes.length);
  System.arraycopy(c1, 0, ret, encryptedBytes.length, c1.length);

  return ret;
     }
 
}
   