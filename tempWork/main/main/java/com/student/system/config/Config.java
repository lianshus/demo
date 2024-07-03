package com.student.system.config;

import java.io.File;

public class Config {
	private Config() {
		throw new IllegalStateException("Utility class");
	}
	public static final String baseUrl ="192.168.100.100";
	public static final String grpc="grpcs://";
	public static final String CA_ORG1_URL = "https://" + baseUrl + ":7054";
	public static final String ORG1_MSP = "Org1MSP";
	public static final String ORG1 = "org1";
	public static final String CA1NAME = "ca-org1";
	public static final String ADMIN = "admin";
	public static final String ADMIN_P = "adminpw";

	public static final String CA1_TLSFILE = "src/main/resources/"+File.separator+ "crypto-config" + File.separator + "peerOrganizations" + File.separator
			+"org1.example.com"+File.separator+ "ca"+ File.separator+"ca.org1.example.com-cert.pem";

	public static final String ORDERER_URL = grpc+ baseUrl + ":7050";

	public static final String ORDERER_NAME = "orderer.example.com";

	public static final String CHANNEL_NAME = "mychannel";

	public static final String ORG1_PEER_0 = "peer0.org1.example.com";

	public static final String ORG1_PEER_0_URL = grpc + baseUrl+ ":7051";

	public static final String ORG2_PEER_0 = "peer0.org2.example.com";

	public static final String ORG2_PEER_0_URL =grpc + baseUrl+ ":9051";

	public static final String CHAINCODE_NAME = "fabric_chaincode";
}