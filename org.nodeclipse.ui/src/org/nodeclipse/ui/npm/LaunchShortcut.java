package org.nodeclipse.ui.npm;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.util.Constants;

/**
 * Using "Run As" --> "npm ..." will lead here.
 **/
public class LaunchShortcut implements ILaunchShortcut,IGoal {

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchShortcut#launch(org.eclipse.jface.viewers
     *      .ISelection, java.lang.String)
     **/
    @Override
    public void launch(ISelection selection, String mode) {
        try {
            Object selectObj = ((IStructuredSelection) selection).getFirstElement();
            if (selectObj instanceof IFile) {
                launchFile((IFile) selectObj, mode);
            } else {
                MessageDialog.openWarning(null, "Warning", "Not implemeneted yet!");
            }
        } catch (CoreException e) {
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchShortcut#launch(org.eclipse.ui.IEditorPart,
     *      java.lang.String)
     **/
    @Override
    public void launch(IEditorPart editor, String mode) {
        try {
            IEditorInput editorInput = editor.getEditorInput();
            if (editorInput instanceof IFileEditorInput) {
                IFile selectObj = ((IFileEditorInput) editorInput).getFile();
                launchFile((IFile) selectObj, mode);
            } else {
                MessageDialog.openWarning(null, "Warning", "Not implemeneted yet!");
            }
        } catch (CoreException e) {
        }
    }

    /**
     * Launch an file,using the file information, which means using default
     * launch configurations.
     * 
     * @param file
     * @param mode
     */
    private void launchFile(IFile file, String mode) throws CoreException {
        // check for an existing launch config for the file
        String path = file.getFullPath().toString();
        ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
        ILaunchConfigurationType type = launchManager.getLaunchConfigurationType(Constants.NPM_LAUNCH_CONFIGURATION_TYPE_ID);
        ILaunchConfiguration configuration = createLaunchConfiguration(type, path, file);
        String goalString = null;
        goalString = configuration.getAttribute(Constants.KEY_GOAL, Constants.BLANK_STRING);
        if (Constants.BLANK_STRING.equals(goalString)) {
            Shell shell = Activator.getActiveWorkbenchShell();
            String groupIdentifier = Constants.NPM_LAUNCH_CONFIGURATION_TABGROUP_ID + mode;
            DebugUITools.openLaunchConfigurationPropertiesDialog(shell, configuration, groupIdentifier);
        }
        goalString = configuration.getAttribute(Constants.KEY_GOAL, Constants.BLANK_STRING);
        if (!Constants.BLANK_STRING.equals(goalString)) {
            DebugUITools.launch(configuration, mode);
        }
    }

    /**
     * Create a new configuration and set useful data.
     * 
     * @param type
     * @param path
     * @param file
     * @return
     * @throws CoreException
     */
    private ILaunchConfiguration createLaunchConfiguration(ILaunchConfigurationType type, String path, IFile file) throws CoreException {
        // create a new configuration for the file
        ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null, file.getName());
        workingCopy.setAttribute(Constants.KEY_FILE_PATH, path);
        workingCopy.setAttribute(Constants.KEY_GOAL, getGoal());
        return workingCopy.doSave();
    }

    @Override
    public String getGoal() {
        return Constants.BLANK_STRING;
    }


}
