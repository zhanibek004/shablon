import java.util.ArrayList;
import java.util.List;

// Основной класс
public class Program {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        // Создаем несколько наблюдателей
        WeatherDisplay mobileApp = new WeatherDisplay("Мобильное приложение");
        WeatherDisplay digitalBillboard = new WeatherDisplay("Электронное табло");
        EmailNotification emailNotification = new EmailNotification("random@gmail.com");

        // Регистрируем наблюдателей в системе
        weatherStation.registerObserver(mobileApp);
        weatherStation.registerObserver(digitalBillboard);
        weatherStation.registerObserver(emailNotification);

        // Изменяем температуру на станции, что приводит к уведомлению наблюдателей
        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);

        // Убираем один из дисплеев и снова меняем температуру
        weatherStation.removeObserver(digitalBillboard);
        weatherStation.setTemperature(28.0f);

        weatherStation.removeObserver(digitalBillboard); // для проверки ошибки
        weatherStation.setTemperature(60.0f);
    }
}

// Интерфейс наблюдателя
interface Observer {
    void update(float temperature);
}

// Интерфейс субъекта
interface Subject {
    void registerObserver(Observer observer);  // Регистрация наблюдателя
    void removeObserver(Observer observer);    // Удаление наблюдателя
    void notifyObservers();                    // Уведомление всех наблюдателей
}

// Класс станции погоды
class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        } else {
            System.out.println("Наблюдатель уже существует.");
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        } else {
            System.out.println("Наблюдатель не существует.");
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(float newTemperature) {
        if (newTemperature < -50 || newTemperature > 50) {
            System.out.println("Ошибка: неправильная температура.");
            return;
        }
        System.out.println("Изменение температуры: " + newTemperature + "°C");
        temperature = newTemperature;
        notifyObservers();
    }
}

// Класс отображения погоды
class WeatherDisplay implements Observer {
    private String name;

    public WeatherDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " показывает новую температуру: " + temperature + "°C");
    }
}

// Класс уведомления по email
class EmailNotification implements Observer {
    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void update(float temperature) {
        System.out.println("Отправляю email на " + email + ": новая температура равна: " + temperature + "°C");
    }
}
