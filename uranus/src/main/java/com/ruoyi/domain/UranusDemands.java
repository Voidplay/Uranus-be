package com.ruoyi.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 需求对象 uranus_demands
 * 
 * @author uranus
 * @date 2023-09-10
 */
public class UranusDemands extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long demandsId;

    /** 需求名称 */
    @Excel(name = "需求名称")
    private String demandsName;

    /** 需求内容 */
    @Excel(name = "需求内容")
    private String demandsContents;

    /** 解释需求来源，可以是书籍、视频等 */
    @Excel(name = "解释需求来源，可以是书籍、视频等")
    private String demandsSource;

    /** 需求紧急程度，划分三个档次 */
    @Excel(name = "需求紧急程度，划分三个档次")
    private String demandsUrgency;

    /** 预计完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planTime;

    /** 实际完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeTime;

    public void setDemandsId(Long demandsId) 
    {
        this.demandsId = demandsId;
    }

    public Long getDemandsId() 
    {
        return demandsId;
    }
    public void setDemandsName(String demandsName) 
    {
        this.demandsName = demandsName;
    }

    public String getDemandsName() 
    {
        return demandsName;
    }
    public void setDemandsContents(String demandsContents) 
    {
        this.demandsContents = demandsContents;
    }

    public String getDemandsContents() 
    {
        return demandsContents;
    }
    public void setDemandsSource(String demandsSource) 
    {
        this.demandsSource = demandsSource;
    }

    public String getDemandsSource() 
    {
        return demandsSource;
    }
    public void setDemandsUrgency(String demandsUrgency) 
    {
        this.demandsUrgency = demandsUrgency;
    }

    public String getDemandsUrgency() 
    {
        return demandsUrgency;
    }
    public void setPlanTime(Date planTime) 
    {
        this.planTime = planTime;
    }

    public Date getPlanTime() 
    {
        return planTime;
    }
    public void setCompleteTime(Date completeTime) 
    {
        this.completeTime = completeTime;
    }

    public Date getCompleteTime() 
    {
        return completeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("demandsId", getDemandsId())
            .append("demandsName", getDemandsName())
            .append("demandsContents", getDemandsContents())
            .append("demandsSource", getDemandsSource())
            .append("demandsUrgency", getDemandsUrgency())
            .append("createTime", getCreateTime())
            .append("planTime", getPlanTime())
            .append("completeTime", getCompleteTime())
            .toString();
    }
}
