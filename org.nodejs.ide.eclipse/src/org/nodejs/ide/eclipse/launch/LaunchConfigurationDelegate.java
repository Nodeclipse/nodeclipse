/*******************************************************************************
 * Copyright (c) 2012 Lamb. All rights reserved. 
 *
 *******************************************************************************/
package org.nodejs.ide.eclipse.launch;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.nodejs.ide.eclipse.Constants;

public class LaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.
     * eclipse.debug.core.ILaunchConfiguration, java.lang.String,
     * org.eclipse.debug.core.ILaunch,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
        // Using configuration to build command line
        List<String> cmdLine = new ArrayList<String>();
        // Application path should be stored in preference.
        cmdLine.add(Constants.NODE);
        if (mode.equals(ILaunchManager.DEBUG_MODE)) {
            cmdLine.add(ILaunchManager.DEBUG_MODE);
        }
        String file = configuration.getAttribute(Constants.FILE, Constants.BLANK);
        String filePath = ResourcesPlugin.getWorkspace().getRoot().findMember(file).getLocation().toOSString();
        // path is relative, so can not found it.
        cmdLine.add(filePath);
        String[] cmds = {};
        cmds = cmdLine.toArray(cmds);
        // Launch a process to debug.eg,
        Process p = DebugPlugin.exec(cmds, null);
        DebugPlugin.newProcess(launch, p, Constants.PROCESS_MESSAGE);

    }

}
