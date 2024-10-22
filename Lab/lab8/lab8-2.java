// Базовый класс Beverage, который содержит шаблонный метод и общие шаги приготовления напитков
public abstract class Beverage {

    // Шаблонный метод
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Общий шаг для всех напитков
    private void boilWater() {
        System.out.println("Кипячение воды...");
    }

    // Общий шаг для всех напитков
    private void pourInCup() {
        System.out.println("Наливание в чашку...");
    }

    // Абстрактные методы для шагов, которые будут реализованы в подклассах
    protected abstract void brew();
    protected abstract void addCondiments();
}

// Класс для приготовления чая
public class Tea extends Beverage {

    @Override
    protected void brew() {
        System.out.println("Заваривание чая...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавление лимона...");
    }
}

// Класс для приготовления кофе
public class Coffee extends Beverage {

    @Override
    protected void brew() {
        System.out.println("Заваривание кофе...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавление сахара и молока...");
    }
}

// Клиентский код для демонстрации процесса приготовления напитков
public class BeverageTest {
    public static void main(String[] args) {
        // Приготовление чая
        Beverage tea = new Tea();
        System.out.println("Приготовление чая:");
        tea.prepareRecipe();

        System.out.println();

        // Приготовление кофе
        Beverage coffee = new Coffee();
        System.out.println("Приготовление кофе:");
        coffee.prepareRecipe();
    }
}
