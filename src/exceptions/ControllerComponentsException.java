package exceptions;
import util.LogManager;

public class ControllerComponentsException extends ExceptionMVCComponent {
    public ControllerComponentsException(String className) {
        LogManager logManager = LogManager.getInstance();   
        logManager.addLog("The controller class " + className + " couldnt be instanciated (The class name doesnt exist)", 'a');
    }
}
