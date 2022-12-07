package util.transactionConfig;

import controller.BaseController;
import exceptions.ControllerComponentsException;
import exceptions.ExceptionMVCComponent;
import exceptions.ModelComponentsException;
import exceptions.ViewComponentsException;
import java.lang.reflect.*;
import java.util.ArrayList;
import model.BaseModel;
import view.BaseView;

public class Instantiator {

    public static TransactionMVC createTransaccion(String modelo, String controlador, ArrayList<String> views) throws ExceptionMVCComponent {
        TransactionMVC transaccion = new TransactionMVC();
        for (String view : views) {
            transaccion.addView(instantiateView(view));
        }
        transaccion.setController(instantiateController(controlador));
        transaccion.setModel(instantiateModel(modelo));
        return transaccion;
    }

    private static void verify(String name) throws ExceptionMVCComponent {
        if (instantiateObject(name) == null && name.contains("view")) {
            throw new ViewComponentsException(name);
        } else if (instantiateObject(name) == null && name.contains("model")) {
            throw new ModelComponentsException(name);
        } else if (instantiateObject(name) == null && name.contains("controller")) {
            throw new ControllerComponentsException(name);
        }
    }

    private static Object instantiateObject(String name) {
        Object obj = null;
        try {
            Class cls = Class.forName(name);
            Constructor ctor = cls.getConstructor();
            obj = ctor.newInstance();
        } catch (Throwable e) {
        }
        return obj;
    }

    private static BaseView instantiateView(String name) throws ExceptionMVCComponent {
        BaseView view = null;
        verify(name);
        view = (BaseView) instantiateObject(name);
        return view;
    }

    private static BaseController instantiateController(String name) throws ExceptionMVCComponent {
        BaseController controller = null;
        verify(name);
        controller = (BaseController) instantiateObject(name);
        return controller;
    }

    private static BaseModel instantiateModel(String name) throws ExceptionMVCComponent {
        BaseModel model = null;
        verify(name);
        model = (BaseModel) instantiateObject(name);
        return model;
    }

}
