package io.renren.modules.binancegame.service.impl;

import com.google.code.kaptcha.Producer;
import io.renren.common.exception.RRException;
import io.renren.common.utils.DateUtils;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.sys.entity.SysCaptchaEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.AccountCaptchaDao;
import io.renren.modules.binancegame.entity.AccountCaptchaEntity;
import io.renren.modules.binancegame.dto.AccountCaptchaDTO;
import io.renren.modules.binancegame.vo.AccountCaptchaVO;
import io.renren.modules.binancegame.service.AccountCaptchaService;
import io.renren.modules.binancegame.conver.AccountCaptchaConver;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Service("accountCaptchaService")
@BinanceGame
public class AccountCaptchaServiceImpl extends ServiceImpl<AccountCaptchaDao, AccountCaptchaEntity> implements AccountCaptchaService {

    @Override
    public PageUtils<AccountCaptchaVO> queryPage(AccountCaptchaDTO accountCaptcha) {
        IPage<AccountCaptchaEntity> page = baseMapper.selectPage(
                new Query<AccountCaptchaEntity>(accountCaptcha).getPage(),
                new QueryWrapper<AccountCaptchaEntity>()
        );

        return PageUtils.<AccountCaptchaVO>page(page).setList(AccountCaptchaConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public AccountCaptchaVO getById(String uuid) {
        return AccountCaptchaConver.MAPPER.conver(baseMapper.selectById(uuid));
    }

    @Override
    public boolean save(AccountCaptchaDTO accountCaptcha) {
        AccountCaptchaEntity accountCaptchaEntity = AccountCaptchaConver.MAPPER.converDTO(accountCaptcha);
        return this.save(accountCaptchaEntity);
    }

    @Override
    public boolean updateById(AccountCaptchaDTO accountCaptcha) {
        AccountCaptchaEntity accountCaptchaEntity = AccountCaptchaConver.MAPPER.converDTO(accountCaptcha);
        return this.updateById(accountCaptchaEntity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        return super.removeByIds(ids);
    }
    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new RRException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();

        AccountCaptchaEntity captchaEntity = new AccountCaptchaEntity();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        //5分钟后过期
        captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
        baseMapper.insert(captchaEntity);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        AccountCaptchaEntity captchaEntity = this.getOne(new QueryWrapper<AccountCaptchaEntity>().lambda()
                .eq(AccountCaptchaEntity::getUuid, uuid));
        if(captchaEntity == null){
            return false;
        }

        if(captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()){
            return true;
        }

        return false;
    }
}
