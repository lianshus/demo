package org.example.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class SimpleAuth extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b50611472806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c8063104ace021461004657806358467dbc14610268578063a87430ba1461034c575b600080fd5b610266600480360360c081101561005c57600080fd5b810190808035906020019064010000000081111561007957600080fd5b82018360208201111561008b57600080fd5b803590602001918460018302840111640100000000831117156100ad57600080fd5b9091929391929390803590602001906401000000008111156100ce57600080fd5b8201836020820111156100e057600080fd5b8035906020019184600183028401116401000000008311171561010257600080fd5b90919293919293908035906020019064010000000081111561012357600080fd5b82018360208201111561013557600080fd5b8035906020019184600183028401116401000000008311171561015757600080fd5b90919293919293908035906020019064010000000081111561017857600080fd5b82018360208201111561018a57600080fd5b803590602001918460018302840111640100000000831117156101ac57600080fd5b9091929391929390803590602001906401000000008111156101cd57600080fd5b8201836020820111156101df57600080fd5b8035906020019184600183028401116401000000008311171561020157600080fd5b90919293919293908035906020019064010000000081111561022257600080fd5b82018360208201111561023457600080fd5b8035906020019184600183028401116401000000008311171561025657600080fd5b90919293919293905050506105c0565b005b6103346004803603604081101561027e57600080fd5b810190808035906020019064010000000081111561029b57600080fd5b8201836020820111156102ad57600080fd5b803590602001918460018302840111640100000000831117156102cf57600080fd5b9091929391929390803590602001906401000000008111156102f057600080fd5b82018360208201111561030257600080fd5b8035906020019184600183028401116401000000008311171561032457600080fd5b9091929391929390505050610ae2565b60405180821515815260200191505060405180910390f35b61038e6004803603602081101561036257600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611032565b60405180806020018781526020018060200180602001806020018060200186810386528c818151815260200191508051906020019080838360005b838110156103e45780820151818401526020810190506103c9565b50505050905090810190601f1680156104115780820380516001836020036101000a031916815260200191505b5086810385528a818151815260200191508051906020019080838360005b8381101561044a57808201518184015260208101905061042f565b50505050905090810190601f1680156104775780820380516001836020036101000a031916815260200191505b50868103845289818151815260200191508051906020019080838360005b838110156104b0578082015181840152602081019050610495565b50505050905090810190601f1680156104dd5780820380516001836020036101000a031916815260200191505b50868103835288818151815260200191508051906020019080838360005b838110156105165780820151818401526020810190506104fb565b50505050905090810190601f1680156105435780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b8381101561057c578082015181840152602081019050610561565b50505050905090810190601f1680156105a95780820380516001836020036101000a031916815260200191505b509b50505050505050505050505060405180910390f35b600073ffffffffffffffffffffffffffffffffffffffff1660018d8d604051808383808284378083019250505092505050908152602001604051809103902060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146106a9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f557365726e616d6520616c72656164792074616b656e2e00000000000000000081525060200191505060405180910390fd5b6000801b6000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001015414610762576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f5573657220616c726561647920726567697374657265642e000000000000000081525060200191505060405180910390fd5b60008a8a6040516020018083838082843780830192505050925050506040516020818303038152906040528051906020012090506040518060c001604052808e8e8080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505081526020018281526020018a8a8080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050815260200188888080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050815260200186868080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050815260200184848080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050508152506000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000820151816000019080519060200190610971929190611366565b50602082015181600101556040820151816002019080519060200190610998929190611366565b5060608201518160030190805190602001906109b5929190611366565b5060808201518160040190805190602001906109d2929190611366565b5060a08201518160050190805190602001906109ef929190611366565b509050503360018e8e604051808383808284378083019250505092505050908152602001604051809103902060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503373ffffffffffffffffffffffffffffffffffffffff167fc04f3273accb8a629beba8b5650dda611095e2f1f68083a832d9a72bf9b5bfe58e8e60405180806020018281038252848482818152602001925080828437600081840152601f19601f820116905080830192505050935050505060405180910390a250505050505050505050505050565b60008073ffffffffffffffffffffffffffffffffffffffff1660018686604051808383808284378083019250505092505050908152602001604051809103902060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610bcd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f557365726e616d6520646f6573206e6f742065786973742e000000000000000081525060200191505060405180910390fd5b610bd56113e6565b60008060018888604051808383808284378083019250505092505050908152602001604051809103902060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206040518060c0016040529081600082018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610cfe5780601f10610cd357610100808354040283529160200191610cfe565b820191906000526020600020905b815481529060010190602001808311610ce157829003601f168201915b5050505050815260200160018201548152602001600282018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610daa5780601f10610d7f57610100808354040283529160200191610daa565b820191906000526020600020905b815481529060010190602001808311610d8d57829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e4c5780601f10610e2157610100808354040283529160200191610e4c565b820191906000526020600020905b815481529060010190602001808311610e2f57829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610eee5780601f10610ec357610100808354040283529160200191610eee565b820191906000526020600020905b815481529060010190602001808311610ed157829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f905780601f10610f6557610100808354040283529160200191610f90565b820191906000526020600020905b815481529060010190602001808311610f7357829003601f168201915b5050505050815250509050600084846040516020018083838082843780830192505050925050506040516020818303038152906040528051906020012082602001511490503373ffffffffffffffffffffffffffffffffffffffff167fba67bef358e4f607c3365cae079ac00416af2eb037c6327e26adb3bf82297aea8260405180821515815260200191505060405180910390a28092505050949350505050565b6000602052806000526040600020600091509050806000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156110de5780601f106110b3576101008083540402835291602001916110de565b820191906000526020600020905b8154815290600101906020018083116110c157829003601f168201915b505050505090806001015490806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111825780601f1061115757610100808354040283529160200191611182565b820191906000526020600020905b81548152906001019060200180831161116557829003601f168201915b505050505090806003018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112205780601f106111f557610100808354040283529160200191611220565b820191906000526020600020905b81548152906001019060200180831161120357829003601f168201915b505050505090806004018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112be5780601f10611293576101008083540402835291602001916112be565b820191906000526020600020905b8154815290600101906020018083116112a157829003601f168201915b505050505090806005018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561135c5780601f106113315761010080835404028352916020019161135c565b820191906000526020600020905b81548152906001019060200180831161133f57829003601f168201915b5050505050905086565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106113a757805160ff19168380011785556113d5565b828001600101855582156113d5579182015b828111156113d45782518255916020019190600101906113b9565b5b5090506113e2919061141f565b5090565b6040518060c001604052806060815260200160008019168152602001606081526020016060815260200160608152602001606081525090565b5b80821115611438576000816000905550600101611420565b509056fea2646970667358221220cea19c1aa9a94336fe8396b441960bf734e59148c7c8cda3ca0f979b9235199664736f6c634300060c0033";

    public static final String FUNC_USERS = "users";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_LOGIN = "login";

    public static final Event LOGINSUCCESSFUL_EVENT = new Event("LoginSuccessful", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event REGISTRATIONSUCCESSFUL_EVENT = new Event("RegistrationSuccessful", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected SimpleAuth(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SimpleAuth(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SimpleAuth(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SimpleAuth(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<LoginSuccessfulEventResponse> getLoginSuccessfulEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGINSUCCESSFUL_EVENT, transactionReceipt);
        ArrayList<LoginSuccessfulEventResponse> responses = new ArrayList<LoginSuccessfulEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LoginSuccessfulEventResponse typedResponse = new LoginSuccessfulEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.success = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LoginSuccessfulEventResponse> loginSuccessfulEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LoginSuccessfulEventResponse>() {
            @Override
            public LoginSuccessfulEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGINSUCCESSFUL_EVENT, log);
                LoginSuccessfulEventResponse typedResponse = new LoginSuccessfulEventResponse();
                typedResponse.log = log;
                typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.success = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LoginSuccessfulEventResponse> loginSuccessfulEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGINSUCCESSFUL_EVENT));
        return loginSuccessfulEventFlowable(filter);
    }

    public List<RegistrationSuccessfulEventResponse> getRegistrationSuccessfulEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTRATIONSUCCESSFUL_EVENT, transactionReceipt);
        ArrayList<RegistrationSuccessfulEventResponse> responses = new ArrayList<RegistrationSuccessfulEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RegistrationSuccessfulEventResponse typedResponse = new RegistrationSuccessfulEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.username = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RegistrationSuccessfulEventResponse> registrationSuccessfulEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RegistrationSuccessfulEventResponse>() {
            @Override
            public RegistrationSuccessfulEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REGISTRATIONSUCCESSFUL_EVENT, log);
                RegistrationSuccessfulEventResponse typedResponse = new RegistrationSuccessfulEventResponse();
                typedResponse.log = log;
                typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.username = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RegistrationSuccessfulEventResponse> registrationSuccessfulEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REGISTRATIONSUCCESSFUL_EVENT));
        return registrationSuccessfulEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> users(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_USERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> register(String username, String password, String usertype, String phone, String email, String place) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(username), 
                new org.web3j.abi.datatypes.Utf8String(password), 
                new org.web3j.abi.datatypes.Utf8String(usertype), 
                new org.web3j.abi.datatypes.Utf8String(phone), 
                new org.web3j.abi.datatypes.Utf8String(email), 
                new org.web3j.abi.datatypes.Utf8String(place)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> login(String username, String password) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LOGIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(username), 
                new org.web3j.abi.datatypes.Utf8String(password)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SimpleAuth load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleAuth(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SimpleAuth load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleAuth(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SimpleAuth load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SimpleAuth(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SimpleAuth load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SimpleAuth(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SimpleAuth> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleAuth.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SimpleAuth> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleAuth.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SimpleAuth> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleAuth.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SimpleAuth> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleAuth.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class LoginSuccessfulEventResponse extends BaseEventResponse {
        public String user;

        public Boolean success;
    }

    public static class RegistrationSuccessfulEventResponse extends BaseEventResponse {
        public String user;

        public String username;
    }
}
