package org.nodeclipse.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.ui.Activator;

public class NodePreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

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
		addField(new FileFieldEditor(PreferenceConstants.P_NODE_PATH,
				"Node Path:", getFieldEditorParent()));
	}

}