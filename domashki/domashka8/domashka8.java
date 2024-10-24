import java.util.Stack;
public interface ICommand {
    void execute();
    void undo();
}

public class Light {
    public void turnOn() {
        System.out.println("The light is ON");
    }

    public void turnOff() {
        System.out.println("The light is OFF");
    }
}


public class Door {
    public void open() {
        System.out.println("The door is OPEN");
    }

    public void close() {
        System.out.println("The door is CLOSED");
    }
}

public class Thermostat {
    private int temperature;

    public void increaseTemperature() {
        temperature += 1;
        System.out.println("Temperature increased to " + temperature + " degrees");
    }

    public void decreaseTemperature() {
        temperature -= 1;
        System.out.println("Temperature decreased to " + temperature + " degrees");
    }

    public int getTemperature() {
        return temperature;
    }
}

public class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

public class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

public class DoorOpenCommand implements ICommand {
    private Door door;

    public DoorOpenCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }

    @Override
    public void undo() {
        door.close();
    }
}

public class DoorCloseCommand implements ICommand {
    private Door door;

    public DoorCloseCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

    @Override
    public void undo() {
        door.open();
    }
}


public class IncreaseTemperatureCommand implements ICommand {
    private Thermostat thermostat;

    public IncreaseTemperatureCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void execute() {
        thermostat.increaseTemperature();
    }

    @Override
    public void undo() {
        thermostat.decreaseTemperature();
    }
}

public class DecreaseTemperatureCommand implements ICommand {
    private Thermostat thermostat;

    public DecreaseTemperatureCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void execute() {
        thermostat.decreaseTemperature();
    }

    @Override
    public void undo() {
        thermostat.increaseTemperature();
    }
}
public class Invoker {
    private Stack<ICommand> commandHistory;
    private Stack<ICommand> undoneHistory;

    public Invoker() {
        commandHistory = new Stack<>();
        undoneHistory = new Stack<>();
    }

    public void executeCommand(ICommand command) {
        command.execute();
        commandHistory.push(command);
        undoneHistory.clear(); // Очистить историю отмены после нового выполнения
    }

    public void undoCommand() {
        if (!commandHistory.isEmpty()) {
            ICommand lastCommand = commandHistory.pop();
            lastCommand.undo();
            undoneHistory.push(lastCommand);
        }
    }

    public void redoCommand() {
        if (!undoneHistory.isEmpty()) {
            ICommand lastUndoneCommand = undoneHistory.pop();
            lastUndoneCommand.execute();
            commandHistory.push(lastUndoneCommand);
        }
    }
}
public class SmartHomeTest {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();
        Thermostat thermostat = new Thermostat();

  
        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);
        DoorOpenCommand doorOpen = new DoorOpenCommand(door);
        DoorCloseCommand doorClose = new DoorCloseCommand(door);
        IncreaseTemperatureCommand increaseTemp = new IncreaseTemperatureCommand(thermostat);
        DecreaseTemperatureCommand decreaseTemp = new DecreaseTemperatureCommand(thermostat);

        Invoker remote = new Invoker();

      
        remote.executeCommand(lightOn);      // Включение света
        remote.executeCommand(doorOpen);     // Открытие двери
        remote.executeCommand(increaseTemp); // Увеличение температуры

     
        remote.undoCommand(); // Отменить увеличение температуры
        remote.undoCommand(); // Закрыть дверь
        remote.undoCommand(); // Выключить свет

 
        remote.redoCommand(); // Включить свет
        remote.redoCommand(); // Открыть дверь
        remote.redoCommand(); // Увеличить температуру
    }
}
