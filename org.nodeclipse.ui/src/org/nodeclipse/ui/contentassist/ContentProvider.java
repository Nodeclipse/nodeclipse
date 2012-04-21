package org.nodeclipse.ui.contentassist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentProvider {
    public static final String PREFIX = "org/nodeclipse/ui/contentassist/api/";
    public static final String SUFFIX = ".json";
    public static final String MODULES = "modules";
    public static final String METHODS = "methods";
    public static final String[] FILE_NAMES = { "path" };
    public static final Map<String, JSONObject> contents = new HashMap<String, JSONObject>();

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
        for (int i = 0; i < FILE_NAMES.length; i++) {
            contents.put(FILE_NAMES[i], getContentObject(FILE_NAMES[i]));
        }
    }

    public static JSONObject getContentObject(String fileName) throws JSONException, IOException {
        InputStream is = ContentProvider.class.getClassLoader().getResourceAsStream(getFilePath(fileName));
        JSONObject object = new JSONObject(inputStream2String(is));
        return (JSONObject) object.getJSONArray(MODULES).get(0);
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
