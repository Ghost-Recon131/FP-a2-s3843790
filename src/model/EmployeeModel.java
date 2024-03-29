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
    private String Status;
    private int PreviousTableNumber;

    public EmployeeModel(int id, String Firstname, String Lastname, String Username, String Password, String Role,
                         String Secret_Question, String SQ_Answer, String Status, int PreviousTableNumber){
        this.id = id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
        this.Secret_Question = Secret_Question;
        this.SQ_Answer = SQ_Answer;
        this.Status = Status;
        this.PreviousTableNumber = PreviousTableNumber;
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

    public String getStatus() {
        return Status;
    }

    public int getPreviousTableNumber() {
        return PreviousTableNumber;
    }
}
