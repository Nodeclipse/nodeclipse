package org.nodeclipse.debug.util;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    private static final String BUNDLE_NAME = "org.nodeclipse.debug.util.messages";

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

    public static String fileLabel;
    public static String searchLabel;
    public static String searchTitle;
}
