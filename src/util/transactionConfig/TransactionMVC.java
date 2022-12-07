package util.transactionConfig;

import controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import model.BaseModel;
import view.BaseView;

public class TransactionMVC {
    private BaseModel model;
    private BaseController controller;
    private List<BaseView> views = new ArrayList<>();

    public void addView(BaseView vista) {
        this.views.add(vista);
    }

    public void setModel(BaseModel model) {
        this.model = model;
    }

    public void setController(BaseController controller) {
        this.controller = controller;
    }

    public BaseModel getModel() {
        return model;
    }

    public BaseController getController() {
        return controller;
    }

    public List<BaseView> getViews() {
        return views;
    }

}
