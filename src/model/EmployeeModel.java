package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EmployeeModel {
    private int id;
    private String Firstname;
    private String Lastname;
    private String Username;
    private String Password;
    private String Role;
    private String Secret_Question;
    private String SQ_Answer;

    public EmployeeModel(int id, String Firstname, String Lastname, String Username, String Password, String Role,
                         String Secret_Question, String SQ_Answer){
        this.id = id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
        this.Secret_Question = Secret_Question;
        this.SQ_Answer = SQ_Answer;
    }

//    public String getHash(String toHash) throws NoSuchAlgorithmException {
//        //Referenced code from [1]
//        MessageDigest MD = MessageDigest.getInstance("SHA-256");
//        MD.update(toHash.getBytes());
//
//        byte[] digest = MD.digest();
//        StringBuffer StringBuffer = new StringBuffer();
//        for (byte b : digest){
//            StringBuffer.append(String.format("%02x", b & 0xff));
//        }
//        return StringBuffer.toString();
//    }

    SHAHash HASH = new SHAHash();

    public int getID() {
        return id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getFullName() {
        return Firstname + " " + Lastname;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() throws NoSuchAlgorithmException {
        return HASH.getHash(Password);
    }

    //this getter is used to retrieve a hashed password from the database
    //otherwise the hashed password will be hashed again before being returned
    //via the EmployeeDAO
    public String getHashedPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }

    public String getSecretQuestion() {
        return Secret_Question;
    }

    public String getSQAnswer() throws NoSuchAlgorithmException {
        return HASH.getHash(SQ_Answer);
    }

    //same logic as with password
    public String getHashedSQAnswer(){
        return SQ_Answer;
    }
}
