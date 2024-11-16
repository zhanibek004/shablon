public class AudioSystem {
    public void turnOn() {
        System.out.println("Audio system is turned on.");
    }

    public void setVolume(int level) {
        System.out.println("Audio volume is set to " + level + ".");
    }

    public void turnOff() {
        System.out.println("Audio system is turned off.");
    }
}

public class VideoProjector {
    public void turnOn() {
        System.out.println("Video projector is turned on.");
    }

    public void setResolution(String resolution) {
        System.out.println("Video resolution is set to " + resolution + ".");
    }

    public void turnOff() {
        System.out.println("Video projector is turned off.");
    }
}

public class LightingSystem {
    public void turnOn() {
        System.out.println("Lights are turned on.");
    }

    public void setBrightness(int level) {
        System.out.println("Lights brightness is set to " + level + ".");
    }

    public void turnOff() {
        System.out.println("Lights are turned off.");
    }
}
public class HomeTheaterFacade {
    private AudioSystem audioSystem;
    private VideoProjector videoProjector;
    private LightingSystem lightingSystem;

    public HomeTheaterFacade(AudioSystem audioSystem, VideoProjector videoProjector, LightingSystem lightingSystem) {
        this.audioSystem = audioSystem;
        this.videoProjector = videoProjector;
        this.lightingSystem = lightingSystem;
    }

    public void startMovie() {
        System.out.println("Preparing to start the movie...");
        lightingSystem.turnOn();
        lightingSystem.setBrightness(5);
        audioSystem.turnOn();
        audioSystem.setVolume(8);
        videoProjector.turnOn();
        videoProjector.setResolution("HD");
        System.out.println("Movie started.");
    }

    public void endMovie() {
        System.out.println("Shutting down movie...");
        videoProjector.turnOff();
        audioSystem.turnOff();
        lightingSystem.turnOff();
        System.out.println("Movie ended.");
    }
}
public class Main {
    public static void main(String[] args) {
        // Создаем подсистемы
        AudioSystem audio = new AudioSystem();
        VideoProjector video = new VideoProjector();
        LightingSystem lights = new LightingSystem();

        // Создаем фасад
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(audio, video, lights);

        // Начинаем фильм
        homeTheater.startMovie();

        System.out.println();

        // Завершаем фильм
        homeTheater.endMovie();
    }
}
