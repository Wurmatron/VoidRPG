package wurmatron.voidrpg.common.utils;

import wurmatron.voidrpg.common.config.Settings;
import wurmatron.voidrpg.common.reference.Global;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class VersionChecker {

		public static boolean isUpdated () {
				if (getVersion(Global.UPDATE_URL) != null && getVersion(Global.UPDATE_URL).equals(Global.VERSION) && Settings.updateCheck)
						return true;
				return false;
		}

		public static String getVersion (String updateLink) {
				if (Settings.updateCheck && updateLink != null) {
						String[] temp = readURL(updateLink).split("\n");
						for (String t : temp) {
								if (t.startsWith("mod_version="))
										return t.replace("mod_version=", "");
						}
				}
				return null;
		}

		private static String readURL (String theUrl) {
				StringBuilder content = new StringBuilder();
				try {
						URL url = new URL(theUrl);
						URLConnection urlConnection = url.openConnection();
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
						String line;
						while ((line = bufferedReader.readLine()) != null) {
								content.append(line + "\n");
						}
						bufferedReader.close();
				} catch (Exception e) {
						LogHandler.debug(e.getLocalizedMessage());
				}
				return content.toString();
		}
}
