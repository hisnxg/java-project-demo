package com.nxg.pojo;

public class Drug {
    private Integer id;

    private String img;

    private Double inPrice;

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", inPrice=" + inPrice +
                ", outPrice=" + outPrice +
                ", drugName='" + drugName + '\'' +
                ", drugType='" + drugType + '\'' +
                ", description='" + description + '\'' +
                ", quality=" + quality +
                ", detailDescription='" + detailDescription + '\'' +
                ", factory='" + factory + '\'' +
                ", instruction='" + instruction + '\'' +
                ", note='" + note + '\'' +
                ", totalNumber=" + totalNumber +
                ", sentNumber=" + sentNumber +
                ", resetNumber=" + resetNumber +
                ", status=" + status +
                '}';
    }

    private Double outPrice;

    private String drugName;

    private String drugType;

    private String description;

    private Integer quality;

    private String detailDescription;

    private String factory;

    private String instruction;

    private String note;

    private Integer totalNumber;

    private Integer sentNumber;

    private Integer resetNumber;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Double getInPrice() {
        return inPrice;
    }

    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }

    public Double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Double outPrice) {
        this.outPrice = outPrice;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName == null ? null : drugName.trim();
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType == null ? null : drugType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription == null ? null : detailDescription.trim();
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction == null ? null : instruction.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getSentNumber() {
        return sentNumber;
    }

    public void setSentNumber(Integer sentNumber) {
        this.sentNumber = sentNumber;
    }

    public Integer getResetNumber() {
        return resetNumber;
    }

    public void setResetNumber(Integer resetNumber) {
        this.resetNumber = resetNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}