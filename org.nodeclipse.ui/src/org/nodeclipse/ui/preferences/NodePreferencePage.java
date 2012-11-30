package org.nodeclipse.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.ui.Activator;

public class NodePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private FileFieldEditor fileFieldEditor;
    private FileFieldEditor expressPath;
    
    public NodePreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Node Settings");
    }

    @Override
    public void init(IWorkbench workbench) {

    }

    @Override
    protected void createFieldEditors() {
        fileFieldEditor = new FileFieldEditor(PreferenceConstants.NODE_PATH, "Node Path:", getFieldEditorParent());
        addField(fileFieldEditor);
        
        expressPath = new FileFieldEditor(PreferenceConstants.EXPRESS_PATH, "Express Path:", getFieldEditorParent());
        addField(expressPath);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

}