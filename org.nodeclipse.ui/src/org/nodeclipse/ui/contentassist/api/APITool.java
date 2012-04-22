package org.nodeclipse.ui.contentassist.api;

public class APITool {
    public static String clear(String s) {
        String[] regex = {"&apos;","&quot;"};
        String[] repla = {"'","\""};
        for (int i = 0; i < repla.length; i++) {
            s= s.replaceAll(regex[i], repla[i]); 
        }
        return s;
    }

}
