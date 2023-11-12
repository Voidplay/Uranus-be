package com.ruoyi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.UranusTradeHistoryMapper;
import com.ruoyi.domain.UranusTradeHistory;
import com.ruoyi.service.IUranusTradeHistoryService;

/**
 * 交易历史Service业务层处理
 * 
 * @author uranus
 * @date 2023-11-12
 */
@Service
public class UranusTradeHistoryServiceImpl implements IUranusTradeHistoryService 
{
    @Autowired
    private UranusTradeHistoryMapper uranusTradeHistoryMapper;

    /**
     * 查询交易历史
     * 
     * @param tradeHistoryId 交易历史主键
     * @return 交易历史
     */
    @Override
    public UranusTradeHistory selectUranusTradeHistoryByTradeHistoryId(Long tradeHistoryId)
    {
        return uranusTradeHistoryMapper.selectUranusTradeHistoryByTradeHistoryId(tradeHistoryId);
    }

    /**
     * 查询交易历史列表
     * 
     * @param uranusTradeHistory 交易历史
     * @return 交易历史
     */
    @Override
    public List<UranusTradeHistory> selectUranusTradeHistoryList(UranusTradeHistory uranusTradeHistory)
    {
        return uranusTradeHistoryMapper.selectUranusTradeHistoryList(uranusTradeHistory);
    }

    /**
     * 新增交易历史
     * 
     * @param uranusTradeHistory 交易历史
     * @return 结果
     */
    @Override
    public int insertUranusTradeHistory(UranusTradeHistory uranusTradeHistory)
    {
        return uranusTradeHistoryMapper.insertUranusTradeHistory(uranusTradeHistory);
    }

    /**
     * 修改交易历史
     * 
     * @param uranusTradeHistory 交易历史
     * @return 结果
     */
    @Override
    public int updateUranusTradeHistory(UranusTradeHistory uranusTradeHistory)
    {
        return uranusTradeHistoryMapper.updateUranusTradeHistory(uranusTradeHistory);
    }

    /**
     * 批量删除交易历史
     * 
     * @param tradeHistoryIds 需要删除的交易历史主键
     * @return 结果
     */
    @Override
    public int deleteUranusTradeHistoryByTradeHistoryIds(Long[] tradeHistoryIds)
    {
        return uranusTradeHistoryMapper.deleteUranusTradeHistoryByTradeHistoryIds(tradeHistoryIds);
    }

    /**
     * 删除交易历史信息
     * 
     * @param tradeHistoryId 交易历史主键
     * @return 结果
     */
    @Override
    public int deleteUranusTradeHistoryByTradeHistoryId(Long tradeHistoryId)
    {
        return uranusTradeHistoryMapper.deleteUranusTradeHistoryByTradeHistoryId(tradeHistoryId);
    }
}
