package utils;
import java.io.IOException;
import java.util.Properties;


/**
 * Class with data which are used in tests
 */
public class Config {
	
    private static final String PROPERTIES_LOCATION = "config.properties";
    private static Properties prop = new Properties();

    static {
        try {
        	prop.load(Config.class.getClassLoader().getResourceAsStream(PROPERTIES_LOCATION));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    
    public static String url = prop.getProperty("url");
    public static boolean headless = Boolean.valueOf(System.getProperty("headless"));
    public static String browser = prop.getProperty("browser");
}