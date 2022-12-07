package  util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class LogManager {
    private static LogManager logManager = null;   
    
    private final Logger logger;   
    private FileHandler fileHandler;
    private final SimpleFormatter simpleFormatter = new SimpleFormatter();
    private int fileNum;
    private String currentLog;
   
    //FROM JSON
    private static final String filePath = "src/files/logs/";
    private static final String logName = "Logs";
    private static final int BASE_WEIGHT = 68;
    private static final double MAX_BYTE_SIZE = 250; //Min: 65
    
    private LogManager(){   
        searchForLastLog();
        logger = Logger.getLogger("MyLog");
        setNewHandler();
    }   
    
    public void addLog(String log, char importance){
        try {
            if(Files.size(Path.of(currentLog)) + log.length() + BASE_WEIGHT > MAX_BYTE_SIZE){
                fileHandler.close();
                fileNum++;
                currentLog = filePath + logName + fileNum + ".txt";
                logger.removeHandler(fileHandler);
                setNewHandler();
            }
            switch (importance){
                case 's':
                    logger.severe(log);
                    break;
                case 'w':
                    logger.warning(log);
                    break;
                case 'i':
                    logger.info(log);
                    break;
                default:
                    logger.fine(log);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(LogManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean setNewHandler(){
        try {
            this.fileHandler = new FileHandler(     currentLog);
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
            return true;
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(LogManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    private void searchForLastLog(){
        fileNum = 0;
        do{
           currentLog = filePath + logName + fileNum + ".txt";
           fileNum++; 
        }while(Files.exists(Path.of(currentLog)));
        fileNum--;
        currentLog = filePath + logName + fileNum + ".txt";
    }
    public static LogManager getInstance(){   
        if (logManager == null){
            logManager = new LogManager();   
        }   
        return logManager;   
    }  
}
