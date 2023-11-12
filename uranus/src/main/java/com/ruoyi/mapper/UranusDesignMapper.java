package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.UranusDesign;

/**
 * 需求Mapper接口
 * 
 * @author uranus
 * @date 2023-09-24
 */
public interface UranusDesignMapper 
{
    /**
     * 查询需求
     * 
     * @param designId 需求主键
     * @return 需求
     */
    public UranusDesign selectUranusDesignByDesignId(Long designId);

    /**
     * 查询需求列表
     * 
     * @param uranusDesign 需求
     * @return 需求集合
     */
    public List<UranusDesign> selectUranusDesignList(UranusDesign uranusDesign);

    /**
     * 新增需求
     * 
     * @param uranusDesign 需求
     * @return 结果
     */
    public int insertUranusDesign(UranusDesign uranusDesign);

    /**
     * 修改需求
     * 
     * @param uranusDesign 需求
     * @return 结果
     */
    public int updateUranusDesign(UranusDesign uranusDesign);

    /**
     * 删除需求
     * 
     * @param designId 需求主键
     * @return 结果
     */
    public int deleteUranusDesignByDesignId(Long designId);

    /**
     * 批量删除需求
     * 
     * @param designIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUranusDesignByDesignIds(Long[] designIds);
}
