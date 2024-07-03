package org.example.controller;

import org.example.contracts.CustomGasProvider;
import org.example.contracts.SimpleAuth;
import org.example.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.RawTransactionManager;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class UserController {
    private final Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));

    // 每个用户注册需要更换私钥
    private final Credentials credentials = Credentials.create("0x0a8a11a4a9a3a0a956886c77716a0fede2e707a36083ed9a16403c94acecfe43");
    private final SimpleAuth contract;
    private static final BigInteger GAS = BigInteger.valueOf(4_500_000);

    public UserController() {
        contract = SimpleAuth.load(
                // 合约地址
                "0xB7485d73e125c789b0C4ae4fB86C3E0AD3c609bb",
                web3j,
                new RawTransactionManager(web3j, credentials),
                new CustomGasProvider()
        );
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            String passwordHash = Numeric.toHexStringNoPrefix(Hash.sha3(user.getPassword().getBytes(StandardCharsets.UTF_8)));
            TransactionReceipt transactionReceipt = contract.register(user.getUsername(), passwordHash, user.getUsertype(), user.getPhone(), user.getEmail(), user.getPlace()).send();

            if (transactionReceipt.isStatusOK()) {
                return ResponseEntity.ok("Registration successful");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: Transaction failed with status " + transactionReceipt.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            TransactionReceipt transactionReceipt = contract.login(user.getUsername(), Numeric.toHexStringNoPrefix(Hash.sha3(user.getPassword().getBytes(StandardCharsets.UTF_8)))).send();

            if (transactionReceipt.isStatusOK()) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: Transaction failed with status " + transactionReceipt.getStatus());
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
        }
    }
}