import java.util.Scanner;

/* Decorator */
public class Program {
    public static void main(String[] args) {
        // Создаем базовый напиток — кофе
        IBeverage beverage = new Coffee();
        boolean addingIngredients = true;
        Scanner scanner = new Scanner(System.in);

        while (addingIngredients) {
            System.out.println("\nТекущий заказ: " + beverage.getDescription());
            System.out.println("Текущая стоимость: " + beverage.getCost());

            System.out.println("\nВыберите ингредиент для добавления:");
            System.out.println("1. Молоко (+10.0)");
            System.out.println("2. Сахар (+5.0)");
            System.out.println("3. Шоколад (+15.0)");
            System.out.println("4. Ваниль (+20.0)");
            System.out.println("5. Корица (+25.0)");
            System.out.println("6. Миндальное молоко (+30.0)");
            System.out.println("7. Завершить заказ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    beverage = new MilkDecorator(beverage);
                    System.out.println("Добавлено молоко.");
                    break;
                case "2":
                    beverage = new SugarDecorator(beverage);
                    System.out.println("Добавлен сахар.");
                    break;
                case "3":
                    beverage = new ChocolateDecorator(beverage);
                    System.out.println("Добавлен шоколад.");
                    break;
                case "4":
                    beverage = new VanillaDecorator(beverage);
                    System.out.println("Добавлена ваниль.");
                    break;
                case "5":
                    beverage = new CinnamonDecorator(beverage);
                    System.out.println("Добавлена корица.");
                    break;
                case "6":
                    beverage = new AlmondMilkDecorator(beverage);
                    System.out.println("Добавлено миндальное молоко.");
                    break;
                case "7":
                    addingIngredients = false;
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
                    break;
            }
        }
        System.out.println("\nИтоговый заказ: " + beverage.getDescription());
        System.out.println("Общая стоимость: " + beverage.getCost());
        scanner.close();
    }
}

interface IBeverage {
    double getCost();  // Получить стоимость напитка
    String getDescription();  // Получить описание напитка
}

class Coffee implements IBeverage {
    @Override
    public double getCost() {
        return 50.0;  // Стоимость кофе
    }

    @Override
    public String getDescription() {
        return "Кофе";
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
        return super.getDescription() + ", Молоко";
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
        return super.getDescription() + ", Сахар";
    }
}

class ChocolateDecorator extends BeverageDecorator {
    public ChocolateDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 15.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Шоколад";
    }
}

class VanillaDecorator extends BeverageDecorator {
    public VanillaDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 20.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Ваниль";
    }
}

class CinnamonDecorator extends BeverageDecorator {
    public CinnamonDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 25.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Корица";
    }
}

class AlmondMilkDecorator extends BeverageDecorator {
    public AlmondMilkDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 30.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Миндальное молоко";
    }
}

