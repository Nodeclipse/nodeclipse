package org.nodeclipse.lab.contentassist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

public class TestJSON {
    public static final String FILE_NAME = "org/nodeclipse/lab/api/path.json";
    public static String source = "";
    
    public static String inputStream2String(InputStream is) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null){
          buffer.append(line);
        }
        return buffer.toString();
     }

    public static void main(String[] args) throws IOException, JSONException {
        InputStream is = TestJSON.class.getClassLoader().getResourceAsStream(FILE_NAME);
        source = inputStream2String(is);
        JSONObject object = new JSONObject(source);
        JSONObject path = (JSONObject)object.getJSONArray("modules").get(0);
        System.out.println(path.getString("name"));
        System.out.println(path.getJSONArray("methods"));
    }

}
