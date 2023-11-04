package com.ruoyi.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import com.ruoyi.domain.UranusPosition;
import com.ruoyi.mapper.UranusPositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.UranusTradeCryptoMapper;
import com.ruoyi.domain.UranusTradeCrypto;
import com.ruoyi.service.IUranusTradeCryptoService;

/**
 * 虚拟货币交易Service业务层处理
 *
 * @author uranus
 * @date 2023-10-28
 */
@Service
public class UranusTradeCryptoServiceImpl implements IUranusTradeCryptoService {
    @Autowired
    private UranusTradeCryptoMapper uranusTradeCryptoMapper;
    @Autowired
    private UranusPositionMapper uranusPositionMapper;

    /**
     * 查询虚拟货币交易
     *
     * @param tradeCryptoId 虚拟货币交易主键
     * @return 虚拟货币交易
     */
    @Override
    public UranusTradeCrypto selectUranusTradeCryptoByTradeCryptoId(Long tradeCryptoId) {
        return uranusTradeCryptoMapper.selectUranusTradeCryptoByTradeCryptoId(tradeCryptoId);
    }

    /**
     * 查询虚拟货币交易列表
     *
     * @param uranusTradeCrypto 虚拟货币交易
     * @return 虚拟货币交易
     */
    @Override
    public List<UranusTradeCrypto> selectUranusTradeCryptoList(UranusTradeCrypto uranusTradeCrypto) {
        return uranusTradeCryptoMapper.selectUranusTradeCryptoList(uranusTradeCrypto);
    }

    /**
     * 新增虚拟货币交易
     *
     * @param uranusTradeCrypto 虚拟货币交易
     * @return 结果
     */
    @Override
    public int insertUranusTradeCrypto(UranusTradeCrypto uranusTradeCrypto) {
        return uranusTradeCryptoMapper.insertUranusTradeCrypto(uranusTradeCrypto);
    }

    /**
     * 修改虚拟货币交易
     *
     * @param uranusTradeCrypto 虚拟货币交易
     * @return 结果
     */
    @Override
    public int updateUranusTradeCrypto(UranusTradeCrypto uranusTradeCrypto) {
        return uranusTradeCryptoMapper.updateUranusTradeCrypto(uranusTradeCrypto);
    }

    /**
     * 批量删除虚拟货币交易
     *
     * @param tradeCryptoIds 需要删除的虚拟货币交易主键
     * @return 结果
     */
    @Override
    public int deleteUranusTradeCryptoByTradeCryptoIds(Long[] tradeCryptoIds) {
        return uranusTradeCryptoMapper.deleteUranusTradeCryptoByTradeCryptoIds(tradeCryptoIds);
    }

    /**
     * 删除虚拟货币交易信息
     *
     * @param tradeCryptoId 虚拟货币交易主键
     * @return 结果
     */
    @Override
    public int deleteUranusTradeCryptoByTradeCryptoId(Long tradeCryptoId) {
        return uranusTradeCryptoMapper.deleteUranusTradeCryptoByTradeCryptoId(tradeCryptoId);
    }

    @Override
    public String generateReviewId() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String currentTime = dateTime.format(formatter);
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        String tradeNum = "crypto-" + currentTime + "-" + sb;
        return tradeNum;
    }

    @Override
    public UranusTradeCrypto cryptoClosePosition(UranusTradeCrypto uranusTradeCrypto) {
        UranusPosition cryptoPosition = uranusPositionMapper.selectUranusPositionByName("crypto");
        UranusPosition totalPosition = uranusPositionMapper.selectUranusPositionByName("crypto");
        //判断盈亏
        if (uranusTradeCrypto.getWinorlose().equals("gain")) {
            cryptoPosition.setPositionNetWorth(cryptoPosition.getPositionNetWorth().add(uranusTradeCrypto.getBalanceChanges()));
            cryptoPosition.setPositionNetWorth(totalPosition.getPositionNetWorth().add(uranusTradeCrypto.getBalanceChanges()));
            uranusPositionMapper.updateUranusPosition(cryptoPosition);
        } else if (uranusTradeCrypto.getWinorlose().equals("lose")) {

        } else if (uranusTradeCrypto.getWinorlose().equals("burst")) {


        }
        return uranusTradeCrypto;
    }
}
