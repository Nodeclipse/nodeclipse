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
		//TODO 初始化设置这个很坑爹的说，一开始的用户根本不知道需要设置这个，所以肯定是运行出现错误的
		//window默认是C:/Program Files/nodejs/node.exe *nix下默认是/usr/local/bin/node
		store.setDefault(PreferenceConstants.P_NODE_PATH, "node");
	}

}