package util.transactionConfig;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.ExceptionMVCComponent;
import org.json.JSONException;
import util.JSONReader;
import util.LogManager;

public class TransactionInitialConfig {
    LogManager logManager = LogManager.getInstance();  
    
    private final TransactionPool pool;
    private final JSONReader jsonReader;

    public TransactionInitialConfig() {
        this.pool = new TransactionPool();
        this.jsonReader = JSONReader.getInstance();
    }

    public void setInitialData(String filePath) throws ExceptionMVCComponent{
        ArrayList<JSONObject> data = this.jsonReader.readFileJSONformat(filePath);
        try{
            for (JSONObject element : data) {
                String model = element.getString("model");
                String controller = element.getString("controller");
                String name = element.getString("transaction");

                JSONArray array = (JSONArray) element.get("views");
                List<Object> newArray = new ArrayList<>();
                for(int i = 0; i< array.length(); i++){
                    newArray.add(array.get(i));
                }
                ArrayList<String> views = new ArrayList<>();
                for (Object arrayElement : newArray) {
                    views.add((String) arrayElement);
                }
                this.transactionManager(model, controller, views, name);
            }
        }catch(JSONException e){
            logManager.addLog(e.getMessage(), 'a');
        }
    }

    public void transactionManager(String model, String controller, ArrayList<String> views, String name) throws ExceptionMVCComponent {
        TransactionMVC transaction = Instantiator.createTransaccion(model, controller, views);
        TransactionConector.connectTransactionElements(transaction);
        pool.addTransaction(name, transaction);
    }
    
    public TransactionPool getTransaccionPool() {
        return this.pool;
    }

}
