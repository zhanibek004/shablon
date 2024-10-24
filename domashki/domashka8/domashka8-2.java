
public abstract class Beverage {


    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    private void boilWater() {
        System.out.println("Кипячение воды...");
    }


    private void pourInCup() {
        System.out.println("Наливание в чашку...");
    }

  
    protected abstract void brew();
    protected abstract void addCondiments();
}


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

public class BeverageTest {
    public static void main(String[] args) {
   
        Beverage tea = new Tea();
        System.out.println("Приготовление чая:");
        tea.prepareRecipe();

        System.out.println();

  
        Beverage coffee = new Coffee();
        System.out.println("Приготовление кофе:");
        coffee.prepareRecipe();
    }
}
