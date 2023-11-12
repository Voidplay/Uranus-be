package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.UranusPosition;

/**
 * 持仓Mapper接口
 * 
 * @author uranus
 * @date 2023-10-25
 */
public interface UranusPositionMapper 
{
    /**
     * 查询持仓
     * 
     * @param positionId 持仓主键
     * @return 持仓
     */
    public UranusPosition selectUranusPositionByPositionId(Long positionId);

    /**
     * 查询持仓by name
     *
     * @param positionName 仓位名称
     * @return 持仓
     */
    public UranusPosition selectUranusPositionByName(String positionName);

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
     * 删除持仓
     * 
     * @param positionId 持仓主键
     * @return 结果
     */
    public int deleteUranusPositionByPositionId(Long positionId);

    /**
     * 批量删除持仓
     * 
     * @param positionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUranusPositionByPositionIds(Long[] positionIds);
}
