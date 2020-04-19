package com.fzu.teamwork.model;

import java.io.Serializable;

/**
 * account_data
 * @author 
 */
public class AccountData implements Serializable {
    private Integer id;

    private Integer level;

    private Integer experienceValue;

    private Integer score;

    private Integer focusNum;

    private Integer questionNum;

    private Integer responseNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperienceValue() {
        return experienceValue;
    }

    public void setExperienceValue(Integer experienceValue) {
        this.experienceValue = experienceValue;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getFocusNum() {
        return focusNum;
    }

    public void setFocusNum(Integer focusNum) {
        this.focusNum = focusNum;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getResponseNum() {
        return responseNum;
    }

    public void setResponseNum(Integer responseNum) {
        this.responseNum = responseNum;
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
        AccountData other = (AccountData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getExperienceValue() == null ? other.getExperienceValue() == null : this.getExperienceValue().equals(other.getExperienceValue()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getFocusNum() == null ? other.getFocusNum() == null : this.getFocusNum().equals(other.getFocusNum()))
            && (this.getQuestionNum() == null ? other.getQuestionNum() == null : this.getQuestionNum().equals(other.getQuestionNum()))
            && (this.getResponseNum() == null ? other.getResponseNum() == null : this.getResponseNum().equals(other.getResponseNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getExperienceValue() == null) ? 0 : getExperienceValue().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getFocusNum() == null) ? 0 : getFocusNum().hashCode());
        result = prime * result + ((getQuestionNum() == null) ? 0 : getQuestionNum().hashCode());
        result = prime * result + ((getResponseNum() == null) ? 0 : getResponseNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", level=").append(level);
        sb.append(", experienceValue=").append(experienceValue);
        sb.append(", score=").append(score);
        sb.append(", focusNum=").append(focusNum);
        sb.append(", questionNum=").append(questionNum);
        sb.append(", responseNum=").append(responseNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}