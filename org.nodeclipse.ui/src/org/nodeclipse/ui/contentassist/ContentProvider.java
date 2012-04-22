package org.nodeclipse.ui.contentassist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentProvider {
    public static final String PREFIX = "org/nodeclipse/ui/contentassist/api/";
    public static final String SUFFIX = ".json";
    public static final String MODULES_KEY = "modules";
    public static final String METHODS_KEY = "methods";
    public static final String[] METHOD_FILE_NAMES = { "assert", "path" };//,"buffer","child_process"
    public static final JSONArray METHODS = new JSONArray();
    static {
        try {
            initContent();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initContent() throws JSONException, IOException {
        for (int i = 0; i < METHOD_FILE_NAMES.length; i++) {
            JSONArray modules = getModules(METHOD_FILE_NAMES[i]);
            for (int j = 0; modules != null && j < modules.length(); j++) {
                putMethods(getMethods(modules.getJSONObject(j)));
            }
        }
    }

    
    public static JSONArray getMethods(JSONObject object) throws JSONException {
       return object.getJSONArray(METHODS_KEY);
    }

    public static void putMethods(JSONArray array) throws JSONException {
        for (int i = 0; array != null && i < array.length(); i++) {
            METHODS.put(array.get(i));
        }
    }

    public static JSONArray getModules(String fileName) throws JSONException, IOException {
        InputStream is = ContentProvider.class.getClassLoader().getResourceAsStream(getFilePath(fileName));
        JSONObject object = new JSONObject(inputStream2String(is));
        return object.getJSONArray(MODULES_KEY);
    }

    public static String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    public static String getFilePath(String fileName) {
        return PREFIX + fileName + SUFFIX;
    }
}
