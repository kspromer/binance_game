package io.renren.config.bsc;

import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.math.BigInteger;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2021/12/1 02:57
 */
public class DyProvider extends StaticGasProvider {

    public DyProvider() {
        super(null, null);
    }

    @Override
    public BigInteger getGasPrice(String contractFunc) {
        return Convert.toWei("0.000000005000000000", Convert.Unit.ETHER).toBigInteger();
    }

    @Override
    public BigInteger getGasPrice() {
        return super.getGasPrice();
    }

    @Override
    public BigInteger getGasLimit(String contractFunc) {
        BigInteger gasLimit = null;
//        if (contractFunc.equals(FUNC_UNLOCKLEVEL)) {
//            gasLimit = BigInteger.valueOf(213673);
//        }else if (contractFunc.equals(FUNC_FIGHT)) {
//            gasLimit = BigInteger.valueOf(178564);
//        }else if (contractFunc.equals(FUNC_CLAIMREWARDS)) {
//            gasLimit = BigInteger.valueOf(72301);
//        }else
        if (contractFunc.equals("transfer")) {
            gasLimit = BigInteger.valueOf(66043);
        }else if (contractFunc.equals("approve")) {
            gasLimit = BigInteger.valueOf(54404);
        }else if (contractFunc.equals("setApprovalForAll")) {
            gasLimit = BigInteger.valueOf(211057);
        }else if (contractFunc.equals("deposit")) {
            gasLimit = BigInteger.valueOf(211057);
        }else if (contractFunc.equals("deposit")) {
            gasLimit = BigInteger.valueOf(211057);
        }else if (contractFunc.equals("breedBNB")) {
            gasLimit = BigInteger.valueOf(1000000);
        }else if (contractFunc.equals("transferFrom")) {
            gasLimit = BigInteger.valueOf(88232);
        }else if (contractFunc.equals("buyBus")) {
            gasLimit = BigInteger.valueOf(499997);
        }else if (contractFunc.equals("buyPlayer")) {
            gasLimit = BigInteger.valueOf(499997);
        }else if (contractFunc.equals("safeTransferFrom")) {
            gasLimit = BigInteger.valueOf(208232);
        }else if (contractFunc.equals("accept")) {
            gasLimit = BigInteger.valueOf(1000000);
        }else if (contractFunc.equals("buyContracts")) {
            gasLimit = BigInteger.valueOf(1000000);
        }else if (contractFunc.equals("playGame")) {
            gasLimit = BigInteger.valueOf(500000);
        }else if (contractFunc.equals("enterStaking")) {
            gasLimit = BigInteger.valueOf(200000);
        }else if (contractFunc.equals("multisendToken")) {
            gasLimit = BigInteger.valueOf(1000000);
        }else if (contractFunc.equals("0xa2d2dabd")) {
            gasLimit = BigInteger.valueOf(1000000);
        }
        return BigInteger.valueOf(1000000);
    }

    @Override
    public BigInteger getGasLimit() {
        return super.getGasLimit();
    }
}
