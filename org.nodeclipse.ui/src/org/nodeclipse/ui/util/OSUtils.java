package org.nodeclipse.ui.util;

import org.eclipse.core.runtime.Platform;

public class OSUtils {

    private OSUtils() {
    }

    public static boolean isWindows() {
        return Platform.getOS().startsWith("win");
    }

    public static boolean isMacOS() {
        return Platform.getOS().equals(Platform.OS_MACOSX);
    }

    public static boolean isLinux() {
        return Platform.getOS().equals(Platform.OS_LINUX);
    }

}
