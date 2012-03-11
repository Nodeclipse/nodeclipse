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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.nodejs.ide.eclipse.Activator;
import org.nodejs.ide.eclipse.Constants;

public class LaunchConfigurationMainTab extends AbstractLaunchConfigurationTab {

    private Text fileText;
    private Button fileButton;
    //private Combo typeCombo;

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
        //createVerticalSpacer(comp, 1);
        //createEmulatorGroup(comp);
        setControl(comp);

    }

//    private void createEmulatorGroup(Composite parent) {
//        Group group = new Group(parent, SWT.NONE);
//        group.setText("&Type");
//        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
//        group.setLayoutData(gd);
//        GridLayout layout = new GridLayout();
//        layout.numColumns = 1;
//        group.setLayout(layout);
//        group.setFont(parent.getFont());
//
//        typeCombo = new Combo(group, SWT.READ_ONLY);
//        typeCombo.add("6.0.0");
//        gd = new GridData(GridData.FILL_HORIZONTAL);
//        typeCombo.setLayoutData(gd);
//        typeCombo.setFont(parent.getFont());
//    }

    private void createFileGroup(Composite parent) {
        Group fileGroup = new Group(parent, SWT.NONE);
        fileGroup.setText("&XML File");
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

        fileButton = createPushButton(fileGroup, "&Browse...", null); //$NON-NLS-1$
        gd = new GridData(GridData.FILL_HORIZONTAL);
        fileButton.addSelectionListener(new SelectionAdapter() {
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
            program = configuration.getAttribute(Constants.FILE, "");
//            String emulatorName = configuration.getAttribute("type", (String) null);
            if (program != null) {
                fileText.setText(program);
            }
//            if (emulatorName == null) {
//                typeCombo.select(0);
//            } else {
//                for (int i = 0; i < typeCombo.getItemCount(); i++) {
//                    if (emulatorName.equals(typeCombo.getItem(i))) {
//                        typeCombo.select(i);
//                    }
//                }
//            }

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
        String program = fileText.getText().trim();
        if (program.length() == 0) {
            program = null;
        }
//        String typeName = typeCombo.getText().trim();
//        if (typeName.length() == 0) {
//            typeName = null;
//        }

        configuration.setAttribute(Constants.FILE, program);
        // configuration.setAttribute(LaunchConstants._TYPE, typeName);

        // perform resource mapping for contextual launch
        // IResource[] resources = null;
        // if (program != null) {
        // IPath path = new Path(program);
        // IResource res =
        // ResourcesPlugin.getWorkspace().getRoot().findMember(path);
        // if (res != null) {
        // resources = new IResource[] { res };
        // }
        // }
        // configuration.setMappedResources(resources);
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

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
     **/
    public Image getImage() {
        return Activator.getImageDescriptor(Constants.NODEJS_ICON).createImage();
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
     **/
    public String getName() {
        return Constants.MAIN_TAB_NAME;
    }

}
