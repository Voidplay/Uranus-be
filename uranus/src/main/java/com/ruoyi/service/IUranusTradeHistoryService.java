package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.UranusTradeHistory;

/**
 * 交易历史Service接口
 * 
 * @author uranus
 * @date 2023-11-12
 */
public interface IUranusTradeHistoryService 
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
     * 批量删除交易历史
     * 
     * @param tradeHistoryIds 需要删除的交易历史主键集合
     * @return 结果
     */
    public int deleteUranusTradeHistoryByTradeHistoryIds(Long[] tradeHistoryIds);

    /**
     * 删除交易历史信息
     * 
     * @param tradeHistoryId 交易历史主键
     * @return 结果
     */
    public int deleteUranusTradeHistoryByTradeHistoryId(Long tradeHistoryId);

}
