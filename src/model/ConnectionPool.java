package model;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

import util.PropertiesReader;

public class ConnectionPool {
    /*private String DB="adquisiciones";
    private String URL="jdbc:mysql://localhost:3306/"+DB+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String USER="root";
    private String PASS="";*/
    
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource=null;
    private PropertiesReader properties;
    
    /*private ConnectionPool(){
     
        properties = new PropertiesReader();
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxWaitMillis(-1);
        
    }*/

    private ConnectionPool(){
     
        properties = new PropertiesReader();
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(properties.getDriver());
        basicDataSource.setUsername(properties.getUser());
        basicDataSource.setPassword(properties.getPassword());
        basicDataSource.setUrl(properties.getUrl());
        
        /*basicDataSource.setMinIdle(Integer.valueOf(properties.getMinIdle()));
        basicDataSource.setMaxIdle(Integer.valueOf(properties.getMaxIdle()));
        basicDataSource.setMaxTotal(Integer.valueOf(properties.getMaxTotal()));
        basicDataSource.setMaxWaitMillis(Integer.valueOf(properties.getWaitingTime()));*/
        
    }
    
    public static ConnectionPool getInstance() {
        if (dataSource == null) {
            dataSource = new ConnectionPool();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException{
      return this.basicDataSource.getConnection();
    }
    
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
