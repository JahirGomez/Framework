package controller;

import model.BaseModel;

public abstract class BaseController {
    private BaseModel model;
    
    public abstract void procesar(String info);

    public void atenderPeticion(String info) {
        this.procesar(info);
        this.getModel().request(info);
    }
    
    public void setModel(BaseModel model) {
        this.model = model;
    }

    public BaseModel getModel() {
        return model;

    }
}
