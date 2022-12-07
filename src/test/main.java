package test;
import util.LogManager;

public class main {
    
    public static void main(String[] args) {
        LogManager log = LogManager.getInstance();  
        
        log.addLog("Hola Mundo", 's');
        log.addLog("Hello World", 'w');
        log.addLog("no sé más idiomas, profe:c", 'i');
    }
    
}
