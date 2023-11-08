package filter;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/** * Secure password encryption service. * * Example usage: * <pre> * PasswordService passwordService = new PasswordService(); * String salt = passwordService.generateSalt(); * String encryptedPassword = passwordService.encrypt(password, salt); * </pre> */
public final class NewClass {

private static final int SALT_LENGTH =16; private static final String ALLOWED_SALT_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

public NewClass()
{ }

/** * Generates a random String that can be used as a password salt. * @return a random String of length 8 using only alphanumeric characters. */
public final String generateSalt()
{ final SecureRandom random = new SecureRandom();
  final StringBuilder salt = new StringBuilder();
  for (int i = 0; i < SALT_LENGTH; i++)
  { salt.append(ALLOWED_SALT_CHARS.charAt(random.nextInt(ALLOWED_SALT_CHARS.length()))); }
  return salt.toString(); }

/** * Encrypts the given plaintext password, using the given salt. The encrypted form is * a message digest generated with the SHA-1 algorithm, and rendered in String format using Base-64 * encoding. * * @param plaintext password to encrypt * @param salt salt to add to password to ensure encryption is secure * @return encrypted password * @throws NullPointerException if either plaintext or salt are null */
public final String encrypt(final String plaintext, final String salt)
{ if (plaintext == null) { throw new NullPointerException(); }
  if (salt == null) { throw new NullPointerException(); }

try { final MessageDigest md = MessageDigest.getInstance("SHA"); md.update((plaintext + salt).getBytes("UTF-8"));
return new BASE64Encoder().encode(md.digest()); }
catch (NoSuchAlgorithmException e)
{ throw new EncryptionException(e); }
catch (UnsupportedEncodingException e)
{ throw new EncryptionException(e); } }


public class EncryptionException extends RuntimeException {

public EncryptionException(Throwable cause) { super(cause); }

}

  public static void main(String args[]) throws Exception
   {
NewClass passwordService = new NewClass();
String salt = passwordService.generateSalt();
String encryptedPassword = passwordService.encrypt("D:/*.xml", salt);
//System.out.println("sALT IS  " +salt);
//System.out.println("PASS "+encryptedPassword);
   }
}