package model.AdminModel;
import controller.utils.IntegerCheckUtil;
import dao.BookingsDAO;
import javafx.scene.control.Label;


public class ManageViewBookingsModel {
    BookingsDAO BDAO = new BookingsDAO();
    IntegerCheckUtil ICU = new IntegerCheckUtil();

    public int ToInt(String BookingID){
        return ICU.verifyInteger(BookingID);
    }

    public void ApproveBooking(String StringBookingID, Label label){
        BDAO.updateBookings();
        int BookingID = ToInt(StringBookingID);
        String Status = BDAO.getBookingStatus(BookingID);

        if(BookingID == -1){
            label.setText("Please enter numbers only!");
        }

        if(!BDAO.VerifyBookingID(BookingID)){
            label.setText("Please check the entered BookingID");
            return;
        }

        if(BDAO.VerifyBookingID(BookingID) && Status.equals("pending") || Status.equals("rejected")){
            BDAO.approveBooking(BookingID);
            label.setText("Booking approved");
        }else if(Status.equals("approved")) {
            label.setText("Booking is already approved!");
        }
    }

    public void RejectBooking(String StringBookingID, Label label){
        BDAO.updateBookings();
        int BookingID = ToInt(StringBookingID);
        String Status = BDAO.getBookingStatus(BookingID);

        if(BookingID == -1){
            label.setText("Please enter numbers only!");
        }

        if(!BDAO.VerifyBookingID(BookingID)){
        label.setText("Please check the entered BookingID");
            return;
        }

        if(BDAO.VerifyBookingID(BookingID) && Status.equals("pending") || Status.equals("approved")){
            BDAO.rejectBooking(BookingID);
            label.setText("Booking rejected!");
        }else if(Status.equals("rejected")) {
            label.setText("Booking is already rejected!");
        }
    }

}
