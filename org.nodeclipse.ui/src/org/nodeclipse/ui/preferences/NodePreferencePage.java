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

	@Override
	public boolean isValid() {
		//window默认是C:/Program Files/nodejs/node.exe *nix下默认是/usr/local/bin/node
		//这个最好可以检测一些这些是不是存在，如果不存在提示用户安装
		return super.isValid();
	}

}