import java.util.ArrayList;
import java.util.List;

interface IMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}

class ChatRoom implements IMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
         
            if (user != sender) {
                user.receive(message, sender);
            }
        }
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
}

abstract class User {
    protected IMediator mediator;
    protected String name;

    public User(IMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

  
    public abstract void send(String message);


    public abstract void receive(String message, User sender);

    public String getName() {
        return this.name;
    }
}


class ConcreteUser extends User {

    public ConcreteUser(IMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message, User sender) {
        System.out.println(this.name + " received a message from " + sender.getName() + ": " + message);
    }
}

// Клиентский код
public class ChatAppDemo {
    public static void main(String[] args) {

        IMediator chatRoom = new ChatRoom();


        User user1 = new ConcreteUser(chatRoom, "Alice");
        User user2 = new ConcreteUser(chatRoom, "Bob");
        User user3 = new ConcreteUser(chatRoom, "Charlie");

        
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.send("Hello everyone!");
        user2.send("Hi Alice!");
        user3.send("Good morning, everyone!");
    }
}
