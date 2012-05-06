package org.nodeclipse.ui.npm;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.util.Constants;

public class LaunchConfigurationMainTab extends AbstractLaunchConfigurationTab {

    public static final String MAIN_TAB_NAME = "Main";
    private Text fileText;
    private Button fileButton;
    private Text goalText;

    /**
     * @wbp.parser.entryPoint (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse
     *      .swt.widgets.Composite)
     **/
    public void createControl(Composite parent) {
        Font font = parent.getFont();
        Composite comp = createComposite(parent, font, 1, 1, GridData.FILL_BOTH);
        createFileGroup(comp);
        createGoalGroup(comp);
        setControl(comp);

    }

    private void createGoalGroup(Composite parent) {
        Group goalGroup = new Group(parent, SWT.NONE);
        goalGroup.setText(Constants.GOAL_LABEL);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        goalGroup.setLayoutData(gd);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        goalGroup.setLayout(layout);
        goalGroup.setFont(parent.getFont());

        goalText = new Text(goalGroup, SWT.SINGLE | SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        goalText.setLayoutData(gd);
        goalText.setFont(parent.getFont());
    }

    private void createFileGroup(Composite parent) {
        Group fileGroup = new Group(parent, SWT.NONE);
        fileGroup.setText(Constants.FILE_LABEL);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        fileGroup.setLayoutData(gd);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        fileGroup.setLayout(layout);
        fileGroup.setFont(parent.getFont());

        fileText = new Text(fileGroup, SWT.SINGLE | SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        fileText.setLayoutData(gd);
        fileText.setFont(parent.getFont());
        fileText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                updateLaunchConfigurationDialog();
            }
        });

        fileButton = createPushButton(fileGroup, Constants.SEARCH_LABEL, null); //$NON-NLS-1$
        gd = new GridData(GridData.FILL_HORIZONTAL);
        fileButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                browseFiles();
            }
        });
    }

    /***
     * Open a resource chooser to select a file
     **/
    protected void browseFiles() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(getShell(), root, IResource.FILE);
        dialog.setTitle(Constants.SEARCH_TITLE);
        if (dialog.open() == Window.OK) {
            Object[] files = dialog.getResult();
            IFile file = (IFile) files[0];
            fileText.setText(file.getFullPath().toString());
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.
     *      debug.core.ILaunchConfigurationWorkingCopy)
     **/
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
     *      .debug.core.ILaunchConfiguration)
     **/
    public void initializeFrom(ILaunchConfiguration configuration) {

        try {
            String path = null;
            path = configuration.getAttribute(Constants.KEY_FILE_PATH, Constants.BLANK_STRING);

            String goal = null;
            goal = configuration.getAttribute(Constants.KEY_GOAL, Constants.BLANK_STRING);
            if (path != null) {
                fileText.setText(path);
            }
            if (goal != null) {
                goalText.setText(goal);
            }
        } catch (CoreException e) {
            setErrorMessage(e.getMessage());
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse
     *      .debug.core.ILaunchConfigurationWorkingCopy)
     **/
    public void performApply(ILaunchConfigurationWorkingCopy configuration) {
        String file = fileText.getText().trim();
        if (file.length() == 0) {
            file = null;
        }
        String goal = goalText.getText().trim();
        if (goal.length() == 0) {
            goal = null;
        }
        configuration.setAttribute(Constants.KEY_FILE_PATH, file);
        configuration.setAttribute(Constants.KEY_GOAL, goal);
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(org.eclipse.debug
     *      .core.ILaunchConfiguration)
     **/
    public boolean isValid(ILaunchConfiguration launchConfig) {
        setErrorMessage(null);
        setMessage(null);
        String text = fileText.getText();

        if (text.length() > 0) {
            IPath path = new Path(text);
            if (ResourcesPlugin.getWorkspace().getRoot().findMember(path) == null) {
                setErrorMessage("Specified file does not exist");
                return false;
            }
        } else {
            setMessage("Specify an file");
        }
        return true;
    }

    public Composite createComposite(Composite parent, Font font, int columns, int hspan, int fill) {
        Composite g = new Composite(parent, SWT.NONE);
        g.setLayout(new GridLayout(columns, false));
        g.setFont(font);
        GridData gd = new GridData(fill);
        gd.horizontalSpan = hspan;
        g.setLayoutData(gd);
        return g;
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
     **/
    public Image getImage() {
        return Activator.getImageDescriptor(Constants.NPM_ICON).createImage();
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
     **/
    public String getName() {
        return MAIN_TAB_NAME;
    }

}
