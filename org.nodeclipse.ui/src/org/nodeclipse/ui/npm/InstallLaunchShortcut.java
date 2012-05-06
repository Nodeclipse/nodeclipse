package org.nodeclipse.ui.npm;

import org.nodeclipse.ui.util.Constants;

/**
 * Using "Run As" --> "npm install" will lead here.
 **/
public class InstallLaunchShortcut extends LaunchShortcut implements IGoal {
    public String getGoal() {
        return Constants.NPM_INSTALL;
    }
}
