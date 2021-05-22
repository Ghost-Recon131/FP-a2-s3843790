package controller.UserFeatures;

import controller.utils.ChangeSceneUtil;
import controller.utils.TableStatusUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import model.UserModel.BookTableModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

public class BookTableController implements Initializable {
    private LocalDate SelectedDate;
    private int TableChosen = 0;
    @FXML private Label DateError, TableError, TableError2, PickedTable, PickDate, ReserveTableError, CurrentBooking;
    @FXML private Button B1, B2, B3, B4, B5, B6, B7, B8, B9, B10;
    @FXML private Rectangle R1, R2, R3, R4, R5, R6, R7, R8, R9, R10;
    ChangeSceneUtil CSU = new ChangeSceneUtil();
    TableStatusUtil TSU = new TableStatusUtil();
    BookTableModel BTM = new BookTableModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TSU.SetTableColour(R1, 1); // first part sets table colours
        TSU.SetTableColour(R2, 2);
        TSU.SetTableColour(R3, 3);
        TSU.SetTableColour(R4, 4);
        TSU.SetTableColour(R5, 5);
        TSU.SetTableColour(R6, 6);
        TSU.SetTableColour(R7, 7);
        TSU.SetTableColour(R8, 8);
        TSU.SetTableColour(R9, 9);
        TSU.SetTableColour(R10, 10);
        B1.setOnAction(e-> {TableChosen = 1; ShowTableChosen();}); // this part determines which table the user choose
        B2.setOnAction(e-> {TableChosen = 2; ShowTableChosen();});
        B3.setOnAction(e-> {TableChosen = 3; ShowTableChosen();});
        B4.setOnAction(e-> {TableChosen = 4; ShowTableChosen();});
        B5.setOnAction(e-> {TableChosen = 5; ShowTableChosen();});
        B6.setOnAction(e-> {TableChosen = 6; ShowTableChosen();});
        B7.setOnAction(e-> {TableChosen = 7; ShowTableChosen();});
        B8.setOnAction(e-> {TableChosen = 8; ShowTableChosen();});
        B9.setOnAction(e-> {TableChosen = 9; ShowTableChosen();});
        B10.setOnAction(e-> {TableChosen = 10; ShowTableChosen();});
    }

    private void ShowTableChosen(){ // gives user visual confirmation of their chosen table
        PickedTable.setText("You have picked Table " + TableChosen);
    }

    //todo Implement controller & model
    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/UserView/userHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }

    @FXML private DatePicker Date; // get the date selected by the user
    public void setDatePickerAction(){
        SelectedDate = Date.getValue();
        PickDate.setText("You selected date: " + SelectedDate.toString());
    }

    @FXML
    public void setReserveTableButtonClick(){
        boolean error1, error2, error3, error4;
        error1 = BTM.CorrectDate(SelectedDate, DateError);
        error2 = BTM.TableAvailable(TableChosen, TableError);
        error3 = BTM.HasBooking();
        error4 = BTM.NotSameTable(TableChosen, TableError2);

        if(error1 && error2 && !error3 && error4){
            BTM.PlaceBooking(TableChosen, SelectedDate);
            ReserveTableError.setTextFill(GREEN);
            ReserveTableError.setText("Booking successful!");
        }else{
            ReserveTableError.setTextFill(RED);
            ReserveTableError.setText("Booking failed!");
        }
    }

    public void DisplayMessage(){
        if(BTM.HasBooking()){
            CurrentBooking.setText(
                    "Your your booking is on: " + BTM.getBookingDate() + "\n" +
                    "Your your Table is : " + BTM.getTableNumber() + "\n" +
                    "Your your Booking ID is : " + BTM.getCurrentBookingID() + "\n");
        }else{
            CurrentBooking.setText("No reservations found, please select a date then a \n + table that is coloured Green.");
        }
    }

}
