package io.renren.common.utils.wallet;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.crypto.*;
import org.bitcoinj.wallet.DeterministicSeed;
import org.springframework.stereotype.Component;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2021/11/28 18:02
 */
@Slf4j
@Component
public class WalletClient {

    public String getAddress(ECKeyPair ecKeyPair) {
        return "0x" + Keys.getAddress(ecKeyPair);
    }
    /**
     * 创建秘钥对
     */
    public ECKeyPair createECKeyPair(String meMnemonic,Integer childNumber) {
        List<String> words = Splitter.on(" ").splitToList(meMnemonic);
        return createECKeyPair(words,childNumber);
    }

    /**
     * 创建秘钥对
     */
    public ECKeyPair createECKeyPair(List<String> words,Integer childNumber) {
        //获取主私钥
        DeterministicKey masterPrivateKey = createMasterPrivateKey(words);
        //获取接下来派生函数的key
        DeterministicKey accountKey = childDerivationKey(masterPrivateKey);
        //子地址的创建秘钥对
        DeterministicKey childKey = HDKeyDerivation.deriveChildKey(accountKey, childNumber);
        byte[] bytes = childKey.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(bytes);
        return keyPair;
    }

    /**
     * 获取接下来派生函数的key
     * @param masterPrivateKey
     * @return
     */
    public DeterministicKey childDerivationKey(DeterministicKey masterPrivateKey){
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
        DeterministicKey accountKey = deterministicHierarchy.get(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true);
        return accountKey;
    }

    /**
     * 主私钥
     * @return
     */
    public DeterministicKey createMasterPrivateKey(List<String> words) {
        //使用助记词生成钱包种子
        byte[] seed = MnemonicCode.toSeed(words, "");
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        return masterPrivateKey;
    }

    /**
     * 主私钥
     * @return
     */
    public DeterministicKey createMasterPrivateKey(String meMnemonic) {
        List<String> words = Splitter.on(" ").splitToList(meMnemonic);
        return createMasterPrivateKey(words);
    }

    /**
     * 生成助记词
     * @return
     */
    public List<String> generateMnemonic(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[DeterministicSeed.DEFAULT_SEED_ENTROPY_BITS / 8];
        secureRandom.nextBytes(entropy);
        List<String>  str = null;
        try {
            str = MnemonicCode.INSTANCE.toMnemonic(entropy);
        } catch (MnemonicException.MnemonicLengthException e) {
            log.error("generateMnemonic error {}",e.getMessage());
        }
        return str;
    }

    public static void main(String[] args) {
        WalletClient walletClient = new WalletClient();
        List<String> strings = walletClient.generateMnemonic();
        String collect = strings.stream().collect(Collectors.joining());
        System.out.println(collect);
    }

    /**
     * path路径
     */
    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

}
