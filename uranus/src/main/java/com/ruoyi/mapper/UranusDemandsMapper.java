package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.UranusDemands;

/**
 * 需求Mapper接口
 * 
 * @author uranus
 * @date 2023-09-10
 */
public interface UranusDemandsMapper 
{
    /**
     * 查询需求
     * 
     * @param demandsId 需求主键
     * @return 需求
     */
    public UranusDemands selectUranusDemandsByDemandsId(Long demandsId);

    /**
     * 查询需求列表
     * 
     * @param uranusDemands 需求
     * @return 需求集合
     */
    public List<UranusDemands> selectUranusDemandsList(UranusDemands uranusDemands);

    /**
     * 新增需求
     * 
     * @param uranusDemands 需求
     * @return 结果
     */
    public int insertUranusDemands(UranusDemands uranusDemands);

    /**
     * 修改需求
     * 
     * @param uranusDemands 需求
     * @return 结果
     */
    public int updateUranusDemands(UranusDemands uranusDemands);

    /**
     * 删除需求
     * 
     * @param demandsId 需求主键
     * @return 结果
     */
    public int deleteUranusDemandsByDemandsId(Long demandsId);

    /**
     * 批量删除需求
     * 
     * @param demandsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUranusDemandsByDemandsIds(Long[] demandsIds);
}
