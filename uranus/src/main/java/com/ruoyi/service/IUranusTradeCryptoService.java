package com.ruoyi.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.domain.UranusTradeCrypto;

/**
 * 虚拟货币交易Service接口
 * 
 * @author uranus
 * @date 2023-10-28
 */
public interface IUranusTradeCryptoService 
{
    /**
     * 查询虚拟货币交易
     * 
     * @param tradeCryptoId 虚拟货币交易主键
     * @return 虚拟货币交易
     */
    public UranusTradeCrypto selectUranusTradeCryptoByTradeCryptoId(Long tradeCryptoId);

    /**
     * 查询虚拟货币交易列表
     * 
     * @param uranusTradeCrypto 虚拟货币交易
     * @return 虚拟货币交易集合
     */
    public List<UranusTradeCrypto> selectUranusTradeCryptoList(UranusTradeCrypto uranusTradeCrypto);

    /**
     * 新增虚拟货币交易
     * 
     * @param uranusTradeCrypto 虚拟货币交易
     * @return 结果
     */
    public int insertUranusTradeCrypto(UranusTradeCrypto uranusTradeCrypto);

    /**
     * 修改虚拟货币交易
     * 
     * @param uranusTradeCrypto 虚拟货币交易
     * @return 结果
     */
    public int updateUranusTradeCrypto(UranusTradeCrypto uranusTradeCrypto);

    /**
     * 批量删除虚拟货币交易
     * 
     * @param tradeCryptoIds 需要删除的虚拟货币交易主键集合
     * @return 结果
     */
    public int deleteUranusTradeCryptoByTradeCryptoIds(Long[] tradeCryptoIds);

    /**
     * 删除虚拟货币交易信息
     * 
     * @param tradeCryptoId 虚拟货币交易主键
     * @return 结果
     */
    public int deleteUranusTradeCryptoByTradeCryptoId(Long tradeCryptoId);
    /**
     * 生成reviewId
     *
     * @param
     * @return 结果
     */
    public String generateReviewId();

    public UranusTradeCrypto cryptoClosePosition(UranusTradeCrypto uranusTradeCrypto);
    public BigDecimal generateStopLossPrice(UranusTradeCrypto uranusTradeCrypto);

    List<UranusTradeCrypto> selectUranusTradeCryptoOpenList(UranusTradeCrypto uranusTradeCrypto);
}
