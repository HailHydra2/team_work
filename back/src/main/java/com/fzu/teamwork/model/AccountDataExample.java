package com.fzu.teamwork.model;

import java.util.ArrayList;
import java.util.List;

public class AccountDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public AccountDataExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("`level` is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("`level` is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("`level` =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("`level` <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("`level` >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("`level` >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("`level` <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("`level` <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("`level` in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("`level` not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("`level` between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("`level` not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andExperienceValueIsNull() {
            addCriterion("experience_value is null");
            return (Criteria) this;
        }

        public Criteria andExperienceValueIsNotNull() {
            addCriterion("experience_value is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceValueEqualTo(Integer value) {
            addCriterion("experience_value =", value, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andExperienceValueNotEqualTo(Integer value) {
            addCriterion("experience_value <>", value, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andExperienceValueGreaterThan(Integer value) {
            addCriterion("experience_value >", value, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andExperienceValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("experience_value >=", value, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andExperienceValueLessThan(Integer value) {
            addCriterion("experience_value <", value, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andExperienceValueLessThanOrEqualTo(Integer value) {
            addCriterion("experience_value <=", value, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andExperienceValueIn(List<Integer> values) {
            addCriterion("experience_value in", values, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andExperienceValueNotIn(List<Integer> values) {
            addCriterion("experience_value not in", values, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andExperienceValueBetween(Integer value1, Integer value2) {
            addCriterion("experience_value between", value1, value2, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andExperienceValueNotBetween(Integer value1, Integer value2) {
            addCriterion("experience_value not between", value1, value2, "experienceValue");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andFocusNumIsNull() {
            addCriterion("focus_num is null");
            return (Criteria) this;
        }

        public Criteria andFocusNumIsNotNull() {
            addCriterion("focus_num is not null");
            return (Criteria) this;
        }

        public Criteria andFocusNumEqualTo(Integer value) {
            addCriterion("focus_num =", value, "focusNum");
            return (Criteria) this;
        }

        public Criteria andFocusNumNotEqualTo(Integer value) {
            addCriterion("focus_num <>", value, "focusNum");
            return (Criteria) this;
        }

        public Criteria andFocusNumGreaterThan(Integer value) {
            addCriterion("focus_num >", value, "focusNum");
            return (Criteria) this;
        }

        public Criteria andFocusNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("focus_num >=", value, "focusNum");
            return (Criteria) this;
        }

        public Criteria andFocusNumLessThan(Integer value) {
            addCriterion("focus_num <", value, "focusNum");
            return (Criteria) this;
        }

        public Criteria andFocusNumLessThanOrEqualTo(Integer value) {
            addCriterion("focus_num <=", value, "focusNum");
            return (Criteria) this;
        }

        public Criteria andFocusNumIn(List<Integer> values) {
            addCriterion("focus_num in", values, "focusNum");
            return (Criteria) this;
        }

        public Criteria andFocusNumNotIn(List<Integer> values) {
            addCriterion("focus_num not in", values, "focusNum");
            return (Criteria) this;
        }

        public Criteria andFocusNumBetween(Integer value1, Integer value2) {
            addCriterion("focus_num between", value1, value2, "focusNum");
            return (Criteria) this;
        }

        public Criteria andFocusNumNotBetween(Integer value1, Integer value2) {
            addCriterion("focus_num not between", value1, value2, "focusNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIsNull() {
            addCriterion("question_num is null");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIsNotNull() {
            addCriterion("question_num is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionNumEqualTo(Integer value) {
            addCriterion("question_num =", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotEqualTo(Integer value) {
            addCriterion("question_num <>", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumGreaterThan(Integer value) {
            addCriterion("question_num >", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("question_num >=", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumLessThan(Integer value) {
            addCriterion("question_num <", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumLessThanOrEqualTo(Integer value) {
            addCriterion("question_num <=", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIn(List<Integer> values) {
            addCriterion("question_num in", values, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotIn(List<Integer> values) {
            addCriterion("question_num not in", values, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumBetween(Integer value1, Integer value2) {
            addCriterion("question_num between", value1, value2, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotBetween(Integer value1, Integer value2) {
            addCriterion("question_num not between", value1, value2, "questionNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumIsNull() {
            addCriterion("response_num is null");
            return (Criteria) this;
        }

        public Criteria andResponseNumIsNotNull() {
            addCriterion("response_num is not null");
            return (Criteria) this;
        }

        public Criteria andResponseNumEqualTo(Integer value) {
            addCriterion("response_num =", value, "responseNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumNotEqualTo(Integer value) {
            addCriterion("response_num <>", value, "responseNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumGreaterThan(Integer value) {
            addCriterion("response_num >", value, "responseNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("response_num >=", value, "responseNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumLessThan(Integer value) {
            addCriterion("response_num <", value, "responseNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumLessThanOrEqualTo(Integer value) {
            addCriterion("response_num <=", value, "responseNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumIn(List<Integer> values) {
            addCriterion("response_num in", values, "responseNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumNotIn(List<Integer> values) {
            addCriterion("response_num not in", values, "responseNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumBetween(Integer value1, Integer value2) {
            addCriterion("response_num between", value1, value2, "responseNum");
            return (Criteria) this;
        }

        public Criteria andResponseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("response_num not between", value1, value2, "responseNum");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}