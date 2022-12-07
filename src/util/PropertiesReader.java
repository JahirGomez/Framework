package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private String url;
    private String user;
    private String password;
    private String minIdle;
    private String maxIdle;
    private String maxTotal;
    private String waitingTime;
    private String driver;

    // Constructor que carga el archivo de configuración y obtiene los valores de URL, usuario y contraseña
    public PropertiesReader() {
     Properties properties = new Properties();
        try{
            FileInputStream in = new FileInputStream("config.properties");
            //properties.load(getClass().getResourceAsStream(in));
            properties.load(in);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            minIdle= properties.getProperty("minIdle");
            maxIdle= properties.getProperty("maxIdle");
            maxTotal= properties.getProperty("maxTotal");
            waitingTime= properties.getProperty("waitingTime");
            driver = properties.getProperty("driver");
            //System.out.println(url + " " + user + " " + password + " " + minIdle + " " + maxIdle + " " + maxTotal + " " + waitingTime + " " + driver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException E){
            E.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public String getMaxTotal() {
        return maxTotal;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public String getDriver() {
        return driver;
    }

    public static void main(String[] args) {
        PropertiesReader p = new PropertiesReader();
    }
}
