package com.ruoyi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.UranusDemandsMapper;
import com.ruoyi.domain.UranusDemands;
import com.ruoyi.service.IUranusDemandsService;

/**
 * 需求Service业务层处理
 * 
 * @author uranus
 * @date 2023-09-10
 */
@Service
public class UranusDemandsServiceImpl implements IUranusDemandsService 
{
    @Autowired
    private UranusDemandsMapper uranusDemandsMapper;

    /**
     * 查询需求
     * 
     * @param demandsId 需求主键
     * @return 需求
     */
    @Override
    public UranusDemands selectUranusDemandsByDemandsId(Long demandsId)
    {
        return uranusDemandsMapper.selectUranusDemandsByDemandsId(demandsId);
    }

    /**
     * 查询需求列表
     * 
     * @param uranusDemands 需求
     * @return 需求
     */
    @Override
    public List<UranusDemands> selectUranusDemandsList(UranusDemands uranusDemands)
    {
        return uranusDemandsMapper.selectUranusDemandsList(uranusDemands);
    }

    /**
     * 新增需求
     * 
     * @param uranusDemands 需求
     * @return 结果
     */
    @Override
    public int insertUranusDemands(UranusDemands uranusDemands)
    {
        uranusDemands.setCreateTime(DateUtils.getNowDate());
        return uranusDemandsMapper.insertUranusDemands(uranusDemands);
    }

    /**
     * 修改需求
     * 
     * @param uranusDemands 需求
     * @return 结果
     */
    @Override
    public int updateUranusDemands(UranusDemands uranusDemands)
    {
        return uranusDemandsMapper.updateUranusDemands(uranusDemands);
    }

    /**
     * 批量删除需求
     * 
     * @param demandsIds 需要删除的需求主键
     * @return 结果
     */
    @Override
    public int deleteUranusDemandsByDemandsIds(Long[] demandsIds)
    {
        return uranusDemandsMapper.deleteUranusDemandsByDemandsIds(demandsIds);
    }

    /**
     * 删除需求信息
     * 
     * @param demandsId 需求主键
     * @return 结果
     */
    @Override
    public int deleteUranusDemandsByDemandsId(Long demandsId)
    {
        return uranusDemandsMapper.deleteUranusDemandsByDemandsId(demandsId);
    }
}
