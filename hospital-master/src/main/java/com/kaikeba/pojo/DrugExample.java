package com.kaikeba.pojo;

import java.util.ArrayList;
import java.util.List;

public class DrugExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DrugExample() {
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

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andInPriceIsNull() {
            addCriterion("inPrice is null");
            return (Criteria) this;
        }

        public Criteria andInPriceIsNotNull() {
            addCriterion("inPrice is not null");
            return (Criteria) this;
        }

        public Criteria andInPriceEqualTo(Double value) {
            addCriterion("inPrice =", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceNotEqualTo(Double value) {
            addCriterion("inPrice <>", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceGreaterThan(Double value) {
            addCriterion("inPrice >", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("inPrice >=", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceLessThan(Double value) {
            addCriterion("inPrice <", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceLessThanOrEqualTo(Double value) {
            addCriterion("inPrice <=", value, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceIn(List<Double> values) {
            addCriterion("inPrice in", values, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceNotIn(List<Double> values) {
            addCriterion("inPrice not in", values, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceBetween(Double value1, Double value2) {
            addCriterion("inPrice between", value1, value2, "inPrice");
            return (Criteria) this;
        }

        public Criteria andInPriceNotBetween(Double value1, Double value2) {
            addCriterion("inPrice not between", value1, value2, "inPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceIsNull() {
            addCriterion("outPrice is null");
            return (Criteria) this;
        }

        public Criteria andOutPriceIsNotNull() {
            addCriterion("outPrice is not null");
            return (Criteria) this;
        }

        public Criteria andOutPriceEqualTo(Double value) {
            addCriterion("outPrice =", value, "outPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceNotEqualTo(Double value) {
            addCriterion("outPrice <>", value, "outPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceGreaterThan(Double value) {
            addCriterion("outPrice >", value, "outPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("outPrice >=", value, "outPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceLessThan(Double value) {
            addCriterion("outPrice <", value, "outPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceLessThanOrEqualTo(Double value) {
            addCriterion("outPrice <=", value, "outPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceIn(List<Double> values) {
            addCriterion("outPrice in", values, "outPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceNotIn(List<Double> values) {
            addCriterion("outPrice not in", values, "outPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceBetween(Double value1, Double value2) {
            addCriterion("outPrice between", value1, value2, "outPrice");
            return (Criteria) this;
        }

        public Criteria andOutPriceNotBetween(Double value1, Double value2) {
            addCriterion("outPrice not between", value1, value2, "outPrice");
            return (Criteria) this;
        }

        public Criteria andDrugNameIsNull() {
            addCriterion("drugName is null");
            return (Criteria) this;
        }

        public Criteria andDrugNameIsNotNull() {
            addCriterion("drugName is not null");
            return (Criteria) this;
        }

        public Criteria andDrugNameEqualTo(String value) {
            addCriterion("drugName =", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotEqualTo(String value) {
            addCriterion("drugName <>", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameGreaterThan(String value) {
            addCriterion("drugName >", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameGreaterThanOrEqualTo(String value) {
            addCriterion("drugName >=", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameLessThan(String value) {
            addCriterion("drugName <", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameLessThanOrEqualTo(String value) {
            addCriterion("drugName <=", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameLike(String value) {
            addCriterion("drugName like", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotLike(String value) {
            addCriterion("drugName not like", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameIn(List<String> values) {
            addCriterion("drugName in", values, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotIn(List<String> values) {
            addCriterion("drugName not in", values, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameBetween(String value1, String value2) {
            addCriterion("drugName between", value1, value2, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotBetween(String value1, String value2) {
            addCriterion("drugName not between", value1, value2, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIsNull() {
            addCriterion("drugType is null");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIsNotNull() {
            addCriterion("drugType is not null");
            return (Criteria) this;
        }

        public Criteria andDrugTypeEqualTo(String value) {
            addCriterion("drugType =", value, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNotEqualTo(String value) {
            addCriterion("drugType <>", value, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeGreaterThan(String value) {
            addCriterion("drugType >", value, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeGreaterThanOrEqualTo(String value) {
            addCriterion("drugType >=", value, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeLessThan(String value) {
            addCriterion("drugType <", value, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeLessThanOrEqualTo(String value) {
            addCriterion("drugType <=", value, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeLike(String value) {
            addCriterion("drugType like", value, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNotLike(String value) {
            addCriterion("drugType not like", value, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIn(List<String> values) {
            addCriterion("drugType in", values, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNotIn(List<String> values) {
            addCriterion("drugType not in", values, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeBetween(String value1, String value2) {
            addCriterion("drugType between", value1, value2, "drugType");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNotBetween(String value1, String value2) {
            addCriterion("drugType not between", value1, value2, "drugType");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andQualityIsNull() {
            addCriterion("quality is null");
            return (Criteria) this;
        }

        public Criteria andQualityIsNotNull() {
            addCriterion("quality is not null");
            return (Criteria) this;
        }

        public Criteria andQualityEqualTo(Integer value) {
            addCriterion("quality =", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotEqualTo(Integer value) {
            addCriterion("quality <>", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThan(Integer value) {
            addCriterion("quality >", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quality >=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThan(Integer value) {
            addCriterion("quality <", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThanOrEqualTo(Integer value) {
            addCriterion("quality <=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityIn(List<Integer> values) {
            addCriterion("quality in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotIn(List<Integer> values) {
            addCriterion("quality not in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityBetween(Integer value1, Integer value2) {
            addCriterion("quality between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotBetween(Integer value1, Integer value2) {
            addCriterion("quality not between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionIsNull() {
            addCriterion("detailDescription is null");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionIsNotNull() {
            addCriterion("detailDescription is not null");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionEqualTo(String value) {
            addCriterion("detailDescription =", value, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionNotEqualTo(String value) {
            addCriterion("detailDescription <>", value, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionGreaterThan(String value) {
            addCriterion("detailDescription >", value, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("detailDescription >=", value, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionLessThan(String value) {
            addCriterion("detailDescription <", value, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionLessThanOrEqualTo(String value) {
            addCriterion("detailDescription <=", value, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionLike(String value) {
            addCriterion("detailDescription like", value, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionNotLike(String value) {
            addCriterion("detailDescription not like", value, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionIn(List<String> values) {
            addCriterion("detailDescription in", values, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionNotIn(List<String> values) {
            addCriterion("detailDescription not in", values, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionBetween(String value1, String value2) {
            addCriterion("detailDescription between", value1, value2, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andDetailDescriptionNotBetween(String value1, String value2) {
            addCriterion("detailDescription not between", value1, value2, "detailDescription");
            return (Criteria) this;
        }

        public Criteria andFactoryIsNull() {
            addCriterion("factory is null");
            return (Criteria) this;
        }

        public Criteria andFactoryIsNotNull() {
            addCriterion("factory is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryEqualTo(String value) {
            addCriterion("factory =", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryNotEqualTo(String value) {
            addCriterion("factory <>", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryGreaterThan(String value) {
            addCriterion("factory >", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryGreaterThanOrEqualTo(String value) {
            addCriterion("factory >=", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryLessThan(String value) {
            addCriterion("factory <", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryLessThanOrEqualTo(String value) {
            addCriterion("factory <=", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryLike(String value) {
            addCriterion("factory like", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryNotLike(String value) {
            addCriterion("factory not like", value, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryIn(List<String> values) {
            addCriterion("factory in", values, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryNotIn(List<String> values) {
            addCriterion("factory not in", values, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryBetween(String value1, String value2) {
            addCriterion("factory between", value1, value2, "factory");
            return (Criteria) this;
        }

        public Criteria andFactoryNotBetween(String value1, String value2) {
            addCriterion("factory not between", value1, value2, "factory");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNull() {
            addCriterion("instruction is null");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNotNull() {
            addCriterion("instruction is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionEqualTo(String value) {
            addCriterion("instruction =", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotEqualTo(String value) {
            addCriterion("instruction <>", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThan(String value) {
            addCriterion("instruction >", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThanOrEqualTo(String value) {
            addCriterion("instruction >=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThan(String value) {
            addCriterion("instruction <", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThanOrEqualTo(String value) {
            addCriterion("instruction <=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLike(String value) {
            addCriterion("instruction like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotLike(String value) {
            addCriterion("instruction not like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionIn(List<String> values) {
            addCriterion("instruction in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotIn(List<String> values) {
            addCriterion("instruction not in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionBetween(String value1, String value2) {
            addCriterion("instruction between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotBetween(String value1, String value2) {
            addCriterion("instruction not between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIsNull() {
            addCriterion("totalNumber is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIsNotNull() {
            addCriterion("totalNumber is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumberEqualTo(Integer value) {
            addCriterion("totalNumber =", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotEqualTo(Integer value) {
            addCriterion("totalNumber <>", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberGreaterThan(Integer value) {
            addCriterion("totalNumber >", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalNumber >=", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberLessThan(Integer value) {
            addCriterion("totalNumber <", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberLessThanOrEqualTo(Integer value) {
            addCriterion("totalNumber <=", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIn(List<Integer> values) {
            addCriterion("totalNumber in", values, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotIn(List<Integer> values) {
            addCriterion("totalNumber not in", values, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberBetween(Integer value1, Integer value2) {
            addCriterion("totalNumber between", value1, value2, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("totalNumber not between", value1, value2, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberIsNull() {
            addCriterion("sentNumber is null");
            return (Criteria) this;
        }

        public Criteria andSentNumberIsNotNull() {
            addCriterion("sentNumber is not null");
            return (Criteria) this;
        }

        public Criteria andSentNumberEqualTo(Integer value) {
            addCriterion("sentNumber =", value, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberNotEqualTo(Integer value) {
            addCriterion("sentNumber <>", value, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberGreaterThan(Integer value) {
            addCriterion("sentNumber >", value, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("sentNumber >=", value, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberLessThan(Integer value) {
            addCriterion("sentNumber <", value, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberLessThanOrEqualTo(Integer value) {
            addCriterion("sentNumber <=", value, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberIn(List<Integer> values) {
            addCriterion("sentNumber in", values, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberNotIn(List<Integer> values) {
            addCriterion("sentNumber not in", values, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberBetween(Integer value1, Integer value2) {
            addCriterion("sentNumber between", value1, value2, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andSentNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("sentNumber not between", value1, value2, "sentNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberIsNull() {
            addCriterion("resetNumber is null");
            return (Criteria) this;
        }

        public Criteria andResetNumberIsNotNull() {
            addCriterion("resetNumber is not null");
            return (Criteria) this;
        }

        public Criteria andResetNumberEqualTo(Integer value) {
            addCriterion("resetNumber =", value, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberNotEqualTo(Integer value) {
            addCriterion("resetNumber <>", value, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberGreaterThan(Integer value) {
            addCriterion("resetNumber >", value, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("resetNumber >=", value, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberLessThan(Integer value) {
            addCriterion("resetNumber <", value, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberLessThanOrEqualTo(Integer value) {
            addCriterion("resetNumber <=", value, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberIn(List<Integer> values) {
            addCriterion("resetNumber in", values, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberNotIn(List<Integer> values) {
            addCriterion("resetNumber not in", values, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberBetween(Integer value1, Integer value2) {
            addCriterion("resetNumber between", value1, value2, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andResetNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("resetNumber not between", value1, value2, "resetNumber");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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