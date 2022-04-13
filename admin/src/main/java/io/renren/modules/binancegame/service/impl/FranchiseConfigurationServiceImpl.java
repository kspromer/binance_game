package io.renren.modules.binancegame.service.impl;

import io.renren.datasources.annotation.BinanceGame;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.FranchiseConfigurationDao;
import io.renren.modules.binancegame.entity.FranchiseConfigurationEntity;
import io.renren.modules.binancegame.dto.FranchiseConfigurationDTO;
import io.renren.modules.binancegame.vo.FranchiseConfigurationVO;
import io.renren.modules.binancegame.service.FranchiseConfigurationService;
import io.renren.modules.binancegame.conver.FranchiseConfigurationConver;

import java.io.Serializable;
import java.util.Collection;


@Service("franchiseConfigurationService")
@BinanceGame
public class FranchiseConfigurationServiceImpl extends ServiceImpl<FranchiseConfigurationDao, FranchiseConfigurationEntity> implements FranchiseConfigurationService {

    @Override
    public PageUtils<FranchiseConfigurationVO> queryPage(FranchiseConfigurationDTO franchiseConfiguration) {
        IPage<FranchiseConfigurationEntity> page = baseMapper.selectPage(
                new Query<FranchiseConfigurationEntity>(franchiseConfiguration).getPage(),
                new QueryWrapper<FranchiseConfigurationEntity>()
        );

        return PageUtils.<FranchiseConfigurationVO>page(page).setList(FranchiseConfigurationConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public FranchiseConfigurationVO getById(Long id) {
        return FranchiseConfigurationConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(FranchiseConfigurationDTO franchiseConfiguration) {
        FranchiseConfigurationEntity franchiseConfigurationEntity = FranchiseConfigurationConver.MAPPER.converDTO(franchiseConfiguration);
        return this.save(franchiseConfigurationEntity);
    }

    @Override
    public boolean updateById(FranchiseConfigurationDTO franchiseConfiguration) {
        FranchiseConfigurationEntity franchiseConfigurationEntity = FranchiseConfigurationConver.MAPPER.converDTO(franchiseConfiguration);
        return this.updateById(franchiseConfigurationEntity);
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
