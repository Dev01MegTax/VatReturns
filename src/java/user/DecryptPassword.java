package user;
import javax.crypto.*;
import javax.crypto.spec.*;
public class DecryptPassword
{
  public static  String Decrypt(byte [] encryptedBytes, String sessionPassword)throws Exception
  {       
     PBEKeySpec pbeKeySpec;
     PBEParameterSpec pbeParamSpec;
     SecretKeyFactory keyFac;

     //Salt
     byte[] salt = {(byte) 0x9f, (byte) 0x33, (byte) 0x4e, (byte) 0xfe, (byte) 0xd4, (byte) 0xee, (byte) 0x12, (byte) 0x54};

     //Iteration count
     int count = 17;

     // Create PBE parameter set
     pbeParamSpec = new PBEParameterSpec(salt, count);
     
     char[] password = sessionPassword.toCharArray();
     pbeKeySpec = new PBEKeySpec(password);
     
     keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
     SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

     // Create PBE Cipher
     Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

     // Initialize PBE Cipher with key and parameters
     pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
     System.out.println("Encrypting file using DES/ECB/PKCS5Padding");

     System.out.println("Reading encrypted file and decrypting...");
     
        // Read the next chunk of bytes...
     byte[] decryptedBytes = pbeCipher.update(encryptedBytes);
  
  byte[] c1=pbeCipher.doFinal();
  return (new String(decryptedBytes)) + (new String(c1));
  }      
     
      public static void main(String args[]) throws Exception
   {
  EncryptPassword ep=new EncryptPassword();
  String opname="biplob";
  byte [] encryptedValue = ep.Encrypt(opname, "12345");
  System.out.println(Decrypt(encryptedValue, "12345"));
   }


}  