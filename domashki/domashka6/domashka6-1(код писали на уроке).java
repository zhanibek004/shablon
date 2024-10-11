
public class Logger{

    private Logger(){}
    private static Logger _logger;
    public static LogLevel _level = LogLevel.INFO;
    public static Logger GetInstance(){
        if (_logger == null)
            _logger = new Logger();

        return _logger;
    }

    public static void SetLevel(LogLevel level){
        _level = level;
    }
    public void Log(String message, LogLevel level){
        if (_level == level)
        {

        }
    }
}
enum LogLevel {
    INFO(1),
    WARNING(2),
    ERROR(3);

    public int level;

    LogLevel(int level) {
        this.level = level;
    }

}

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.GetInstance();
        logger.SetLevel(LogLevel.WARNING);

        logger.Log("Some error", LogLevel.WARNING);

        Logger.GetInstance().Log("Some error", LogLevel.INFO);

    }

}
