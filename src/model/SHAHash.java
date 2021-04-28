package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHAHash {
    public String getHash(String toHash) throws NoSuchAlgorithmException {
        //Referenced code from [1]
        MessageDigest MD = MessageDigest.getInstance("SHA-256");
        MD.update(toHash.getBytes());

        byte[] digest = MD.digest();
        StringBuffer StringBuffer = new StringBuffer();
        for (byte b : digest){
            StringBuffer.append(String.format("%02x", b & 0xff));
        }
        return StringBuffer.toString();
    }

}
