package com.ruoyi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.UranusDesignMapper;
import com.ruoyi.domain.UranusDesign;
import com.ruoyi.service.IUranusDesignService;

/**
 * 需求Service业务层处理
 * 
 * @author uranus
 * @date 2023-09-24
 */
@Service
public class UranusDesignServiceImpl implements IUranusDesignService 
{
    @Autowired
    private UranusDesignMapper uranusDesignMapper;

    /**
     * 查询需求
     * 
     * @param designId 需求主键
     * @return 需求
     */
    @Override
    public UranusDesign selectUranusDesignByDesignId(Long designId)
    {
        return uranusDesignMapper.selectUranusDesignByDesignId(designId);
    }

    /**
     * 查询需求列表
     * 
     * @param uranusDesign 需求
     * @return 需求
     */
    @Override
    public List<UranusDesign> selectUranusDesignList(UranusDesign uranusDesign)
    {
        return uranusDesignMapper.selectUranusDesignList(uranusDesign);
    }

    /**
     * 新增需求
     * 
     * @param uranusDesign 需求
     * @return 结果
     */
    @Override
    public int insertUranusDesign(UranusDesign uranusDesign)
    {
        uranusDesign.setCreateTime(DateUtils.getNowDate());
        return uranusDesignMapper.insertUranusDesign(uranusDesign);
    }

    /**
     * 修改需求
     * 
     * @param uranusDesign 需求
     * @return 结果
     */
    @Override
    public int updateUranusDesign(UranusDesign uranusDesign)
    {
        return uranusDesignMapper.updateUranusDesign(uranusDesign);
    }

    /**
     * 批量删除需求
     * 
     * @param designIds 需要删除的需求主键
     * @return 结果
     */
    @Override
    public int deleteUranusDesignByDesignIds(Long[] designIds)
    {
        return uranusDesignMapper.deleteUranusDesignByDesignIds(designIds);
    }

    /**
     * 删除需求信息
     * 
     * @param designId 需求主键
     * @return 结果
     */
    @Override
    public int deleteUranusDesignByDesignId(Long designId)
    {
        return uranusDesignMapper.deleteUranusDesignByDesignId(designId);
    }
}
