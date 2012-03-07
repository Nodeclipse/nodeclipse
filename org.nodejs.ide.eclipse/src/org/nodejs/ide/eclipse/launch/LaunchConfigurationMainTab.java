/*******************************************************************************
 * Copyright (c) 2012 Lamb. All rights reserved. 
 *
 *******************************************************************************/
package org.nodejs.ide.eclipse.launch;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.ui.IInternalDebugUIConstants;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.DebugUITools;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;

public class LaunchConfigurationMainTab extends AbstractLaunchConfigurationTab {

    private Text fProgramText;
    private Button fProgramButton;
    private Combo typeCombo;

    /**
     * @wbp.parser.entryPoint (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse
     *      .swt.widgets.Composite)
     **/
    public void createControl(Composite parent) {
        Font font = parent.getFont();
        Composite comp = createComposite(parent, font, 1, 1, GridData.FILL_BOTH);
        createKulGroup(comp);
        createVerticalSpacer(comp, 1);
        createEmulatorGroup(comp);
        setControl(comp);

    }

    private void createEmulatorGroup(Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setText("&Type");
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        group.setLayoutData(gd);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        group.setLayout(layout);
        group.setFont(parent.getFont());

        typeCombo = new Combo(group, SWT.READ_ONLY);
        typeCombo.add("6.0.0");
        gd = new GridData(GridData.FILL_HORIZONTAL);
        typeCombo.setLayoutData(gd);
        typeCombo.setFont(parent.getFont());
    }

    private void createKulGroup(Composite parent) {
        Group kulGroup = new Group(parent, SWT.NONE);
        kulGroup.setText("&XML File");
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        kulGroup.setLayoutData(gd);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        kulGroup.setLayout(layout);
        kulGroup.setFont(parent.getFont());

        fProgramText = new Text(kulGroup, SWT.SINGLE | SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        fProgramText.setLayoutData(gd);
        fProgramText.setFont(parent.getFont());
        fProgramText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                updateLaunchConfigurationDialog();
            }
        });

        fProgramButton = createPushButton(kulGroup, "&Browse...", null); //$NON-NLS-1$
        gd = new GridData(GridData.FILL_HORIZONTAL);
        fProgramButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                browseXMLFiles();
            }
        });
    }

    /***
     * Open a resource chooser to select a PDA program
     **/
    protected void browseXMLFiles() {
        ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(),
                IResource.FILE);
        dialog.setTitle("Xml Program");
        dialog.setMessage("Select XML Program");
        if (dialog.open() == Window.OK) {
            Object[] files = dialog.getResult();
            IFile file = (IFile) files[0];
            fProgramText.setText(file.getFullPath().toString());
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.
     *      debug.core.ILaunchConfigurationWorkingCopy)
     **/
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
        System.out.println("setdefaults");
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
     *      .debug.core.ILaunchConfiguration)
     **/
    public void initializeFrom(ILaunchConfiguration configuration) {

        try {
            String program = null;
            program = configuration.getAttribute(LaunchConstants.FILE, "");
            String emulatorName = configuration.getAttribute("type", (String) null);
            if (program != null) {
                fProgramText.setText(program);
            }
            if (emulatorName == null) {
                typeCombo.select(0);
            } else {
                for (int i = 0; i < typeCombo.getItemCount(); i++) {
                    if (emulatorName.equals(typeCombo.getItem(i))) {
                        typeCombo.select(i);
                    }
                }
            }

        } catch (CoreException e) {
            setErrorMessage(e.getMessage());
        }
        // #endif
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse
     *      .debug.core.ILaunchConfigurationWorkingCopy)
     **/
    public void performApply(ILaunchConfigurationWorkingCopy configuration) {
        String program = fProgramText.getText().trim();
        if (program.length() == 0) {
            program = null;
        }
        String typeName = typeCombo.getText().trim();
        if (typeName.length() == 0) {
            typeName = null;
        }

        configuration.setAttribute(LaunchConstants.FILE, program);
        // configuration.setAttribute(LaunchConstants._TYPE, typeName);

        // perform resource mapping for contextual launch
        IResource[] resources = null;
        if (program != null) {
            IPath path = new Path(program);
            IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
            if (res != null) {
                resources = new IResource[] { res };
            }
        }
        configuration.setMappedResources(resources);
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
     **/
    public String getName() {
        return "Main";
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
        String text = fProgramText.getText();

        if (text.length() > 0) {
            IPath path = new Path(text);
            if (ResourcesPlugin.getWorkspace().getRoot().findMember(path) == null) {
                setErrorMessage("Specified xml file does not exist");
                return false;
            }
        } else {
            setMessage("Specify an xml file");
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
    
    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
     */
    @SuppressWarnings("restriction")
    public Image getImage() {
        return DebugUITools.getImage(IInternalDebugUIConstants.IMG_OBJS_ARRAY_PARTITION);
    }

}
