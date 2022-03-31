package io.renren.modules.binancegame.service.impl;

import io.renren.datasources.annotation.BinanceGame;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.BetConfigDao;
import io.renren.modules.binancegame.entity.BetConfigEntity;
import io.renren.modules.binancegame.dto.BetConfigDTO;
import io.renren.modules.binancegame.vo.BetConfigVO;
import io.renren.modules.binancegame.service.BetConfigService;
import io.renren.modules.binancegame.conver.BetConfigConver;

import java.io.Serializable;
import java.util.Collection;


@Service("betConfigService")
@BinanceGame
public class BetConfigServiceImpl extends ServiceImpl<BetConfigDao, BetConfigEntity> implements BetConfigService {

    @Override
    public PageUtils<BetConfigVO> queryPage(BetConfigDTO betConfig) {
        IPage<BetConfigEntity> page = baseMapper.selectPage(
                new Query<BetConfigEntity>(betConfig).getPage(),
                new QueryWrapper<BetConfigEntity>()
        );

        return PageUtils.<BetConfigVO>page(page).setList(BetConfigConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public BetConfigVO getById(Integer id) {
        return BetConfigConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(BetConfigDTO betConfig) {
        BetConfigEntity betConfigEntity = BetConfigConver.MAPPER.converDTO(betConfig);
        return this.save(betConfigEntity);
    }

    @Override
    public boolean updateById(BetConfigDTO betConfig) {
        BetConfigEntity betConfigEntity = BetConfigConver.MAPPER.converDTO(betConfig);
        return this.updateById(betConfigEntity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        return super.removeByIds(ids);
    }

}
