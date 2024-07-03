package com.student.system.util;

import com.student.system.config.Config;
import com.student.system.user.UserContext;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Util {
	public static void writeUserContext(UserContext userContext) throws Exception {
		String directoryPath = "users/" + userContext.getAffiliation();
		String filePath = directoryPath + "/" + userContext.getName() + ".ser";
		File directory = new File(directoryPath);
		if (!directory.exists())
			directory.mkdirs();

		FileOutputStream file = new FileOutputStream(filePath);
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(userContext);

		out.close();
		file.close();
	}


	public static UserContext readUserContext(String affiliation, String username) throws Exception {
		String filePath = "users/" + affiliation + "/" + username + ".ser";
		File file = new File(filePath);
		if (file.exists()) {
			// Reading the object from a file
			FileInputStream fileStream = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileStream);

			// Method for deserialization of object
			UserContext uContext = (UserContext) in.readObject();

			in.close();
			fileStream.close();
			return uContext;
		}
		return null;
	}

	public static void cleanUp() {
		String directoryPath = "users";
		File directory = new File(directoryPath);
		deleteDirectory(directory);
	}

	public static boolean deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDirectory(children[i]);
				if (!success) {
					return false;
				}
			}
		}

		// either file or an empty directory
		Logger.getLogger(Util.class.getName()).log(Level.INFO, "Deleting - " + dir.getName());
		return dir.delete();
	}

	static public Properties gete2ePro(String type, String name) {
		Properties pro = new Properties();
		String orgName = name.substring(name.indexOf(".")+1);

		File cert = Paths.get("src/main/resources/"+File.separator , "crypto-config",
				type + "Organizations", orgName, type + "s", name, "tls", "server.crt").toFile();
		if (!cert.exists()) {
			throw new RuntimeException(String.format("Missing cert file for : %s.Could not find at location : %s", name,
					cert.getAbsolutePath()));
		}
		pro.setProperty("pemFile", cert.getAbsolutePath());
		pro.setProperty("hostnameOverride", name);
		pro.setProperty("sslProvider", "openSSL");
		pro.setProperty("negotiationType", "TLS");
		return pro;

	}
	public static void main(String[] args) {
		Properties pro = gete2ePro("orderer", Config.ORDERER_NAME);
	}
}