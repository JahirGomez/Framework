package model;

import java.util.ArrayList;
import java.util.List;
import view.BaseView;


public abstract class BaseModel {

    List<BaseView> allViews = new ArrayList<>();

    public void addView(BaseView view) {
        this.allViews.add(view);
    }

    public void request(String info) {
        this.service(info);
        this.sendMessage(this.getData());
    }

    public void sendMessage(Object dataMsg) {
        for (BaseView view : this.allViews) {
            if (view != null) {
                view.action(dataMsg);
            }
        }
    }

    public abstract Object getData();

    public abstract void service(String info);

    public abstract void inputData(String path);

}
