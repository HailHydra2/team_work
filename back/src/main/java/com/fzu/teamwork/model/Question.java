package com.fzu.teamwork.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * question
 * @author 
 */
@Data
public class Question implements Serializable {
    private Integer id;

    private Integer authorId;

    private Integer responseNum = 0;

    private Integer reportNum = 0;

    private Date createTime;

    private Integer contentId;

    private Integer anonymous = 0;

    private Integer kindId = 1;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
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
            && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
            && (this.getResponseNum() == null ? other.getResponseNum() == null : this.getResponseNum().equals(other.getResponseNum()))
            && (this.getReportNum() == null ? other.getReportNum() == null : this.getReportNum().equals(other.getReportNum()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getContentId() == null ? other.getContentId() == null : this.getContentId().equals(other.getContentId()))
            && (this.getAnonymous() == null ? other.getAnonymous() == null : this.getAnonymous().equals(other.getAnonymous()))
            && (this.getKindId() == null ? other.getKindId() == null : this.getKindId().equals(other.getKindId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getResponseNum() == null) ? 0 : getResponseNum().hashCode());
        result = prime * result + ((getReportNum() == null) ? 0 : getReportNum().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getContentId() == null) ? 0 : getContentId().hashCode());
        result = prime * result + ((getAnonymous() == null) ? 0 : getAnonymous().hashCode());
        result = prime * result + ((getKindId() == null) ? 0 : getKindId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", authorId=").append(authorId);
        sb.append(", responseNum=").append(responseNum);
        sb.append(", reportNum=").append(reportNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", contentId=").append(contentId);
        sb.append(", anonymous=").append(anonymous);
        sb.append(", kindId=").append(kindId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}