package com.kaikeba.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HospitalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HospitalExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andBedNumberIsNull() {
            addCriterion("bedNumber is null");
            return (Criteria) this;
        }

        public Criteria andBedNumberIsNotNull() {
            addCriterion("bedNumber is not null");
            return (Criteria) this;
        }

        public Criteria andBedNumberEqualTo(Integer value) {
            addCriterion("bedNumber =", value, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andBedNumberNotEqualTo(Integer value) {
            addCriterion("bedNumber <>", value, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andBedNumberGreaterThan(Integer value) {
            addCriterion("bedNumber >", value, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andBedNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("bedNumber >=", value, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andBedNumberLessThan(Integer value) {
            addCriterion("bedNumber <", value, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andBedNumberLessThanOrEqualTo(Integer value) {
            addCriterion("bedNumber <=", value, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andBedNumberIn(List<Integer> values) {
            addCriterion("bedNumber in", values, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andBedNumberNotIn(List<Integer> values) {
            addCriterion("bedNumber not in", values, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andBedNumberBetween(Integer value1, Integer value2) {
            addCriterion("bedNumber between", value1, value2, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andBedNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("bedNumber not between", value1, value2, "bedNumber");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(Double value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(Double value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(Double value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(Double value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(Double value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(Double value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<Double> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<Double> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(Double value1, Double value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(Double value1, Double value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNull() {
            addCriterion("introduction is null");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNotNull() {
            addCriterion("introduction is not null");
            return (Criteria) this;
        }

        public Criteria andIntroductionEqualTo(String value) {
            addCriterion("introduction =", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotEqualTo(String value) {
            addCriterion("introduction <>", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThan(String value) {
            addCriterion("introduction >", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("introduction >=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThan(String value) {
            addCriterion("introduction <", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThanOrEqualTo(String value) {
            addCriterion("introduction <=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLike(String value) {
            addCriterion("introduction like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotLike(String value) {
            addCriterion("introduction not like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionIn(List<String> values) {
            addCriterion("introduction in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotIn(List<String> values) {
            addCriterion("introduction not in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionBetween(String value1, String value2) {
            addCriterion("introduction between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotBetween(String value1, String value2) {
            addCriterion("introduction not between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andEndStatusIsNull() {
            addCriterion("endStatus is null");
            return (Criteria) this;
        }

        public Criteria andEndStatusIsNotNull() {
            addCriterion("endStatus is not null");
            return (Criteria) this;
        }

        public Criteria andEndStatusEqualTo(Integer value) {
            addCriterion("endStatus =", value, "endStatus");
            return (Criteria) this;
        }

        public Criteria andEndStatusNotEqualTo(Integer value) {
            addCriterion("endStatus <>", value, "endStatus");
            return (Criteria) this;
        }

        public Criteria andEndStatusGreaterThan(Integer value) {
            addCriterion("endStatus >", value, "endStatus");
            return (Criteria) this;
        }

        public Criteria andEndStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("endStatus >=", value, "endStatus");
            return (Criteria) this;
        }

        public Criteria andEndStatusLessThan(Integer value) {
            addCriterion("endStatus <", value, "endStatus");
            return (Criteria) this;
        }

        public Criteria andEndStatusLessThanOrEqualTo(Integer value) {
            addCriterion("endStatus <=", value, "endStatus");
            return (Criteria) this;
        }

        public Criteria andEndStatusIn(List<Integer> values) {
            addCriterion("endStatus in", values, "endStatus");
            return (Criteria) this;
        }

        public Criteria andEndStatusNotIn(List<Integer> values) {
            addCriterion("endStatus not in", values, "endStatus");
            return (Criteria) this;
        }

        public Criteria andEndStatusBetween(Integer value1, Integer value2) {
            addCriterion("endStatus between", value1, value2, "endStatus");
            return (Criteria) this;
        }

        public Criteria andEndStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("endStatus not between", value1, value2, "endStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIsNull() {
            addCriterion("infoStatus is null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIsNotNull() {
            addCriterion("infoStatus is not null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusEqualTo(Integer value) {
            addCriterion("infoStatus =", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNotEqualTo(Integer value) {
            addCriterion("infoStatus <>", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusGreaterThan(Integer value) {
            addCriterion("infoStatus >", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("infoStatus >=", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusLessThan(Integer value) {
            addCriterion("infoStatus <", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusLessThanOrEqualTo(Integer value) {
            addCriterion("infoStatus <=", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIn(List<Integer> values) {
            addCriterion("infoStatus in", values, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNotIn(List<Integer> values) {
            addCriterion("infoStatus not in", values, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusBetween(Integer value1, Integer value2) {
            addCriterion("infoStatus between", value1, value2, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("infoStatus not between", value1, value2, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andRegisterIdIsNull() {
            addCriterion("registerId is null");
            return (Criteria) this;
        }

        public Criteria andRegisterIdIsNotNull() {
            addCriterion("registerId is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterIdEqualTo(Integer value) {
            addCriterion("registerId =", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdNotEqualTo(Integer value) {
            addCriterion("registerId <>", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdGreaterThan(Integer value) {
            addCriterion("registerId >", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("registerId >=", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdLessThan(Integer value) {
            addCriterion("registerId <", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdLessThanOrEqualTo(Integer value) {
            addCriterion("registerId <=", value, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdIn(List<Integer> values) {
            addCriterion("registerId in", values, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdNotIn(List<Integer> values) {
            addCriterion("registerId not in", values, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdBetween(Integer value1, Integer value2) {
            addCriterion("registerId between", value1, value2, "registerId");
            return (Criteria) this;
        }

        public Criteria andRegisterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("registerId not between", value1, value2, "registerId");
            return (Criteria) this;
        }

        public Criteria andHospitalDateIsNull() {
            addCriterion("hospitalDate is null");
            return (Criteria) this;
        }

        public Criteria andHospitalDateIsNotNull() {
            addCriterion("hospitalDate is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalDateEqualTo(Date value) {
            addCriterionForJDBCDate("hospitalDate =", value, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andHospitalDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("hospitalDate <>", value, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andHospitalDateGreaterThan(Date value) {
            addCriterionForJDBCDate("hospitalDate >", value, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andHospitalDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("hospitalDate >=", value, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andHospitalDateLessThan(Date value) {
            addCriterionForJDBCDate("hospitalDate <", value, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andHospitalDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("hospitalDate <=", value, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andHospitalDateIn(List<Date> values) {
            addCriterionForJDBCDate("hospitalDate in", values, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andHospitalDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("hospitalDate not in", values, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andHospitalDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("hospitalDate between", value1, value2, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andHospitalDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("hospitalDate not between", value1, value2, "hospitalDate");
            return (Criteria) this;
        }

        public Criteria andTotalCostIsNull() {
            addCriterion("totalCost is null");
            return (Criteria) this;
        }

        public Criteria andTotalCostIsNotNull() {
            addCriterion("totalCost is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCostEqualTo(Integer value) {
            addCriterion("totalCost =", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostNotEqualTo(Integer value) {
            addCriterion("totalCost <>", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostGreaterThan(Integer value) {
            addCriterion("totalCost >", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalCost >=", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostLessThan(Integer value) {
            addCriterion("totalCost <", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostLessThanOrEqualTo(Integer value) {
            addCriterion("totalCost <=", value, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostIn(List<Integer> values) {
            addCriterion("totalCost in", values, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostNotIn(List<Integer> values) {
            addCriterion("totalCost not in", values, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostBetween(Integer value1, Integer value2) {
            addCriterion("totalCost between", value1, value2, "totalCost");
            return (Criteria) this;
        }

        public Criteria andTotalCostNotBetween(Integer value1, Integer value2) {
            addCriterion("totalCost not between", value1, value2, "totalCost");
            return (Criteria) this;
        }

        public Criteria andRestCostIsNull() {
            addCriterion("restCost is null");
            return (Criteria) this;
        }

        public Criteria andRestCostIsNotNull() {
            addCriterion("restCost is not null");
            return (Criteria) this;
        }

        public Criteria andRestCostEqualTo(Integer value) {
            addCriterion("restCost =", value, "restCost");
            return (Criteria) this;
        }

        public Criteria andRestCostNotEqualTo(Integer value) {
            addCriterion("restCost <>", value, "restCost");
            return (Criteria) this;
        }

        public Criteria andRestCostGreaterThan(Integer value) {
            addCriterion("restCost >", value, "restCost");
            return (Criteria) this;
        }

        public Criteria andRestCostGreaterThanOrEqualTo(Integer value) {
            addCriterion("restCost >=", value, "restCost");
            return (Criteria) this;
        }

        public Criteria andRestCostLessThan(Integer value) {
            addCriterion("restCost <", value, "restCost");
            return (Criteria) this;
        }

        public Criteria andRestCostLessThanOrEqualTo(Integer value) {
            addCriterion("restCost <=", value, "restCost");
            return (Criteria) this;
        }

        public Criteria andRestCostIn(List<Integer> values) {
            addCriterion("restCost in", values, "restCost");
            return (Criteria) this;
        }

        public Criteria andRestCostNotIn(List<Integer> values) {
            addCriterion("restCost not in", values, "restCost");
            return (Criteria) this;
        }

        public Criteria andRestCostBetween(Integer value1, Integer value2) {
            addCriterion("restCost between", value1, value2, "restCost");
            return (Criteria) this;
        }

        public Criteria andRestCostNotBetween(Integer value1, Integer value2) {
            addCriterion("restCost not between", value1, value2, "restCost");
            return (Criteria) this;
        }
    }

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