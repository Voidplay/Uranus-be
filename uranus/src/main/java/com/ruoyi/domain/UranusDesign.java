package com.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 需求对象 uranus_design
 * 
 * @author uranus
 * @date 2023-09-24
 */
public class UranusDesign extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long designId;

    /** 模块名称 */
    @Excel(name = "模块名称")
    private String designName;

    /** 模块设计内容 */
    @Excel(name = "模块设计内容")
    private String designContents;

    public void setDesignId(Long designId) 
    {
        this.designId = designId;
    }

    public Long getDesignId() 
    {
        return designId;
    }
    public void setDesignName(String designName) 
    {
        this.designName = designName;
    }

    public String getDesignName() 
    {
        return designName;
    }
    public void setDesignContents(String designContents) 
    {
        this.designContents = designContents;
    }

    public String getDesignContents() 
    {
        return designContents;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("designId", getDesignId())
            .append("designName", getDesignName())
            .append("designContents", getDesignContents())
            .append("createTime", getCreateTime())
            .toString();
    }
}
