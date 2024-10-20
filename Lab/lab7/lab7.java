import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        DeliveryContext deliveryContext = new DeliveryContext();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тип доставки: 1 - стандарт, 2 - экспресс, 3 - международная, 4 - за ночь");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                deliveryContext.setShippingStrategy(new StandardShippingStrategy());
                break;
            case "2":
                deliveryContext.setShippingStrategy(new ExpressShippingStrategy());
                break;
            case "3":
                deliveryContext.setShippingStrategy(new InternationalShippingStrategy());
                break;
            case "4":
                deliveryContext.setShippingStrategy(new OverNightDelivery());
                break;
            default:
                System.out.println("Неверный выбор.");
                scanner.close();
                return;
        }

        System.out.println("Введите вес посылки (кг):");
        double weight = scanner.nextDouble();

        System.out.println("Введите расстояние посылки (км):");
        double distance = scanner.nextDouble();

        double cost = deliveryContext.calculateCost(weight, distance);
        System.out.printf("Стоимость доставки: %.2f\n", cost);
        scanner.close();
    }
}

interface IShippingStrategy {
    double calculateShippingCost(double weight, double distance);
}

class StandardShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 0.5 + distance * 0.1;
    }
}

class ExpressShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return (weight * 0.75 + distance * 0.2) + 10; // Дополнительная плата за скорость
    }
}

class InternationalShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 1.0 + distance * 0.5 + 15; // Дополнительные сборы за международную доставку
    }
}

class OverNightDelivery implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 2.0 + distance * 1.0 + 20;
    }
}

class DeliveryContext {
    private IShippingStrategy shippingStrategy;

    // Метод для установки стратегии доставки
    public void setShippingStrategy(IShippingStrategy strategy) {
        this.shippingStrategy = strategy;
    }

    // Метод для расчета стоимости доставки
    public double calculateCost(double weight, double distance) {
        if (shippingStrategy == null) {
            throw new IllegalStateException("Стратегия доставки не установлена.");
        }
        return shippingStrategy.calculateShippingCost(weight, distance);
    }
}

