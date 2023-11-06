package com.ruoyi.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 虚拟货币交易对象 uranus_trade_crypto
 *
 * @author uranus
 * @date 2023-11-06
 */
public class UranusTradeCrypto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long tradeCryptoId;

    /** 代币名称 */
    @Excel(name = "代币名称")
    private String coinName;

    /** 交易类型 */
    @Excel(name = "交易类型")
    private String tradeType;

    /** 杠杆 */
    @Excel(name = "杠杆")
    private Long leverage;

    /** 保证金 */
    @Excel(name = "保证金")
    private BigDecimal margin;

    /** 总仓位 */
    @Excel(name = "总仓位")
    private BigDecimal position;

    /** 是否限价 */
    @Excel(name = "是否限价")
    private String isLimitprice;

    /** 方向 */
    @Excel(name = "方向")
    private String direction;

    /** 入场日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入场日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryDate;

    /** 平仓日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "平仓日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date liquidateDate;

    /** 入场价格 */
    @Excel(name = "入场价格")
    private BigDecimal entryPrice;

    /** 平仓价格 */
    @Excel(name = "平仓价格")
    private BigDecimal liquidatePrice;

    /** 交易费用，含资金费 */
    @Excel(name = "交易费用，含资金费")
    private BigDecimal fees;

    /** 持仓状态 */
    @Excel(name = "持仓状态")
    private String status;

    /** 盈亏 */
    @Excel(name = "盈亏")
    private String winorlose;

    /** 净盈亏 */
    @Excel(name = "净盈亏")
    private BigDecimal balanceChanges;

    /** 交易业务id，供关联笔记软件 */
    @Excel(name = "交易业务id，供关联笔记软件")
    private String reviewId;

    /** 挂单价格 */
    @Excel(name = "挂单价格")
    private BigDecimal llimitPrice;

    /** 挂单日期，精确到分供后续分析 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "挂单日期，精确到分供后续分析", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 止损点，自动计算 */
    @Excel(name = "止损点，自动计算")
    private BigDecimal stopLossPrice;

    public void setTradeCryptoId(Long tradeCryptoId)
    {
        this.tradeCryptoId = tradeCryptoId;
    }

    public Long getTradeCryptoId()
    {
        return tradeCryptoId;
    }
    public void setCoinName(String coinName)
    {
        this.coinName = coinName;
    }

    public String getCoinName()
    {
        return coinName;
    }
    public void setTradeType(String tradeType)
    {
        this.tradeType = tradeType;
    }

    public String getTradeType()
    {
        return tradeType;
    }
    public void setLeverage(Long leverage)
    {
        this.leverage = leverage;
    }

    public Long getLeverage()
    {
        return leverage;
    }
    public void setMargin(BigDecimal margin)
    {
        this.margin = margin;
    }

    public BigDecimal getMargin()
    {
        return margin;
    }
    public void setPosition(BigDecimal position)
    {
        this.position = position;
    }

    public BigDecimal getPosition()
    {
        return position;
    }
    public void setIsLimitprice(String isLimitprice)
    {
        this.isLimitprice = isLimitprice;
    }

    public String getIsLimitprice()
    {
        return isLimitprice;
    }
    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public String getDirection()
    {
        return direction;
    }
    public void setEntryDate(Date entryDate)
    {
        this.entryDate = entryDate;
    }

    public Date getEntryDate()
    {
        return entryDate;
    }
    public void setLiquidateDate(Date liquidateDate)
    {
        this.liquidateDate = liquidateDate;
    }

    public Date getLiquidateDate()
    {
        return liquidateDate;
    }
    public void setEntryPrice(BigDecimal entryPrice)
    {
        this.entryPrice = entryPrice;
    }

    public BigDecimal getEntryPrice()
    {
        return entryPrice;
    }
    public void setLiquidatePrice(BigDecimal liquidatePrice)
    {
        this.liquidatePrice = liquidatePrice;
    }

    public BigDecimal getLiquidatePrice()
    {
        return liquidatePrice;
    }
    public void setFees(BigDecimal fees)
    {
        this.fees = fees;
    }

    public BigDecimal getFees()
    {
        return fees;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setWinorlose(String winorlose)
    {
        this.winorlose = winorlose;
    }

    public String getWinorlose()
    {
        return winorlose;
    }
    public void setBalanceChanges(BigDecimal balanceChanges)
    {
        this.balanceChanges = balanceChanges;
    }

    public BigDecimal getBalanceChanges()
    {
        return balanceChanges;
    }
    public void setReviewId(String reviewId)
    {
        this.reviewId = reviewId;
    }

    public String getReviewId()
    {
        return reviewId;
    }
    public void setLlimitPrice(BigDecimal llimitPrice)
    {
        this.llimitPrice = llimitPrice;
    }

    public BigDecimal getLlimitPrice()
    {
        return llimitPrice;
    }
    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }
    public void setStopLossPrice(BigDecimal stopLossPrice)
    {
        this.stopLossPrice = stopLossPrice;
    }

    public BigDecimal getStopLossPrice()
    {
        return stopLossPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("tradeCryptoId", getTradeCryptoId())
                .append("coinName", getCoinName())
                .append("tradeType", getTradeType())
                .append("leverage", getLeverage())
                .append("margin", getMargin())
                .append("position", getPosition())
                .append("isLimitprice", getIsLimitprice())
                .append("direction", getDirection())
                .append("entryDate", getEntryDate())
                .append("liquidateDate", getLiquidateDate())
                .append("entryPrice", getEntryPrice())
                .append("liquidatePrice", getLiquidatePrice())
                .append("fees", getFees())
                .append("status", getStatus())
                .append("winorlose", getWinorlose())
                .append("balanceChanges", getBalanceChanges())
                .append("reviewId", getReviewId())
                .append("llimitPrice", getLlimitPrice())
                .append("orderDate", getOrderDate())
                .append("stopLossPrice", getStopLossPrice())
                .toString();
    }
}
