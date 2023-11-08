package filter;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/** * Secure password encryption service. * * Example usage: * <pre> * PasswordService passwordService = new PasswordService(); * String salt = passwordService.generateSalt(); * String encryptedPassword = passwordService.encrypt(password, salt); * </pre> */
public final class saltgen {

    private static final int SALT_LENGTH = 16;
    private static final String ALLOWED_SALT_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public saltgen() {
    }

    /** * Generates a random String that can be used as a password salt. * @return a random String of length 8 using only alphanumeric characters. */
    public final String generateSalt() {
        final SecureRandom random = new SecureRandom();
        final StringBuilder salt = new StringBuilder();
        for (int i = 0; i < SALT_LENGTH; i++) {
            salt.append(ALLOWED_SALT_CHARS.charAt(random.nextInt(ALLOWED_SALT_CHARS.length())));
        }
        return salt.toString();
    }
}
