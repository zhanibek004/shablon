import java.util.Scanner;

enum TransportType {
    Car, Motorcycle, Plane, Bicycle
}

interface ITransport {
    void move();
    void fuelUp();
    String getModel();
    void setModel(String model);
    int getSpeed();
    void setSpeed(int speed);
}

class Car implements ITransport {
    private String model;
    private int speed;

    @Override
    public void move() {
        System.out.println("The car is moving");
    }

    @Override
    public void fuelUp() {
        System.out.println("The car is fueling up");
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

class Motorcycle implements ITransport {
    private String model;
    private int speed;

    @Override
    public void move() {
        System.out.println("The motorcycle is moving");
    }

    @Override
    public void fuelUp() {
        System.out.println("The motorcycle is fueling up");
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

class Plane implements ITransport {
    private String model;
    private int speed;

    @Override
    public void move() {
        System.out.println("The plane is moving");
    }

    @Override
    public void fuelUp() {
        System.out.println("The plane is fueling up");
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

class Bicycle implements ITransport {
    private String model;
    private int speed;

    @Override
    public void move() {
        System.out.println("The bike is moving");
    }

    @Override
    public void fuelUp() {
        System.out.println("The bike does not need to fuel up");
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

abstract class TransportFactory {
    public abstract ITransport createTransport();
}

class CarFactory extends TransportFactory {
    @Override
    public ITransport createTransport() {
        return new Car();
    }
}

class MotorcycleFactory extends TransportFactory {
    @Override
    public ITransport createTransport() {
        return new Motorcycle();
    }
}

class PlaneFactory extends TransportFactory {
    @Override
    public ITransport createTransport() {
        return new Plane();
    }
}

class BicycleFactory extends TransportFactory {
    @Override
    public ITransport createTransport() {
        return new Bicycle();
    }
}

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Transport Type to open: ");
        String userChoice = scanner.nextLine();
        try {
            TransportType transportType = TransportType.valueOf(userChoice.toUpperCase());
            ITransport transport = getTransport(transportType);
            System.out.println("Enter the Transport model: ");
            transport.setModel(scanner.nextLine());
            System.out.println("Enter the transport speed: ");
            if (scanner.hasNextInt()) {
                transport.setSpeed(scanner.nextInt());
            } else {
                System.out.println("Wrong speed entered");
                transport.setSpeed(0);
                scanner.next(); // Clear the invalid input
            }
            transport.move();
            transport.fuelUp();
        } catch (IllegalArgumentException e) {
            System.out.println("This type of factory does not exist");
        }
        scanner.close();
    }

    public static ITransport getTransport(TransportType transportType) {
        TransportFactory creator = null;
        switch (transportType) {
            case Car:
                creator = new CarFactory();
                break;
            case Motorcycle:
                creator = new MotorcycleFactory();
                break;
            case Plane:
                creator = new PlaneFactory();
                break;
            case Bicycle:
                creator = new BicycleFactory();
                break;
            default:
                throw new IllegalArgumentException("Wrong factory");
        }
        return creator.createTransport();
    }
}

