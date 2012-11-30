package org.nodeclipse.ui.preferences;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.util.Constants;
import org.nodeclipse.ui.util.OSUtils;
import org.nodeclipse.ui.util.ProcessUtils;

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
		String express_path = "/usr/local/bin/express";
		File file;
		if (OSUtils.isWindows()) {
			path = "C:/Program Files/nodejs/node.exe".replace('/', File.separatorChar);
			express_path = System.getProperty("user.home") 
					+ "/AppData/Roaming/npm/express.cmd".replace('/', File.separatorChar);
		}
		if (OSUtils.isMacOS()) {
			file = new File(path);
			if (!file.exists()) {
				path = "/opt/local/bin/node";
			}
			file = new File(express_path);
			if (!file.exists()) {
				express_path = "/opt/local/bin/express";
			}
		}
		file = new File(path);
		if (file.exists()) {
			store.setDefault(PreferenceConstants.NODE_PATH, path);
		}
		file = new File(express_path);
		if (file.exists()) {
			store.setDefault(PreferenceConstants.EXPRESS_PATH, express_path);
			store.setDefault(PreferenceConstants.EXPRESS_VERSION,
					getExpressVersion(express_path));
		}
	}

	private String getExpressVersion(String express) {
		List<String> cmdLine = new ArrayList<String>();
		cmdLine.add(ProcessUtils.getExpressPath());
		cmdLine.add("--version");
		String ret = Constants.BLANK_STRING;
		try {
			ret = ProcessUtils.exec(cmdLine, null);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ret;
	}
}