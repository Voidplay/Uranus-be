package com.ruoyi.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 持仓对象 uranus_position
 *
 * @author uranus
 * @date 2023-11-04
 */
public class UranusPosition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long positionId;

    /** 标的名称 */
    @Excel(name = "标的名称")
    private String positionName;

    /** 持仓净值 */
    @Excel(name = "持仓净值")
    private BigDecimal positionNetWorth;

    /** 前端展示名称 */
    @Excel(name = "前端展示名称")
    private String positionLabel;

    /** 标的占总仓位百分比 */
    @Excel(name = "标的占总仓位百分比")
    private String positionPercent;

    public void setPositionId(Long positionId)
    {
        this.positionId = positionId;
    }

    public Long getPositionId()
    {
        return positionId;
    }
    public void setPositionName(String positionName)
    {
        this.positionName = positionName;
    }

    public String getPositionName()
    {
        return positionName;
    }
    public void setPositionNetWorth(BigDecimal positionNetWorth)
    {
        this.positionNetWorth = positionNetWorth;
    }

    public BigDecimal getPositionNetWorth()
    {
        return positionNetWorth;
    }
    public void setPositionLabel(String positionLabel)
    {
        this.positionLabel = positionLabel;
    }

    public String getPositionLabel()
    {
        return positionLabel;
    }
    public void setPositionPercent(String positionPercent)
    {
        this.positionPercent = positionPercent;
    }

    public String getPositionPercent()
    {
        return positionPercent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("positionId", getPositionId())
                .append("positionName", getPositionName())
                .append("positionNetWorth", getPositionNetWorth())
                .append("positionLabel", getPositionLabel())
                .append("positionPercent", getPositionPercent())
                .toString();
    }
}
