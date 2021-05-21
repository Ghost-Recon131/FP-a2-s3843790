package controller.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHAHashUtil {
    private String HashedResult;
    public String getHash(String toHash) {
        try {
            //Referenced code from [1]
            MessageDigest MD = MessageDigest.getInstance("SHA-256");
            MD.update(toHash.getBytes());

            byte[] digest = MD.digest();
            StringBuffer StringBuffer = new StringBuffer();
            for (byte b : digest) {
                StringBuffer.append(String.format("%02x", b & 0xff));
            }
            HashedResult = StringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Bug in hashing method");
        }
        return HashedResult;
    }

}
