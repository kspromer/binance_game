package io.renren;

import org.tron.trident.core.ApiWrapper;
import org.tron.trident.core.contract.Contract;
import org.tron.trident.core.contract.Trc20Contract;
import org.tron.trident.core.key.KeyPair;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/12 22:36
 */
public class App {
    public static void main(String[] args) {
        System.out.println("1");
        ApiWrapper wrapper = new ApiWrapper("grpc.trongrid.io:50051", "grpc.trongrid.io:50052", KeyPair.generate().toPrivateKey());
        System.out.println("2");
        Contract contract = wrapper.getContract("TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t");
        Trc20Contract token = new Trc20Contract(contract, "THPvaUhoh2Qn2y9THCZML3H815hhFhn5YC", wrapper);
        BigInteger balanceOf = token.balanceOf("TAGpBun7fpwZng9p9Dndq9mTFyBof55Zpe");
        System.out.println(balanceOf);
        BigDecimal currentBalanceOf = Convert.fromWei(balanceOf.toString(), Convert.Unit.MWEI);
        System.out.println(currentBalanceOf);
    }
}
