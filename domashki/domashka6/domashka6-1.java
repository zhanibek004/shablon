class ConfigurationManager {


    private static final ConfigurationManager instance = new ConfigurationManager();
    
    private String url;
    private String timeout;

 
    private ConfigurationManager() {
        
        this.url = "http://default.com";
        this.timeout = "30"; 
    }


    public static ConfigurationManager getInstance() {
        return instance;
    }

    public void setUrl(String url) {
        this.url = url;
    }

 
    public String getUrl() {
        return this.url;
    }

  
    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getTimeout() {
        return this.timeout;
    }
}
public class Main {
    public static void main(String[] args) {
      
        ConfigurationManager config = ConfigurationManager.getInstance();

      
        System.out.println("Def URL: " + config.getUrl());
        System.out.println("Def Timeout: " + config.getTimeout());

    
        config.setUrl("http://fixfixfix");
        config.setTimeout("60");

     
        System.out.println("обновленный URL: " + config.getUrl());
        System.out.println("обновленный Timeout: " + config.getTimeout());
    }
}
