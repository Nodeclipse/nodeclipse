package org.nodeclipse.ui.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.preferences.PreferenceConstants;

public class ProcessUtils {
	public static String getNodePath() {
		return Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.NODE_PATH);
	}

	public static String getNodeFolder() {
		String nodePath = getNodePath();
		return nodePath.substring(0, nodePath.lastIndexOf(File.separator));
	}

	public static String getNpmPath() {
		String nodePath = Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.NODE_PATH);
		String npmPath = nodePath.substring(0,
				nodePath.lastIndexOf(File.separator) + 1);
		if (OSUtils.isWindows()) {
			return npmPath + Constants.NPM_CMD;
		} else {
			return npmPath + Constants.NPM;
		}
	}

	public static String getExpressPath() {
		return Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.EXPRESS_PATH);
	}

	public static String getExpressVersion() {
		return Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.EXPRESS_VERSION);
	}

	public static int getExpressMajorVersion() {
		String ver = getExpressVersion();
		int idx = ver.indexOf('.');
		if(idx < 0) {
			return 3;
		}
		ver = ver.substring(0, idx);
		int ret = Integer.parseInt(ver);
		return ret;
	}
	
	public static String exec(List<String> cmdLine, File dir)
			throws InvocationTargetException {
		String[] cmds = {};
		cmds = cmdLine.toArray(cmds);
		ProcessBuilder builder = new ProcessBuilder(cmds);
		if (dir != null) {
			builder.directory(dir);
		}

		if(OSUtils.isMacOS()) {
			Map<String, String> env = builder.environment();
			env.put("PATH", getNodeFolder());
		}
		
		StringBuilder sb = new StringBuilder();
		try {
			Process p = builder.start();
			String line;
			BufferedReader bri = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			BufferedReader bre = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));
			while ((line = bri.readLine()) != null) {
				sb.append(line);
				System.out.println(line);
			}
			bri.close();
			while ((line = bre.readLine()) != null) {
				sb.append(line);
				System.out.println(line);
			}
			bre.close();
			p.waitFor();
		} catch (Exception e) {
			throw new InvocationTargetException(e);
		}

		return sb.toString();	
	}
}
