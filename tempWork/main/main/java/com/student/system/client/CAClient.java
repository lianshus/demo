package com.student.system.client;

import com.student.system.config.Config;
import com.student.system.user.UserContext;
import com.student.system.util.Util;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CAClient {
	String caname;
	String caUrl;
	Properties caProperties;

	HFCAClient instance;

	UserContext adminContext;

	public UserContext getAdminUserContext() {
		return adminContext;
	}

	public void setAdminUserContext(UserContext userContext) {
		this.adminContext = userContext;
	}

	public CAClient(String caname ,String caUrl, Properties caProperties) throws MalformedURLException, IllegalAccessException, InstantiationException, ClassNotFoundException, CryptoException, InvalidArgumentException, NoSuchMethodException, InvocationTargetException {
		this.caname=caname;

		this.caUrl = caUrl;
		this.caProperties = caProperties;
		init();
	}

	public CAClient(String caUrl, Properties caProperties) throws MalformedURLException, IllegalAccessException, InstantiationException, ClassNotFoundException, CryptoException, InvalidArgumentException, NoSuchMethodException, InvocationTargetException {
		this.caUrl = caUrl;
		this.caProperties = caProperties;
		initTLS();
	}

	public void init() throws MalformedURLException, IllegalAccessException, InstantiationException, ClassNotFoundException, CryptoException, InvalidArgumentException, NoSuchMethodException, InvocationTargetException {
		CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
		instance = HFCAClient.createNewInstance(caUrl, caProperties);
		instance.setCryptoSuite(cryptoSuite);
	}


	public void initTLS() throws MalformedURLException, IllegalAccessException, InstantiationException, ClassNotFoundException, CryptoException, InvalidArgumentException, NoSuchMethodException, InvocationTargetException {
		CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
		try {
			instance = HFCAClient.createNewInstance(caname, caUrl, caProperties);
		} catch (org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException e) {
			e.printStackTrace();
		}
		instance.setCryptoSuite(cryptoSuite);
	}

	public HFCAClient getInstance() {
		return instance;
	}

	public UserContext enrollAdminUserTLS(String username, String password) throws Exception {

		UserContext userContext = Util.readUserContext(adminContext.getAffiliation(), username);
		if (userContext != null) {
			Logger.getLogger(CAClient.class.getName()).log(Level.WARNING, "CA -" + caUrl + " admin is already enrolled.");
			return userContext;
		}
		instance.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
		EnrollmentRequest enrollmentRequestTLS  = new EnrollmentRequest();
		enrollmentRequestTLS.addHost(Config.CA_ORG1_URL);
		enrollmentRequestTLS.setProfile("tls");
		Enrollment adminenroll = instance.enroll(username, password, enrollmentRequestTLS);
		adminContext.setEnrollment(adminenroll);
		Logger.getLogger(CAClient.class.getName()).log(Level.INFO, "CA -" + caUrl + " Enrolled Admin.");
		Util.writeUserContext(adminContext);
		return adminContext;
	}
}