package org.example.contracts;

import org.web3j.tx.gas.ContractGasProvider;
import java.math.BigInteger;

public class CustomGasProvider implements ContractGasProvider {
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_500_000);
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L); // 20 Gwei

    @Override
    public BigInteger getGasPrice(String contractFunc) {
        return GAS_PRICE;
    }

    @Override
    public BigInteger getGasPrice() {
        return GAS_PRICE;
    }

    @Override
    public BigInteger getGasLimit(String contractFunc) {
        return GAS_LIMIT;
    }

    @Override
    public BigInteger getGasLimit() {
        return GAS_LIMIT;
    }
}
