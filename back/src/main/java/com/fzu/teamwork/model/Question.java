package com.fzu.teamwork.model;

import java.io.Serializable;
import java.util.Date;

/**
 * question
 * @author 
 */
public class Question implements Serializable {
    private Integer id;

    private Integer autherId;

    private Integer responseNum;

    private Integer reportNum;

    private Date createTime;

    private Integer contentId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAutherId() {
        return autherId;
    }

    public void setAutherId(Integer autherId) {
        this.autherId = autherId;
    }

    public Integer getResponseNum() {
        return responseNum;
    }

    public void setResponseNum(Integer responseNum) {
        this.responseNum = responseNum;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Question other = (Question) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAutherId() == null ? other.getAutherId() == null : this.getAutherId().equals(other.getAutherId()))
            && (this.getResponseNum() == null ? other.getResponseNum() == null : this.getResponseNum().equals(other.getResponseNum()))
            && (this.getReportNum() == null ? other.getReportNum() == null : this.getReportNum().equals(other.getReportNum()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getContentId() == null ? other.getContentId() == null : this.getContentId().equals(other.getContentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAutherId() == null) ? 0 : getAutherId().hashCode());
        result = prime * result + ((getResponseNum() == null) ? 0 : getResponseNum().hashCode());
        result = prime * result + ((getReportNum() == null) ? 0 : getReportNum().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getContentId() == null) ? 0 : getContentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", autherId=").append(autherId);
        sb.append(", responseNum=").append(responseNum);
        sb.append(", reportNum=").append(reportNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", contentId=").append(contentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}