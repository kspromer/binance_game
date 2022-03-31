package io.renren;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.binance.client.SyncRequestClient;
import com.binance.client.model.enums.CandlestickInterval;
import com.binance.client.model.market.Candlestick;
import io.renren.common.utils.Decrypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-09-23 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DecryptTest {

    @Autowired
    private SyncRequestClient syncRequestClient;

    @Autowired
    private Decrypt decrypt;

    @Test
    public void test2() {
        DateTime end = new DateTime();
        List<Candlestick> btcusdt = syncRequestClient.getCandlestick("BTCUSDT", CandlestickInterval.FIVE_MINUTES, null, end.getTime(), 70);
        for (Candlestick candlestick : btcusdt) {
            System.out.println(JSONUtil.toJsonStr(candlestick));
        }
    }

    @Test
    public void test1() throws IOException, GeneralSecurityException {

        String[] split = "Y9zwcay7sXtRYBcAtRyd+CuRYirELrZ+D8QbApk8+rFawm7zIMnxYtIjDkyTUWkejtbvoUnmhSBrdivr7KtrBH79K1SsF/ZYHNkZO2qKVcgCSJrw3HENy6QhWx5IwRSToqhtTptkbtNKjk4/LrekE9cY9c6bvSd6aDfPRD8EDXs===JUfhswbz5gQM6pBG2h79wQ5pR3i7F7vUaJpLRc3kY2ABUoIosFkPBwIIpk0b3EQPBhTG2hQsxp0/9bOwV4w95EuIYOasbaV7CThpSMOF8e0U5oln3RTZ9hpzwMHWS4Xp/SzgyAl2ZG2eWyoyPd6CYB6mqOmS7o/wjES7BcYHoKo===Vfl2jScT+R/R2/YuYCYAACXw9mCVHOPkfiEW0N05nf1SL68NBnGkQjsqABr5gGrpYIDK2/1XZeXkJdfBNOIjhMJn9zDrGa5kElsWPZVVInMgxtvrpNLliy+gnWY6bBkc+d9MKZR2MYiyWApqX6X3PoFFcNCkLg4ZA8yMbrr5QxI===".split("===");

        for (String s : split) {
            String decryptString1 = decrypt.getDecryptString(s);
            System.out.println(decryptString1);
        }

    }


}
