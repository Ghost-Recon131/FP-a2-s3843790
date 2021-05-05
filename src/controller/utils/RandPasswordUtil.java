package controller.utils;
import java.security.SecureRandom;

public class RandPasswordUtil {
    private String newPassword = ""; // need to have some kind of value before it enters a loop or it will enter in "null"
    private final String CapAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String lowAlphabet = CapAlphabet.toLowerCase();
    private final String Numbers = "1234567890";
    private final String Symbols = "?!@#$%^&*_=+-";
    String MaxSecurity = Symbols + CapAlphabet + Numbers + lowAlphabet;

    SecureRandom SRandom = new SecureRandom(); // a more secure random generator from Java

    // picks random value from the combined string of characters
    private String generatePassword(int length, String Dictionary) {
        for (int i = 0; i < length; i++) {
            int location = SRandom.nextInt(Dictionary.length());
            newPassword += Dictionary.charAt(location);
        }
        return newPassword;
    }

    // length will be set to 20, no real reason to get the user to specify a length
    public String getRandomPassword(){
        return generatePassword(20, MaxSecurity);
    }
}
