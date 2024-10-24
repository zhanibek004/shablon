
public interface ICommand {
    void execute();
    void undo();
}


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

public class Light {
    public void on() {
        System.out.println("Свет включен.");
    }

    public void off() {
        System.out.println("Свет выключен.");
    }
}

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


public class Television {
    public void on() {
        System.out.println("Телевизор включен.");
    }

    public void off() {
        System.out.println("Телевизор выключен.");
    }
}


public class AirConditioner {
    public void on() {
        System.out.println("Кондиционер включен.");
    }

    public void off() {
        System.out.println("Кондиционер выключен.");
    }
}


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
public class SmartHome {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Television tv = new Television();

  
        ICommand lightOn = new LightOnCommand(livingRoomLight);
        ICommand lightOff = new LightOffCommand(livingRoomLight);

      
        ICommand tvOn = new TelevisionOnCommand(tv);
        ICommand tvOff = new TelevisionOffCommand(tv);

     
        RemoteControl remote = new RemoteControl();
        
      
        remote.setCommands(lightOn, lightOff);
        System.out.println("Управление светом:");
        remote.pressOnButton();
        remote.pressOffButton();
        remote.pressUndoButton();

        remote.setCommands(tvOn, tvOff);
        System.out.println("\nУправление телевизором:");
        remote.pressOnButton();
        remote.pressOffButton();
    }
}
