package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.UranusTradeHistory;

/**
 * 交易历史Mapper接口
 * 
 * @author uranus
 * @date 2023-11-12
 */
public interface UranusTradeHistoryMapper 
{
    /**
     * 查询交易历史
     * 
     * @param tradeHistoryId 交易历史主键
     * @return 交易历史
     */
    public UranusTradeHistory selectUranusTradeHistoryByTradeHistoryId(Long tradeHistoryId);

    /**
     * 查询交易历史列表
     * 
     * @param uranusTradeHistory 交易历史
     * @return 交易历史集合
     */
    public List<UranusTradeHistory> selectUranusTradeHistoryList(UranusTradeHistory uranusTradeHistory);

    /**
     * 新增交易历史
     * 
     * @param uranusTradeHistory 交易历史
     * @return 结果
     */
    public int insertUranusTradeHistory(UranusTradeHistory uranusTradeHistory);

    /**
     * 修改交易历史
     * 
     * @param uranusTradeHistory 交易历史
     * @return 结果
     */
    public int updateUranusTradeHistory(UranusTradeHistory uranusTradeHistory);

    /**
     * 删除交易历史
     * 
     * @param tradeHistoryId 交易历史主键
     * @return 结果
     */
    public int deleteUranusTradeHistoryByTradeHistoryId(Long tradeHistoryId);

    /**
     * 批量删除交易历史
     * 
     * @param tradeHistoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUranusTradeHistoryByTradeHistoryIds(Long[] tradeHistoryIds);
}
