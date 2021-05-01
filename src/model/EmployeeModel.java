package model;



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

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }

    public String getSecretQuestion() {
        return Secret_Question;
    }

    public String getSQAnswer() {
        return SQ_Answer;
    }
}
