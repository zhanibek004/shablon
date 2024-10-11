import java.util.HashMap;
import java.util.Map;

class ConfigurationManager {


    private static final ConfigurationManager instance = new ConfigurationManager();

    private Map<String, String> settings;

    private ConfigurationManager() {
        settings = new HashMap<>();
    }
    public static ConfigurationManager getInstance() {
        return instance;
    }
    public void loadSettings(Map<String, String> newSettings) {
        settings.putAll(newSettings);
    }


    public String getSetting(String key) {
        return settings.get(key);
    }

    public void setSetting(String key, String value) {
        settings.put(key, value);
    }
}
public class Main {
    public static void main(String[] args) {
        ConfigurationManager config = ConfigurationManager.getInstance();
        Map<String, String> initialSettings = new HashMap<>();
        initialSettings.put("url", "http://example.com");
        initialSettings.put("timeout", "30");
        config.loadSettings(initialSettings);


        System.out.println("URL: " + config.getSetting("url"));
        System.out.println("Timeout: " + config.getSetting("timeout"));


        config.setSetting("timeout", "60");
        System.out.println("Updated Timeout: " + config.getSetting("timeout"));
    }
}
