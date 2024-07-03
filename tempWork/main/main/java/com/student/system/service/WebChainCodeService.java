package com.student.system.service;

import com.student.system.client.CAClient;
import com.student.system.client.ChannelClient;
import com.student.system.client.FabricClient;
import com.student.system.config.Config;
import com.student.system.domain.Course;
import com.student.system.domain.EvalstandardDO;
import com.student.system.domain.RecordBlock;
import com.student.system.user.UserContext;
import com.student.system.util.*;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class WebChainCodeService {
    @Autowired
    UserService userService;
    @Autowired
    private IpfsService ipfsService;
    private static final byte[] EXPECTED_EVENT_DATA = "!".getBytes(UTF_8);
    private static final String EXPECTED_EVENT_NAME = "event";

    /**
     * 实例化链码
     * @return
     */
    public BaseData invokeChaincode(String argument){
        try {
            Util.cleanUp();
            Course course = Course.fromJSON(argument);
            String hashstr = Md5Util.generateHash(course.toString());
            String ipfshash = ipfsService.addRecode(argument);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,"ipfshash============="+ipfshash);
            RecordBlock recordBlock = new RecordBlock();
            recordBlock.setHashstr(hashstr);
            recordBlock.setTraceno(course.getBlock_key());
            System.out.println("AES加密前：" + ipfshash);
            String encrypt = AesEncryptUtils.encrypt(ipfshash,AesEncryptUtils.KEY);
            System.out.println("AES加密后：" + encrypt);
            String decrypt = AesEncryptUtils.decrypt(encrypt, AesEncryptUtils.KEY);
            System.out.println("AES解密后：" + decrypt);
            recordBlock.setE_Addr(encrypt);
            recordBlock.setBlock_key(course.getBlock_key());
            UserContext adminUser = new UserContext();
            adminUser.setName(Config.ADMIN);
            adminUser.setAffiliation(Config.ORG1);
            adminUser.setMspId(Config.ORG1_MSP);
            File f = new File (Config.CA1_TLSFILE);
            String certficate = new String (IOUtils.toByteArray(new FileInputStream(f)),"UTF-8");
            Properties properties = new Properties();
            properties.put("pemBytes", certficate.getBytes());
            properties.setProperty("pemFile", f.getAbsolutePath());
            properties.setProperty("allowAllHostNames", "true");
            CAClient caclient=new  CAClient(Config.CA1NAME, Config.CA_ORG1_URL, properties);
            caclient.setAdminUserContext(adminUser);
            adminUser =  caclient.enrollAdminUserTLS(Config.ADMIN, Config.ADMIN_P);

            FabricClient fabClient = new FabricClient(adminUser);

            ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
            Channel channel = channelClient.getChannel();
            Properties properties1 = Util.gete2ePro("peer",Config.ORG1_PEER_0);
            Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL,properties1);
            Properties properties2 = Util.gete2ePro("peer",Config.ORG2_PEER_0);
            Peer peer1 = fabClient.getInstance().newPeer(Config.ORG2_PEER_0, Config.ORG2_PEER_0_URL,properties2);
            Properties orderPro = Util.gete2ePro("orderer",Config.ORDERER_NAME);
            Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL,orderPro);

            channel.addPeer(peer);
            channel.addPeer(peer1);
            channel.addOrderer(orderer);
            channel.initialize();

            TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
            ChaincodeID ccid = ChaincodeID.newBuilder().setName(Config.CHAINCODE_NAME).build();
            request.setChaincodeID(ccid);
            request.setFcn("createBlockInfo");
            String[] arguments = {recordBlock.toString()};
            request.setArgs(arguments);
            request.setProposalWaitTime(1000);


            Map<String, byte[]> tm2 = new HashMap<>();
            tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8));
            tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8));
            tm2.put("result", ":)".getBytes(UTF_8));
            tm2.put(EXPECTED_EVENT_NAME, EXPECTED_EVENT_DATA);
            request.setTransientMap(tm2);
            Collection<ProposalResponse> responses = channelClient.sendTransactionProposal(request);
            for (ProposalResponse res: responses) {
                ChaincodeResponse.Status status = res.getStatus();
                Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Invoked on "+Config.CHAINCODE_NAME + ". Status - " + status);
            }

            String msg = "新增区块链信息，执行结果："+responses;
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,msg);
            return ResultGenerator.genSuccessResult("新增成功！");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, e.getMessage());
            return ResultGenerator.genFailResult("实例化失败！"+e.getMessage());
        }
    }

    /**
     * 链码查询
     * @param key 溯源码
     *   type=0查询评分是否篡改username+courseno ： hash
     *            type=1查询课程 traceno ： recodeblock
     *            record再转course对象
     *            type=2 评价标准信息查询 traceno:评价标准
     * @return
     */
    public BaseData queryChainCode(String key,int type){
        try {
            Util.cleanUp();
            UserContext adminUser = new UserContext();
            adminUser.setName(Config.ADMIN);
            adminUser.setAffiliation(Config.ORG1);
            adminUser.setMspId(Config.ORG1_MSP);
            File f = new File (Config.CA1_TLSFILE);
            String certficate = new String (IOUtils.toByteArray(new FileInputStream(f)),"UTF-8");
            Properties properties = new Properties();
            properties.put("pemBytes", certficate.getBytes());
            properties.setProperty("pemFile", f.getAbsolutePath());
            properties.setProperty("allowAllHostNames", "true");
            CAClient caclient=new  CAClient(Config.CA1NAME, Config.CA_ORG1_URL, properties);
            caclient.setAdminUserContext(adminUser);
            adminUser =  caclient.enrollAdminUserTLS("admin", "adminpw");
            FabricClient fabClient = new FabricClient(adminUser);
            ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
            Channel channel = channelClient.getChannel();
            Properties properties1 = Util.gete2ePro("peer",Config.ORG1_PEER_0);
            Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL,properties1);

            Properties orderPro = Util.gete2ePro("orderer", Config.ORDERER_NAME);
            Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL,orderPro);
            channel.addPeer(peer);
            channel.addOrderer(orderer);
            channel.initialize();
            String[] args1 = {key};
            String msg = "queryChainCode -  "+ args1[0];
            Logger.getLogger(WebChainCodeService.class.getName()).log(Level.INFO, msg);
            msg = "queryChainCodeByNo";
            //获取信息
            String stringResponse ="";
            Collection<ProposalResponse>  responses1Query = channelClient.queryByChainCode(Config.CHAINCODE_NAME, msg, args1);
            for (ProposalResponse pres : responses1Query) {
                stringResponse = new String(pres.getChaincodeActionResponsePayload());
//解析数据
                RecordBlock recordBlock = new RecordBlock();
                recordBlock = RecordBlock.fromJSON(stringResponse);
                System.out.println("AES解密前：" + recordBlock.getE_Addr());
                String ipfshash = AesEncryptUtils.decrypt(recordBlock.getE_Addr(), AesEncryptUtils.KEY);
                System.out.println("AES解密后：" + ipfshash);
                String recordJson = ipfsService.selectRecode(ipfshash);
                if(type==1){
                    Course course= Course.fromJSON(recordJson);
                    String hashstr = Md5Util.generateHash(recordJson);
                    if(!hashstr.equals(recordBlock.getHashstr())){
                        return ResultGenerator.genFailResult("数据已被篡改！");
                    }
                    return ResultGenerator.genSuccessResult(course);
                }else if(type==2){
                    EvalstandardDO evalstandard= EvalstandardDO.fromJSON(recordJson);
                    return ResultGenerator.genSuccessResult(evalstandard);
                }else{
                    return ResultGenerator.genSuccessResult(recordJson);
                }
            }
            return ResultGenerator.genSuccessResult(stringResponse);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, e.getMessage());
            return ResultGenerator.genFailResult("error！"+e.getMessage());
        }
    }
}