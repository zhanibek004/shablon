public class Program {
    public static void main(String[] args) {
        TV tv = new TV();
        AudioSystem audio = new AudioSystem();
        DVDPlayer dvdPlayer = new DVDPlayer();
        GameConsole gameConsole = new GameConsole();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, audio, dvdPlayer, gameConsole);

        homeTheater.watchMovie();
        System.out.println();

        homeTheater.startGame();
        System.out.println();

        homeTheater.listenToMusic();
        System.out.println();

        homeTheater.turnOffSystem();
    }
}

class TV {
    public void turnOn() {
        System.out.println("TV is turned on.");
    }

    public void turnOff() {
        System.out.println("TV is turned off.");
    }

    public void selectChannel(int channel) {
        System.out.println("TV channel set to " + channel + ".");
    }
}

class AudioSystem {
    public void turnOn() {
        System.out.println("Audio system is turned on.");
    }

    public void setVolume(int level) {
        System.out.println("Audio volume set to " + level + ".");
    }

    public void turnOff() {
        System.out.println("Audio system is turned off.");
    }
}

class DVDPlayer {
    public void play(String name) {
        System.out.println("DVD is playing - " + name);
    }

    public void pause() {
        System.out.println("DVD is paused.");
    }

    public void stop() {
        System.out.println("DVD is stopped.");
    }
}

class GameConsole {
    public void turnOn() {
        System.out.println("Game console is turned on.");
    }

    public void startGame(String game) {
        System.out.println("Starting game: " + game);
    }
}

class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audioSystem;
    private DVDPlayer dvdPlayer;
    private GameConsole gameConsole;

    public HomeTheaterFacade(TV tv, AudioSystem audioSystem, DVDPlayer dvdPlayer, GameConsole gameConsole) {
        this.tv = tv;
        this.audioSystem = audioSystem;
        this.dvdPlayer = dvdPlayer;
        this.gameConsole = gameConsole;
    }

    public void watchMovie() {
        System.out.println("Setting up to watch a movie...");
        tv.turnOn();
        audioSystem.turnOn();
        audioSystem.setVolume(10);
        dvdPlayer.play("Openheimer");
        System.out.println("Movie is now playing.");
    }

    public void startGame() {
        System.out.println("Setting up to start a game...");
        tv.turnOn();
        audioSystem.turnOn();
        audioSystem.setVolume(8);
        gameConsole.turnOn();
        gameConsole.startGame("Riders Republic");
        System.out.println("Game has started.");
    }

    public void listenToMusic() {
        System.out.println("Setting up to listen to music...");
        tv.turnOn();
        audioSystem.turnOn();
        audioSystem.setVolume(6);
        System.out.println("Music is now playing through TV and audio system.");
    }

    public void turnOffSystem() {
        System.out.println("Shutting down the home theater system...");
        dvdPlayer.stop();
        tv.turnOff();
        audioSystem.turnOff();
        gameConsole.turnOn();
        System.out.println("System is now off.");
    }
}

