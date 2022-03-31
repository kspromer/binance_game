package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.binancegame.dto.AccountCaptchaDTO;
import io.renren.modules.binancegame.vo.AccountCaptchaVO;
import io.renren.modules.binancegame.entity.AccountCaptchaEntity;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Collection;


/**
 * 系统验证码
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-20 18:05:24
 */
public interface AccountCaptchaService extends IService<AccountCaptchaEntity> {

    /**
     * 分页查询
     * @param accountCaptcha
     * @return
     */
    PageUtils queryPage(AccountCaptchaDTO accountCaptcha);
    /**
     * 根据id查询
     * @param uuid
     * @return
     */
    AccountCaptchaVO getById(String uuid);
    /**
     * 保存
     * @param accountCaptcha
     * @return
     */
    boolean save(AccountCaptchaDTO accountCaptcha);
    /**
     * 根据id修改
     * @param accountCaptcha
     * @return
     */
    boolean updateById(AccountCaptchaDTO accountCaptcha);
    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 根据id批量删除
     * @param ids
     * @return
     */
    @Override
    boolean removeByIds(Collection<? extends Serializable> ids);
    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}

