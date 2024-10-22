// Интерфейс команды
public interface ICommand {
    void execute();
    void undo();
}

// Команда для включения света
public class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

// Команда для выключения света
public class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

// Класс устройства "Свет"
public class Light {
    public void on() {
        System.out.println("Свет включен.");
    }

    public void off() {
        System.out.println("Свет выключен.");
    }
}

// Команда для включения телевизора
public class TelevisionOnCommand implements ICommand {
    private Television television;

    public TelevisionOnCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        television.on();
    }

    @Override
    public void undo() {
        television.off();
    }
}

// Команда для выключения телевизора
public class TelevisionOffCommand implements ICommand {
    private Television television;

    public TelevisionOffCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        television.off();
    }

    @Override
    public void undo() {
        television.on();
    }
}

// Класс устройства "Телевизор"
public class Television {
    public void on() {
        System.out.println("Телевизор включен.");
    }

    public void off() {
        System.out.println("Телевизор выключен.");
    }
}

// Класс устройства "Кондиционер"
public class AirConditioner {
    public void on() {
        System.out.println("Кондиционер включен.");
    }

    public void off() {
        System.out.println("Кондиционер выключен.");
    }
}

// Пульт дистанционного управления
public class RemoteControl {
    private ICommand onCommand;
    private ICommand offCommand;

    public void setCommands(ICommand onCommand, ICommand offCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }

    public void pressOnButton() {
        if (onCommand != null) {
            onCommand.execute();
        }
    }

    public void pressOffButton() {
        if (offCommand != null) {
            offCommand.execute();
        }
    }

    public void pressUndoButton() {
        if (onCommand != null) {
            onCommand.undo();
        }
    }
}

// Клиентский код для демонстрации работы системы умного дома
public class SmartHome {
    public static void main(String[] args) {
        // Создаем устройства
        Light livingRoomLight = new Light();
        Television tv = new Television();

        // Создаем команды для управления светом
        ICommand lightOn = new LightOnCommand(livingRoomLight);
        ICommand lightOff = new LightOffCommand(livingRoomLight);

        // Создаем команды для управления телевизором
        ICommand tvOn = new TelevisionOnCommand(tv);
        ICommand tvOff = new TelevisionOffCommand(tv);

        // Создаем пульт и привязываем команды к кнопкам
        RemoteControl remote = new RemoteControl();
        
        // Управляем светом
        remote.setCommands(lightOn, lightOff);
        System.out.println("Управление светом:");
        remote.pressOnButton();
        remote.pressOffButton();
        remote.pressUndoButton();

        // Управляем телевизором
        remote.setCommands(tvOn, tvOff);
        System.out.println("\nУправление телевизором:");
        remote.pressOnButton();
        remote.pressOffButton();
    }
}

