package com.ruoyi.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public BigDecimal generateStopLossPrice(UranusTradeCrypto uranusTradeCrypto) {
        BigDecimal total = uranusPositionMapper.selectUranusPositionByName("total").getPositionNetWorth();
        Long leverage = uranusTradeCrypto.getLeverage();
        BigDecimal margin = uranusTradeCrypto.getMargin();
        BigDecimal entryprice = null;
        if (uranusTradeCrypto.getEntryPrice() == null){
            entryprice = uranusTradeCrypto.getLlimitPrice();
        }else {
            entryprice = uranusTradeCrypto.getEntryPrice();
        }

        String direction = uranusTradeCrypto.getDirection();
        String tradeType = uranusTradeCrypto.getTradeType();
        BigDecimal max_lose_num = null;
        //根据tradeType来判断最大单次可承受的亏损
        if (tradeType.equals("regular")){
            max_lose_num = total.multiply(BigDecimal.valueOf(0.05));
        } else if (tradeType.equals("fast-fail")) {
            max_lose_num = total.multiply(BigDecimal.valueOf(0.02));
        } else if (tradeType.equals("yolo")) {
            max_lose_num = total.multiply(BigDecimal.valueOf(0.20));
        }
        BigDecimal position = margin.multiply(BigDecimal.valueOf(leverage));
        BigDecimal lose_percet = max_lose_num.divide(position,3, RoundingMode.HALF_UP);
        BigDecimal stop_price = null;
        if (lose_percet.compareTo(BigDecimal.valueOf(1)) > 0) {
            return null;
        }
        if (direction.equals("long")){
            stop_price = entryprice.subtract(entryprice.multiply(lose_percet));
            return stop_price;
        } else if (direction.equals("short")) {
            stop_price = entryprice.add(entryprice.multiply(lose_percet));
           return stop_price;
        }
        return stop_price;
    }

    @Override
    public UranusTradeCrypto cryptoClosePosition(UranusTradeCrypto uranusTradeCrypto) {
        UranusPosition cryptoPosition = uranusPositionMapper.selectUranusPositionByName("crypto");
        UranusPosition totalPosition = uranusPositionMapper.selectUranusPositionByName("total");
        //判断盈亏
        if (uranusTradeCrypto.getWinorlose().equals("gain")) {
            cryptoPosition.setPositionNetWorth(cryptoPosition.getPositionNetWorth().add(uranusTradeCrypto.getBalanceChanges()));
            totalPosition.setPositionNetWorth(totalPosition.getPositionNetWorth().add(uranusTradeCrypto.getBalanceChanges()));
            uranusPositionMapper.updateUranusPosition(cryptoPosition);
            uranusPositionMapper.updateUranusPosition(totalPosition);
        } else if (uranusTradeCrypto.getWinorlose().equals("lose")) {
            cryptoPosition.setPositionNetWorth(cryptoPosition.getPositionNetWorth().subtract(uranusTradeCrypto.getBalanceChanges()));
            totalPosition.setPositionNetWorth(totalPosition.getPositionNetWorth().subtract(uranusTradeCrypto.getBalanceChanges()));
            uranusPositionMapper.updateUranusPosition(cryptoPosition);
            uranusPositionMapper.updateUranusPosition(totalPosition);
        } else if (uranusTradeCrypto.getWinorlose().equals("burst")) {
            cryptoPosition.setPositionNetWorth(cryptoPosition.getPositionNetWorth().subtract(uranusTradeCrypto.getPosition()));
            totalPosition.setPositionNetWorth(totalPosition.getPositionNetWorth().subtract(uranusTradeCrypto.getPosition()));
            uranusPositionMapper.updateUranusPosition(cryptoPosition);
            uranusPositionMapper.updateUranusPosition(totalPosition);
        }
        return uranusTradeCrypto;
    }
}
