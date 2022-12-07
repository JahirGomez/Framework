package util;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader {
    private static JSONReader jsonReader;
    private LogManager logManager;   
    
    private JSONReader(){
        logManager = LogManager.getInstance();
    }
    
    public ArrayList<JSONObject> readFileJSONformat(String path) {
        String error = "";
        try (Scanner scanner = new Scanner(Paths.get(path))) {
            ArrayList<JSONObject> jsonData = new ArrayList<>();
            if (!(scanner.hasNextLine())) {
                error = "Empty JSON file";
                throw new IllegalArgumentException(error);
            }
            if (!error.equalsIgnoreCase("Empty JSON file")) {
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    JSONObject temp = this.createJSONObject(linea);
                    jsonData.add(temp);
                }
                return jsonData;
            }
        } catch (Exception e) {
            logManager.addLog(e.getMessage(), 'a');

            if (error.equals("")) {
                error = "File doesn't exist, path doesn't get the looked file";
                logManager.addLog(error, 'a');
            }
            throw new IllegalArgumentException(error);
        }
        return null;
    }

    private JSONObject createJSONObject(String string) {
        try{
            return new JSONObject(string);
        }catch(JSONException e){
            logManager.addLog(e.getMessage(), 'a');
            return null;
        }
    }
    
    public static JSONReader getInstance(){   
        if (jsonReader == null){
            jsonReader = new JSONReader();   
        }   
        return jsonReader;   
    }  
}
