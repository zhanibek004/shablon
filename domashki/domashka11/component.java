import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        UserManagementService userManagementService = new UserManagementService();
        HotelService hotelService = new HotelService();
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();

        HotelBookingFacade hotelBookingFacade1 = new HotelBookingFacade(
                hotelService, new BookingService(hotelService), paymentService, notificationService, userManagementService);
        HotelBookingFacade hotelBookingFacade2 = new HotelBookingFacade(
                hotelService, new BookingService(hotelService), paymentService, notificationService, userManagementService);
        HotelBookingFacade hotelBookingFacade3 = new HotelBookingFacade(
                hotelService, new BookingService(hotelService), paymentService, notificationService, userManagementService);

        User user = userManagementService.register("Uali Jngu", "nah123456");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a hotel:");
        System.out.println("1. Beach Resort (Malibu)");
        System.out.println("2. Mountain Inn (Colorado)");
        System.out.println("3. City Center Hotel (New York)");

        int choice = scanner.nextInt();

        HotelBookingFacade selectedFacade = null;
        String location = "";
        String roomClass = "";
        double maxPrice = 0;

        switch (choice) {
            case 1:
                selectedFacade = hotelBookingFacade1;
                location = "Malibu";
                roomClass = "Luxury";
                maxPrice = 350;
                break;
            case 2: // won't output because price range is too low
                selectedFacade = hotelBookingFacade2;
                location = "Colorado";
                roomClass = "Economy";
                maxPrice = 100;
                break;
            case 3:
                selectedFacade = hotelBookingFacade3;
                location = "New York";
                roomClass = "Standard";
                maxPrice = 450;
                break;
            default:
                System.out.println("Non-existing choice, restart.");
                return;
        }

        List<Hotel> hotels = selectedFacade.searchHotel(location, roomClass, maxPrice);
        System.out.println("Found " + hotels.size() + " hotel(s) in your price range.");

        if (!hotels.isEmpty()) {
            Hotel hotel = hotels.get(0);
            System.out.println("Selected Hotel: " + hotel.getName() + " in " + hotel.getLocation());

            Booking booking = selectedFacade.makeBooking(user.getId(), hotel.getId(), new Date(), new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000));
            System.out.println("Booking confirmed! Booking ID: " + booking.getId());
        } else {
            System.out.println("No hotels found within the given criteria.");
        }

        scanner.close();
    }
}

// User management
class User {
    private static int counter = 1;
    private final int id;
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.id = counter++;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class UserManagementService {
    private final List<User> users = new ArrayList<>();

    public User register(String username, String password) {
        User user = new User(username, password);
        users.add(user);
        return user;
    }
}

// Hotel management
class Hotel {
    private static int counter = 1;
    private final int id;
    private final String name;
    private final String location;
    private final String roomClass;
    private final double pricePerNight;

    public Hotel(String name, String location, String roomClass, double pricePerNight) {
        this.id = counter++;
        this.name = name;
        this.location = location;
        this.roomClass = roomClass;
        this.pricePerNight = pricePerNight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}

class HotelService {
    private final List<Hotel> hotels = new ArrayList<>();

    public HotelService() {
        hotels.add(new Hotel("Beach Resort", "Malibu", "Luxury", 300));
        hotels.add(new Hotel("Mountain Inn", "Colorado", "Economy", 200));
        hotels.add(new Hotel("City Center Hotel", "New York", "Standard", 450));
    }

    public List<Hotel> searchHotels(String location, String roomClass, double maxPrice) {
        return hotels.stream()
                .filter(h -> h.getLocation().equalsIgnoreCase(location) &&
                             h.getRoomClass().equalsIgnoreCase(roomClass) &&
                             h.getPricePerNight() <= maxPrice)
                .collect(Collectors.toList());
    }
}

// Booking
class Booking {
    private static int counter = 1;
    private final int id;
    private final int userId;
    private final int hotelId;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Booking(int userId, int hotelId, Date checkInDate, Date checkOutDate) {
        this.id = counter++;
        this.userId = userId;
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getId() {
        return id;
    }
}

class BookingService {
    private final HotelService hotelService;
    private final List<Booking> bookings = new ArrayList<>();

    public BookingService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public Booking makeBooking(int userId, int hotelId, Date checkInDate, Date checkOutDate) {
        Booking booking = new Booking(userId, hotelId, checkInDate, checkOutDate);
        bookings.add(booking);
        return booking;
    }
}

// Payment
class PaymentService {
    public boolean processPayment(int orderId, double amount) {
        return true;
    }
}

// Notification
class NotificationService {
    public void sendBookingConfirmation(int userId, int bookingId) {
        System.out.println("Booking confirmation to User " + userId + ": your booking with ID " + bookingId + " is confirmed.");
    }
}

// Facade
class HotelBookingFacade {
    private final HotelService hotelService;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final UserManagementService userService;

    public HotelBookingFacade(HotelService hotelService, BookingService bookingService, PaymentService paymentService, NotificationService notificationService, UserManagementService userService) {
        this.hotelService = hotelService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    public List<Hotel> searchHotel(String location, String roomClass, double maxPrice) {
        return hotelService.searchHotels(location, roomClass, maxPrice);
    }

    public Booking makeBooking(int userId, int hotelId, Date checkInDate, Date checkOutDate) {
        Booking booking = bookingService.makeBooking(userId, hotelId, checkInDate, checkOutDate);
        notificationService.sendBookingConfirmation(userId, booking.getId());
        return booking;
    }
}
