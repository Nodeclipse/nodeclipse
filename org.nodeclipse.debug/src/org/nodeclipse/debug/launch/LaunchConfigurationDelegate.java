package org.nodeclipse.debug.launch;

import java.io.File;
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
import org.nodeclipse.debug.util.Constants;

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
            cmdLine.add("--debug-brk=5858");
        }
        String file = configuration.getAttribute(Constants.KEY_FILE_PATH, Constants.BLANK_STRING);
        String filePath = ResourcesPlugin.getWorkspace().getRoot().findMember(file).getLocation().toOSString();
        // path is relative, so can not found it.
        cmdLine.add(filePath);
        String[] cmds = {};
        cmds = cmdLine.toArray(cmds);
        // Launch a process to debug.eg,
        Process p = DebugPlugin.exec(cmds, (new File(filePath)).getParentFile());
        DebugPlugin.newProcess(launch, p, Constants.PROCESS_MESSAGE);
        // if (mode.equals(ILaunchManager.DEBUG_MODE)) {
        // DebugTarget target = new DebugTarget(launch, process, p);
        // launch.addDebugTarget(target);
        // }

    }

}
