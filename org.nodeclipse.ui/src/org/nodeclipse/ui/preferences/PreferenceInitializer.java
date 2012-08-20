package org.nodeclipse.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.ui.Activator;

/**
 * 
 * @author oncereply
 * 
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public PreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		//TODO 这个地方应该像Android那样主动提示设置哇...
		store.setDefault(PreferenceConstants.P_NODE_PATH, "node");
	}

}