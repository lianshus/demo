package com.student.system.client;

import org.hyperledger.fabric.sdk.BlockEvent.TransactionEvent;
import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ChannelClient {
	private static final byte[] EXPECTED_EVENT_DATA = "!".getBytes(UTF_8);
	private static final String EXPECTED_EVENT_NAME = "event";
	String name;
	Channel channel;
	FabricClient fabClient;
	String onChannelStr = " on channel ";
	public String getName() {
		return name;
	}

	public Channel getChannel() {
		return channel;
	}

	public FabricClient getFabClient() {
		return fabClient;
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param channel
	 * @param fabClient
	 */
	public ChannelClient(String name, Channel channel, FabricClient fabClient) {
		this.name = name;
		this.channel = channel;
		this.fabClient = fabClient;
	}

	/**
	 * Query by chaincode.
	 * 
	 * @param chaincodeName
	 * @param functionName
	 * @param args
	 * @return
	 * @throws InvalidArgumentException
	 * @throws ProposalException
	 */
	public Collection<ProposalResponse> queryByChainCode(String chaincodeName, String functionName, String[] args) throws ProposalException, InvalidArgumentException {
		String msg = "Querying " + functionName +onChannelStr+ channel.getName();
		Logger.getLogger(ChannelClient.class.getName()).log(Level.INFO,msg);
		QueryByChaincodeRequest request = fabClient.getInstance().newQueryProposalRequest();
		ChaincodeID ccid = ChaincodeID.newBuilder().setName(chaincodeName).build();
		request.setChaincodeID(ccid);
		request.setFcn(functionName);
		if (args != null)
			request.setArgs(args);
		return channel.queryByChaincode(request);
	}

	/**
	 * Send transaction proposal.
	 * 
	 * @param request
	 * @return
	 * @throws ProposalException
	 * @throws InvalidArgumentException
	 */
	public Collection<ProposalResponse> sendTransactionProposal(TransactionProposalRequest request) throws InvalidArgumentException, ProposalException {
		String msg = "Sending transaction proposal on channel " + channel.getName();
		Logger.getLogger(ChannelClient.class.getName()).log(Level.INFO,msg);
		Collection<ProposalResponse> response = channel.sendTransactionProposal(request, channel.getPeers());
		for (ProposalResponse pres : response) {
			String stringResponse = new String(pres.getChaincodeActionResponsePayload());
			msg = "Transaction proposal on channel " + channel.getName() + " " + pres.getMessage() + " "
					+ pres.getStatus() + " with transaction id:" + pres.getTransactionID();
			Logger.getLogger(ChannelClient.class.getName()).log(Level.INFO,msg);
			Logger.getLogger(ChannelClient.class.getName()).log(Level.INFO, stringResponse);
		}

		CompletableFuture<TransactionEvent> cf = channel.sendTransaction(response);
		Logger.getLogger(ChannelClient.class.getName()).log(Level.INFO, cf.toString());
		return response;
	}
}