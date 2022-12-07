package exceptions;

import util.LogManager;

public class ModelComponentsException extends ExceptionMVCComponent {
    public ModelComponentsException(String className) {
        LogManager logManager = LogManager.getInstance();   
        logManager.addLog("The model class " + className + " couldnt be instanciated (The class name doesnt exist)", 'a');
    }

}
