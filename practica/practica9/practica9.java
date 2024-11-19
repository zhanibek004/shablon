import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a delivery service:");
        System.out.println("1. Internal Delivery Service");
        System.out.println("2. External Logistics Service A");
        System.out.println("3. External Logistics Service B");
        System.out.print("Enter your choice (1-3): ");
        String input = scanner.nextLine();
        IInternalDeliveryService deliveryService;

        try {
            switch (input) {
                case "1":
                    deliveryService = DeliveryServiceFactory.createDeliveryService("internal");
                    break;
                case "2":
                    deliveryService = DeliveryServiceFactory.createDeliveryService("externalA");
                    break;
                case "3":
                    deliveryService = DeliveryServiceFactory.createDeliveryService("externalB");
                    break;
                default:
                    System.out.println("Non-existing choice, restart.");
                    return;
            }
            String orderId = "404";
            deliveryService.deliverOrder(orderId);
            System.out.println(deliveryService.getDeliveryStatus(orderId));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

interface IInternalDeliveryService {
    void deliverOrder(String orderId);
    String getDeliveryStatus(String orderId);
}

class InternalDeliveryService implements IInternalDeliveryService {
    @Override
    public void deliverOrder(String orderId) {
        System.out.println("Delivering order " + orderId + " by internal delivery service.");
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        return "Status of order " + orderId + ": delivered.";
    }
}

class ExternalLogisticsServiceA {
    public void shipItem(int itemId) {
        System.out.println("Shipping item " + itemId + " by External Logistics Service A.");
    }

    public String trackShipment(int shipmentId) {
        return "Tracking shipment " + shipmentId + " by External Logistics Service A: in Transit.";
    }
}

class ExternalLogisticsServiceB {
    public void sendPackage(String packageInfo) {
        System.out.println("Sending package with info '" + packageInfo + "' by External Logistics Service B.");
    }

    public String checkPackageStatus(String trackingCode) {
        return "Status of package with tracking code " + trackingCode + " by External Logistics Service B: delivered.";
    }
}

class LogisticsAdapterA implements IInternalDeliveryService {
    private final ExternalLogisticsServiceA externalService;

    public LogisticsAdapterA(ExternalLogisticsServiceA externalService) {
        this.externalService = externalService;
    }

    @Override
    public void deliverOrder(String orderId) {
        int itemId = Integer.parseInt(orderId);
        externalService.shipItem(itemId);
        Logger.logAction("Order " + orderId + " delivered by External Logistics Service A.");
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        int shipmentId = Integer.parseInt(orderId);
        String status = externalService.trackShipment(shipmentId);
        Logger.logAction("Checked delivery status for order " + orderId + " by External Logistics Service A.");
        return status;
    }
}

class LogisticsAdapterB implements IInternalDeliveryService {
    private final ExternalLogisticsServiceB externalService;

    public LogisticsAdapterB(ExternalLogisticsServiceB externalService) {
        this.externalService = externalService;
    }

    @Override
    public void deliverOrder(String orderId) {
        externalService.sendPackage(orderId);
        Logger.logAction("Order " + orderId + " delivered by External Logistics Service B.");
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        String status = externalService.checkPackageStatus(orderId);
        Logger.logAction("Checked delivery status for order " + orderId + " by External Logistics Service B.");
        return status;
    }
}

class DeliveryServiceFactory {
    public static IInternalDeliveryService createDeliveryService(String type) {
        switch (type) {
            case "internal":
                return new InternalDeliveryService();
            case "externalA":
                return new LogisticsAdapterA(new ExternalLogisticsServiceA());
            case "externalB":
                return new LogisticsAdapterB(new ExternalLogisticsServiceB());
            default:
                throw new IllegalArgumentException("Non-existing delivery service, restart.");
        }
    }
}

class Logger {
    private static final String LOG_FILE_PATH = "logs/log.txt";

    public static void logAction(String message) {
        String logMessage = LocalDateTime.now() + ": " + message;
        System.out.println(logMessage);
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            writer.write(logMessage + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}

