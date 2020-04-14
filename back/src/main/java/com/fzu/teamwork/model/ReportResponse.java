package com.fzu.teamwork.model;

import java.io.Serializable;

/**
 * report_response
 * @author 
 */
public class ReportResponse implements Serializable {
    private Integer id;

    private Integer reportorId;

    private Integer responseId;

    private Integer flag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportorId() {
        return reportorId;
    }

    public void setReportorId(Integer reportorId) {
        this.reportorId = reportorId;
    }

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
        ReportResponse other = (ReportResponse) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getReportorId() == null ? other.getReportorId() == null : this.getReportorId().equals(other.getReportorId()))
            && (this.getResponseId() == null ? other.getResponseId() == null : this.getResponseId().equals(other.getResponseId()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getReportorId() == null) ? 0 : getReportorId().hashCode());
        result = prime * result + ((getResponseId() == null) ? 0 : getResponseId().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reportorId=").append(reportorId);
        sb.append(", responseId=").append(responseId);
        sb.append(", flag=").append(flag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}