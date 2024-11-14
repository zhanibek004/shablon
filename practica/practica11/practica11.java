import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        HotelFacade hotelFacade = new HotelFacade();
        hotelFacade.BookRoom();
    }
}

class RoomBookingSystem{
    public void MakeReservations(int roomNum, Date StartTime, Date EndTime, int peopleAmount){
    }
    public void Payment(double Pay){

    }

}
class CleaningService{
    public void Notify(Date StartDate, Date EndDate, int roomNum){

    }
}
class RestaurantService{
    public void Notify(int roomNum, int peopleAmount){

    }
}
class HotelFacade{
    public void BookRoom(Date StartDate, Date EndTime, int peopleAmount, int roomNum){
        RoomBookingSystem roomBookingSystem = new RoomBookingSystem();
        roomBookingSystem.MakeReservations(roomNum, StartDate, EndTime, peopleAmount);
        roomBookingSystem.Payment(500);

        CleaningService cleaningService = new CleaningService();
        cleaningService.Notify(StartDate, EndTime, roomNum);

        RestaurantService restaurantService = new RestaurantService();
        restaurantService.Notify(peopleAmount, 200);
    }
}
