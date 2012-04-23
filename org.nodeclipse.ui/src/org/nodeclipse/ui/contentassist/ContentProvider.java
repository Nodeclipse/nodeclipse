package org.nodeclipse.ui.contentassist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nodeclipse.ui.util.Constants;

public class ContentProvider {

    public static JSONArray COMPLETIONS;

    static {
        try {
            InputStream is = ContentProvider.class.getClassLoader().getResourceAsStream(Constants.COMPLETIONS_JSON);
            JSONObject object = new JSONObject(inputStream2String(is));
            COMPLETIONS = object.getJSONArray(Constants.COMPLETIONS_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
