package com.ruoyi.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.UranusPositionMapper;
import com.ruoyi.domain.UranusPosition;
import com.ruoyi.service.IUranusPositionService;

/**
 * 持仓Service业务层处理
 * 
 * @author uranus
 * @date 2023-10-25
 */
@Service
public class UranusPositionServiceImpl implements IUranusPositionService 
{
    @Autowired
    private UranusPositionMapper uranusPositionMapper;

    /**
     * 查询持仓
     * 
     * @param positionId 持仓主键
     * @return 持仓
     */
    @Override
    public UranusPosition selectUranusPositionByPositionId(Long positionId)
    {
        return uranusPositionMapper.selectUranusPositionByPositionId(positionId);
    }

    /**
     * 查询持仓列表
     * 
     * @param uranusPosition 持仓
     * @return 持仓
     */
    @Override
    public List<UranusPosition> selectUranusPositionList(UranusPosition uranusPosition)
    {
        return uranusPositionMapper.selectUranusPositionList(uranusPosition);
    }

    /**
     * 新增持仓
     * 
     * @param uranusPosition 持仓
     * @return 结果
     */
    @Override
    public int insertUranusPosition(UranusPosition uranusPosition)
    {
        return uranusPositionMapper.insertUranusPosition(uranusPosition);
    }

    /**
     * 修改持仓
     * 
     * @param uranusPosition 持仓
     * @return 结果
     */
    @Override
    public int updateUranusPosition(UranusPosition uranusPosition)
    {
        return uranusPositionMapper.updateUranusPosition(uranusPosition);
    }

    /**
     * 批量删除持仓
     * 
     * @param positionIds 需要删除的持仓主键
     * @return 结果
     */
    @Override
    public int deleteUranusPositionByPositionIds(Long[] positionIds)
    {
        return uranusPositionMapper.deleteUranusPositionByPositionIds(positionIds);
    }

    /**
     * 删除持仓信息
     * 
     * @param positionId 持仓主键
     * @return 结果
     */
    @Override
    public int deleteUranusPositionByPositionId(Long positionId)
    {
        return uranusPositionMapper.deleteUranusPositionByPositionId(positionId);
    }

    @Override
    public List<UranusPosition> getUranusPositionPercent(List<UranusPosition> list) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        BigDecimal totalNum = BigDecimal.valueOf(1);
        for (UranusPosition position : list) {
            if (position.getPositionName().equals("total")) {
                totalNum = position.getPositionNetWorth();
            }
        }
        for (UranusPosition up : list) {
            if (up.getPositionName().equals("total")) {
                up.setPositionPercent("100%");
            } else {
                BigDecimal percentage = up.getPositionNetWorth().divide(totalNum, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                up.setPositionPercent(decimalFormat.format(percentage) + "%");
            }
        }
        return list;
    }
    @Override
    public List<UranusPosition> getUranusRunningPercent(List<UranusPosition> list){
        return list;
    }
}
