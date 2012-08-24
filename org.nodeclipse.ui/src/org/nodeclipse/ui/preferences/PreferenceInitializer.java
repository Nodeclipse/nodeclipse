package org.nodeclipse.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.util.OSUtils;

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
        String path = "/usr/local/bin/node";
        if (OSUtils.isWindows()) {
            path = "C:/Program Files/nodejs/node.exe";
        }
        store.setDefault(PreferenceConstants.NODE_PATH, path);
    }

}