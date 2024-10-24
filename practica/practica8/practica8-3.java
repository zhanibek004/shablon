import java.util.*;

interface IMediator {
    void sendMessage(String message, String userId);
    void addUser(User user);
    void removeUser(String userId);
    void notifyUserStatus(String userId, boolean isOnline);
}

interface IUser {
    void receiveMessage(String message);
    void sendMessage(String message);
    void setMediator(IMediator mediator);
    String getId();
    String getName();
}


class User implements IUser {
    private String id;
    private String name;
    private IMediator mediator;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void setMediator(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " received: " + message);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, id);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}

class ChatMediator implements IMediator {
    private Map<String, User> users = new HashMap<>();

    @Override
    public void sendMessage(String message, String userId) {
        for (User user : users.values()) {
            // Отправлять сообщение всем, кроме отправителя
            if (!user.getId().equals(userId)) {
                user.receiveMessage(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
        user.setMediator(this);
        notifyUserStatus(user.getId(), true);
    }

    @Override
    public void removeUser(String userId) {
        User removedUser = users.remove(userId);
        if (removedUser != null) {
            notifyUserStatus(userId, false);
        }
    }

    @Override
    public void notifyUserStatus(String userId, boolean isOnline) {
        String statusMessage = (isOnline ? "joined" : "left") + " the chat.";
        for (User user : users.values()) {
            if (!user.getId().equals(userId)) {
                user.receiveMessage("User " + userId + " " + statusMessage);
            }
        }
    }
}

class ChannelMediator implements IMediator {
    private Map<String, ChatMediator> channels = new HashMap<>();

    public void addChannel(String channelName) {
        channels.put(channelName, new ChatMediator());
        System.out.println("Channel '" + channelName + "' created.");
    }

    public void removeChannel(String channelName) {
        channels.remove(channelName);
        System.out.println("Channel '" + channelName + "' removed.");
    }

    public void addUserToChannel(String channelName, User user) {
        ChatMediator channel = channels.get(channelName);
        if (channel != null) {
            channel.addUser(user);
            System.out.println("User " + user.getName() + " joined channel '" + channelName + "'.");
        }
    }

    public void removeUserFromChannel(String channelName, String userId) {
        ChatMediator channel = channels.get(channelName);
        if (channel != null) {
            channel.removeUser(userId);
        }
    }

    @Override
    public void sendMessage(String message, String userId) {
        // Перебираем все каналы и отправляем сообщение в каждом из них
        for (ChatMediator channel : channels.values()) {
            channel.sendMessage(message, userId);
        }
    }

    @Override
    public void addUser(User user) {
        throw new UnsupportedOperationException("Use addUserToChannel for adding users to a specific channel.");
    }

    @Override
    public void removeUser(String userId) {
        throw new UnsupportedOperationException("Use removeUserFromChannel for removing users from a specific channel.");
    }

    @Override
    public void notifyUserStatus(String userId, boolean isOnline) {
        // Перебираем все каналы и уведомляем статус пользователя
        for (ChatMediator channel : channels.values()) {
            channel.notifyUserStatus(userId, isOnline);
        }
    }
}

// Клиентский код для демонстрации
public class ChatAppDemo {
    public static void main(String[] args) {
        
        ChannelMediator mediator = new ChannelMediator();

 
        mediator.addChannel("General");
        mediator.addChannel("Tech");
        mediator.addChannel("Sports");

  
        User user1 = new User("1", "Alice");
        User user2 = new User("2", "Bob");
        User user3 = new User("3", "Charlie");

   
        mediator.addUserToChannel("General", user1);
        mediator.addUserToChannel("Tech", user2);
        mediator.addUserToChannel("Sports", user3);

        
        user1.sendMessage("Hello, everyone!");
        user2.sendMessage("Any tech news?");
        user3.sendMessage("Who won the game last night?");
        mediator.removeUserFromChannel("General", "1");
    }
}
