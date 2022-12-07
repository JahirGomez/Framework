package exceptions;

import util.LogManager;

public class ViewComponentsException extends ExceptionMVCComponent {
    public ViewComponentsException(String className) {
        LogManager logManager = LogManager.getInstance();   
        logManager.addLog("The view class " + className + " couldnt be instanciated (The class name doesnt exist)", 'a');
    }

}
