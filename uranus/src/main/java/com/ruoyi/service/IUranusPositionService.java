package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.UranusPosition;

/**
 * 持仓Service接口
 * 
 * @author uranus
 * @date 2023-10-25
 */
public interface IUranusPositionService 
{
    /**
     * 查询持仓
     * 
     * @param positionId 持仓主键
     * @return 持仓
     */
    public UranusPosition selectUranusPositionByPositionId(Long positionId);

    /**
     * 查询持仓列表
     * 
     * @param uranusPosition 持仓
     * @return 持仓集合
     */
    public List<UranusPosition> selectUranusPositionList(UranusPosition uranusPosition);

    /**
     * 新增持仓
     * 
     * @param uranusPosition 持仓
     * @return 结果
     */
    public int insertUranusPosition(UranusPosition uranusPosition);

    /**
     * 修改持仓
     * 
     * @param uranusPosition 持仓
     * @return 结果
     */
    public int updateUranusPosition(UranusPosition uranusPosition);

    /**
     * 批量删除持仓
     * 
     * @param positionIds 需要删除的持仓主键集合
     * @return 结果
     */
    public int deleteUranusPositionByPositionIds(Long[] positionIds);

    /**
     * 删除持仓信息
     * 
     * @param positionId 持仓主键
     * @return 结果
     */
    public int deleteUranusPositionByPositionId(Long positionId);
}
