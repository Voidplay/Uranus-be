package com.ruoyi.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 交易历史对象 uranus_trade_history
 *
 * @author uranus
 * @date 2023-11-12
 */
public class UranusTradeHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long tradeHistoryId;

    /** 标的名称 */
    @Excel(name = "标的名称")
    private String tradeSubjectName;

    /** reviewId */
    @Excel(name = "reviewId")
    private String tradeReviewid;

    /** 胜负标识 */
    @Excel(name = "胜负标识")
    private String tradeWinorlose;

    /** 开仓日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开仓日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryDate;

    /** 平仓日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "平仓日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date liquidateTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal balanceChanges;

    public void setTradeHistoryId(Long tradeHistoryId)
    {
        this.tradeHistoryId = tradeHistoryId;
    }

    public Long getTradeHistoryId()
    {
        return tradeHistoryId;
    }
    public void setTradeSubjectName(String tradeSubjectName)
    {
        this.tradeSubjectName = tradeSubjectName;
    }

    public String getTradeSubjectName()
    {
        return tradeSubjectName;
    }
    public void setTradeReviewid(String tradeReviewid)
    {
        this.tradeReviewid = tradeReviewid;
    }

    public String getTradeReviewid()
    {
        return tradeReviewid;
    }
    public void setTradeWinorlose(String tradeWinorlose)
    {
        this.tradeWinorlose = tradeWinorlose;
    }

    public String getTradeWinorlose()
    {
        return tradeWinorlose;
    }
    public void setEntryDate(Date entryDate)
    {
        this.entryDate = entryDate;
    }

    public Date getEntryDate()
    {
        return entryDate;
    }
    public void setLiquidateTime(Date liquidateTime)
    {
        this.liquidateTime = liquidateTime;
    }

    public Date getLiquidateTime()
    {
        return liquidateTime;
    }
    public void setBalanceChanges(BigDecimal balanceChanges)
    {
        this.balanceChanges = balanceChanges;
    }

    public BigDecimal getBalanceChanges()
    {
        return balanceChanges;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("tradeHistoryId", getTradeHistoryId())
                .append("tradeSubjectName", getTradeSubjectName())
                .append("tradeReviewid", getTradeReviewid())
                .append("tradeWinorlose", getTradeWinorlose())
                .append("entryDate", getEntryDate())
                .append("liquidateTime", getLiquidateTime())
                .append("balanceChanges", getBalanceChanges())
                .toString();
    }
}
