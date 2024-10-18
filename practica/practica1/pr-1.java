import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dealer {
    public static void main(String[] args) {
        Car car1 = new Car("Aston Martin", "Valkyrie", 2, "Automatic", new Date(116, 10, 1));
        Motorcycle bike1 = new Motorcycle("Ducati", "Panigale V4", "Sport", false, new Date(118, 8, 11));

        Garage garage1 = new Garage();
        garage1.addVehicle(car1);
        garage1.addVehicle(bike1);

        Fleet fleet = new Fleet();
        fleet.addGarage(garage1);

        fleet.search("Aston Martin");
        fleet.search("Ducati");
    }
}

abstract class Vehicle {
    private String brand;
    private String model;
    private Date year;

    public Vehicle(String brand, String model, Date year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Date getYear() {
        return year;
    }
}

class Car extends Vehicle {
    private int doorAmount;
    private String transmission;

    public Car(String brand, String model, int doorAmount, String transmission, Date year) {
        super(brand, model, year);
        this.doorAmount = doorAmount;
        this.transmission = transmission;
    }

    public int getDoorAmount() {
        return doorAmount;
    }

    public String getTransmission() {
        return transmission;
    }
}

class Motorcycle extends Vehicle {
    private String bodyType;
    private boolean boxAvailability;

    public Motorcycle(String brand, String model, String bodyType, boolean boxAvailability, Date year) {
        super(brand, model, year);
        this.bodyType = bodyType;
        this.boxAvailability = boxAvailability;
    }

    public String getBodyType() {
        return bodyType;
    }

    public boolean isBoxAvailability() {
        return boxAvailability;
    }
}

class Garage {
    private List<Vehicle> vehicles;

    public Garage() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("'" + vehicle.getBrand() + "' has been added to garage");
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) {
            vehicles.remove(vehicle);
            System.out.println("'" + vehicle.getBrand() + "' has been removed from garage");
        } else {
            System.out.println("'" + vehicle.getBrand() + "' has not been found");
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}

class Fleet {
    private List<Garage> garages;

    public Fleet() {
        garages = new ArrayList<>();
    }

    public void addGarage(Garage garage) {
        garages.add(garage);
        System.out.println("Garage has been added");
    }

    public void removeGarage(Garage garage) {
        if (garages.contains(garage)) {
            garages.remove(garage);
            System.out.println("Garage has been removed");
        } else {
            System.out.println("Garage has not been found");
        }
    }

    public void search(String brand) {
        for (Garage garage : garages) {
            for (Vehicle vehicle : garage.getVehicles()) {
                if (vehicle.getBrand().equalsIgnoreCase(brand)) {
                    System.out.println("'" + brand + "' has been found");
                    return;
                }
            }
        }
        System.out.println("'" + brand + "' has not been found");
    }
}
