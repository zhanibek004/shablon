import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        IBeverage beverage = new Coffee();
        boolean addingIngredients = true;
        Scanner scanner = new Scanner(System.in);

        while (addingIngredients) {
            System.out.println("\nCurrent Order: " + beverage.getDescription());
            System.out.println("Current Cost: " + beverage.getCost());

            System.out.println("\nChoose an ingredient to add:");
            System.out.println("1. Milk (+10.0)");
            System.out.println("2. Sugar (+5.0)");
            System.out.println("3. Whipped Cream (+12.0)");
            System.out.println("4. Done - Finish order");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    beverage = new MilkDecorator(beverage);
                    System.out.println("Added Milk.");
                    break;
                case "2":
                    beverage = new SugarDecorator(beverage);
                    System.out.println("Added Sugar.");
                    break;
                case "3":
                    beverage = new WhippedCreamDecorator(beverage);
                    System.out.println("Added Whipped Cream.");
                    break;
                case "4":
                    addingIngredients = false;
                    break;
                default:
                    System.out.println("Non-existing choice, restart.");
                    break;
            }
        }

        System.out.println("\nFinal Order: " + beverage.getDescription());
        System.out.println("Total Cost: " + beverage.getCost());
        scanner.close();
    }
}

interface IBeverage {
    double getCost();
    String getDescription();
}

class Coffee implements IBeverage {
    @Override
    public double getCost() {
        return 50.0;
    }

    @Override
    public String getDescription() {
        return "Coffee";
    }
}

class Espresso implements IBeverage {
    @Override
    public double getCost() {
        return 60.0;
    }

    @Override
    public String getDescription() {
        return "Espresso";
    }
}

class Tea implements IBeverage {
    @Override
    public double getCost() {
        return 40.0;
    }

    @Override
    public String getDescription() {
        return "Tea";
    }
}

abstract class BeverageDecorator implements IBeverage {
    protected IBeverage beverage;

    public BeverageDecorator(IBeverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double getCost() {
        return beverage.getCost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription();
    }
}

class MilkDecorator extends BeverageDecorator {
    public MilkDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 10.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }
}

class SugarDecorator extends BeverageDecorator {
    public SugarDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 5.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }
}

class WhippedCreamDecorator extends BeverageDecorator {
    public WhippedCreamDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 12.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Whipped Cream";
    }
}
