package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.UranusTradeCrypto;

/**
 * 虚拟货币交易Mapper接口
 * 
 * @author uranus
 * @date 2023-10-28
 */
public interface UranusTradeCryptoMapper 
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
     * 删除虚拟货币交易
     * 
     * @param tradeCryptoId 虚拟货币交易主键
     * @return 结果
     */
    public int deleteUranusTradeCryptoByTradeCryptoId(Long tradeCryptoId);

    /**
     * 批量删除虚拟货币交易
     * 
     * @param tradeCryptoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUranusTradeCryptoByTradeCryptoIds(Long[] tradeCryptoIds);
}
