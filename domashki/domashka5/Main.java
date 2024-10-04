import java.util.Scanner;
interface IVehicle {
    void drive();
    void refuel();
}

class Car implements IVehicle {
    public String brand;
    public String model;
    public String fuelType;

    public Car(String brand, String model, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }

    @Override
    public void drive() {
        System.out.println("Drive");
    }

    @Override
    public void refuel() {
        System.out.println("Refuel");
    }
}

class Motorcycle implements IVehicle {
    public String type;
    public int engineVolume;

    public Motorcycle(String type, int engineVolume) {
        this.type = type;
        this.engineVolume = engineVolume;
    }

    public void drive() {
        System.out.println("Drive");
    }


    public void refuel() {
        System.out.println("refuel");
    }
}

class Truck implements IVehicle {
    public int loadCapacity;
    public int axles;

    public Truck(int loadCapacity, int axles) {
        this.loadCapacity = loadCapacity;
        this.axles = axles;
    }

    @Override
    public void drive() {
        System.out.println("Drive");
    }

    public void refuel() {
        System.out.println("Refuel");
    }
}

abstract class VehicleFactory {
    public abstract IVehicle createVehicle();
}


class CarFactory extends VehicleFactory {
    private String brand;
    private String model;
    private String fuelType;

    public CarFactory(String brand, String model, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }

    public IVehicle createVehicle() {
        return new Car(brand, model, fuelType);
    }
}


class MotorcycleFactory extends VehicleFactory {
    private String type;
    private int engineVolume;

    public MotorcycleFactory(String type, int engineVolume) {
        this.type = type;
        this.engineVolume = engineVolume;
    }

    @Override
    public IVehicle createVehicle() {
        return new Motorcycle(type, engineVolume);
    }
}


class TruckFactory extends VehicleFactory {
    private int loadCapacity;
    private int axles;

    public TruckFactory(int loadCapacity, int axles) {
        this.loadCapacity = loadCapacity;
        this.axles = axles;
    }

    public IVehicle createVehicle() {
        return new Truck(loadCapacity, axles);
    }
}

class Bus implements IVehicle {
    public int seatingCapacity;

    public Bus(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

 
    public void drive() {
        System.out.println("Drive");
    }


    public void refuel() {
        System.out.println("Refuel");
    }
}

class BusFactory extends VehicleFactory {
    public int seatingCapacity;

    public BusFactory(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
    @Override
    public IVehicle createVehicle() {
        return new Bus(seatingCapacity);
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleFactory factory = null;

        System.out.println("Choose type: 1. Car 2. Motorcycle 3. Truck 4. Bus");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("марка, модель, тип топлива:");
                String brand = scanner.next();
                String model = scanner.next();
                String fuelType = scanner.next();
                factory = new CarFactory(brand, model, fuelType);
                break;
            case 2:
                System.out.println("тип, объём двигателя:");
                String type = scanner.next();
                int engineVolume = scanner.nextInt();
                factory = new MotorcycleFactory(type, engineVolume);
                break;
            case 3:
                System.out.println("Введите грузоподъемность:");
                int loadCapacity = scanner.nextInt();
                int axles = scanner.nextInt();
                factory = new TruckFactory(loadCapacity, axles);
                break;
            case 4:
                System.out.println("Введите количество мест:");
                int seatingCapacity = scanner.nextInt();
                factory = new BusFactory(seatingCapacity);
                break;
            default:
                System.out.println("Error");
                break;
        }

        if (factory != null) {
            IVehicle vehicle = factory.createVehicle();
            vehicle.drive();
            vehicle.refuel();
        }
    }
}
