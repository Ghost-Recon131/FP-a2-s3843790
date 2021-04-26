package model;

import dao.EmployeeDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

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

    public int Randomint(){
        EmployeeDAO EDAO = new EmployeeDAO();
        Random R = new Random();
        int random = R.nextInt(10000);
        int backup = R.nextInt(10000);
        int returnvalue;

        if(random != EDAO.getAccountID(random)){ //getAccountID will return -1 if there are no matches
            returnvalue = random;
        }
        else if(random != EDAO.getAccountID(random)){
            returnvalue = backup;
        }
        else{ // in the unlikely event of a match, we will introduce another random variable
            returnvalue = random + backup;
        }
        return returnvalue;
    }
}
