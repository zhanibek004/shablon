import java.util.ArrayList;
import java.util.List;

// Интерфейс Посредник
public interface IMediator {
    void sendMessage(String message, Colleague colleague);
}

// Базовый класс для участников
public abstract class Colleague {
    protected IMediator mediator;

    public Colleague(IMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receiveMessage(String message);
}

// Конкретный посредник, управляющий обменом сообщений между участниками
public class ChatMediator implements IMediator {
    private List<Colleague> colleagues;

    public ChatMediator() {
        this.colleagues = new ArrayList<>();
    }

    public void registerColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void sendMessage(String message, Colleague sender) {
        for (Colleague colleague : colleagues) {
            if (colleague != sender) {
                colleague.receiveMessage(message);
            }
        }
    }
}

// Конкретный участник чата
public class User extends Colleague {
    private String name;

    public User(IMediator mediator, String name) {
        super(mediator);
        this.name = name;
    }

    public void send(String message) {
        System.out.println(name + " отправляет сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }
}

// Клиентский код для демонстрации работы чата
public class ChatDemo {
    public static void main(String[] args) {
        // Создаем посредника
        ChatMediator chatMediator = new ChatMediator();

        // Создаем участников
        User user1 = new User(chatMediator, "Алиса");
        User user2 = new User(chatMediator, "Боб");
        User user3 = new User(chatMediator, "Чарли");

        // Регистрируем участников в чате
        chatMediator.registerColleague(user1);
        chatMediator.registerColleague(user2);
        chatMediator.registerColleague(user3);

        // Участники обмениваются сообщениями
        user1.send("Привет всем!");
        user2.send("Привет, Алиса!");
        user3.send("Всем привет!");
    }
}
