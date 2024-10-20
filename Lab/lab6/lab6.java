import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        // Creating an office computer
        System.out.println("Office Computer:");
        IComputerBuilder officeBuilder = new OfficeComputerBuilder();
        ComputerDirector director = new ComputerDirector(officeBuilder);
        director.constructComputer();
        Computer officeComputer = director.getComputer();
        System.out.println(officeComputer);

        // Creating a gaming computer
        System.out.println("Gaming Computer:");
        IComputerBuilder gamingBuilder = new GamingComputerBuilder();
        director = new ComputerDirector(gamingBuilder);
        director.constructComputer();
        Computer gamingComputer = director.getComputer();
        System.out.println(gamingComputer);

        // Creating a server computer
        System.out.println("Server Computer:");
        IComputerBuilder serverComputerBuilder = new ServerComputerBuilder();
        director = new ComputerDirector(serverComputerBuilder);
        director.constructComputer();
        Computer serverComputer = director.getComputer();
        System.out.println(serverComputer);

        // Creating a faulty graphics computer for compatibility check
        System.out.println("Faulty Graphics Computer:");
        IComputerBuilder faultyArtistComputerBuilder = new FaultyArtistComputerBuilder();
        director = new ComputerDirector(faultyArtistComputerBuilder);
        director.constructComputer();
        Computer graphicsComputer = director.getComputer();
        System.out.println(graphicsComputer);
    }
}

class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private String os;
    private String cooler;
    private String psu;

    public void setCpu(String cpu) { this.cpu = cpu; }
    public void setRam(String ram) { this.ram = ram; }
    public void setStorage(String storage) { this.storage = storage; }
    public void setGpu(String gpu) { this.gpu = gpu; }
    public void setOs(String os) { this.os = os; }
    public void setCooler(String cooler) { this.cooler = cooler; }
    public void setPsu(String psu) { this.psu = psu; }

    @Override
    public String toString() {
        return "Computer: CPU - " + cpu + ",\nRAM - " + ram + ",\nStorage - " + storage +
               ",\nGPU - " + gpu + ",\nOS - " + os + ",\nCooler type - " + cooler +
               ",\nPower Supply Unit - " + psu + "\n";
    }

    public boolean compatibilityCheck() {
        if ("Amd Theadripper Pro 5995WX".equals(cpu) && "Air cooler".equals(cooler)) {
            System.out.println("The CPU - Amd Theadripper Pro 5995WX requires liquid cooling.");
            return false;
        }
        if ("Amd Radeon RX 7900 XTX".equals(gpu) && "400W".equals(psu)) {
            System.out.println("The GPU - Amd Radeon RX 7900 XTX requires a 2000W power supply unit.");
            return false;
        }
        return true;
    }
}

interface IComputerBuilder {
    void setCPU();
    void setRAM();
    void setStorage();
    void setGPU();
    void setOS();
    void setCooler();
    void setPSU();
    Computer getComputer();
}

class OfficeComputerBuilder implements IComputerBuilder {
    private Computer computer = new Computer();

    public void setCPU() { computer.setCpu("Intel i3"); }
    public void setRAM() { computer.setRam("8GB"); }
    public void setStorage() { computer.setStorage("1TB HDD"); }
    public void setGPU() { computer.setGpu("Integrated"); }
    public void setOS() { computer.setOs("Windows 10"); }
    public void setCooler() { computer.setCooler("Air cooler"); }
    public void setPSU() { computer.setPsu("500W"); }
    public Computer getComputer() { return computer; }
}

class GamingComputerBuilder implements IComputerBuilder {
    private Computer computer = new Computer();

    public void setCPU() { computer.setCpu("Intel i9"); }
    public void setRAM() { computer.setRam("32GB"); }
    public void setStorage() { computer.setStorage("1TB SSD"); }
    public void setGPU() { computer.setGpu("NVIDIA RTX 3080"); }
    public void setOS() { computer.setOs("Windows 11"); }
    public void setCooler() { computer.setCooler("Liquid cooler"); }
    public void setPSU() { computer.setPsu("1500W"); }
    public Computer getComputer() { return computer; }
}

class ServerComputerBuilder implements IComputerBuilder {
    private Computer computer = new Computer();

    public void setCPU() { computer.setCpu("NVIDIA Grace Cpu Superchip"); }
    public void setRAM() { computer.setRam("2TB"); }
    public void setStorage() { computer.setStorage("4PB"); }
    public void setGPU() { computer.setGpu("NVIDIA RTX A 1000"); }
    public void setOS() { computer.setOs("Linux Arch"); }
    public void setCooler() { computer.setCooler("Liquid cooler"); }
    public void setPSU() { computer.setPsu("4000W"); }
    public Computer getComputer() { return computer; }
}

class FaultyArtistComputerBuilder implements IComputerBuilder {
    private Computer computer = new Computer();

    public void setCPU() { computer.setCpu("Amd Theadripper Pro 5995WX"); }
    public void setRAM() { computer.setRam("128GB"); }
    public void setStorage() { computer.setStorage("4TB"); }
    public void setGPU() { computer.setGpu("Amd Radeon RX 7900 XTX"); }
    public void setOS() { computer.setOs("Windows 10 Pro"); }
    public void setCooler() { computer.setCooler("Air cooler"); }
    public void setPSU() { computer.setPsu("400W"); }
    public Computer getComputer() { return computer; }
}

class ComputerDirector {
    private IComputerBuilder builder;

    public ComputerDirector(IComputerBuilder builder) {
        this.builder = builder;
    }

    public void constructComputer() {
        builder.setCPU();
        builder.setRAM();
        builder.setStorage();
        builder.setGPU();
        builder.setOS();
        builder.setCooler();
        builder.setPSU();
        Computer computer = builder.getComputer();
        if (!computer.compatibilityCheck()) {
            System.out.println("The computer has incompatible parts.");
        }
    }

    public Computer getComputer() {
        return builder.getComputer();
    }
}

