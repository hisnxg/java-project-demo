package com.kaikeba.pojo;

public class Register {
    private Integer id;

    private String registerName;

    private String idNumber;

    private Double regPrice;

    private String medicalNumber;

    private String phone;

    private Integer selfPrice;

    private Integer sex;

    private Integer age;

    private String profession;

    private Integer lookDoctor;

    private Integer department;

    private Integer doctorId;
    /**
     * 一个挂号表对应一个医生，即负责人
     */
    private Doctor doctor ;

    private String note;

    private Integer status;

    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", registerName='" + registerName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", regPrice=" + regPrice +
                ", medicalNumber='" + medicalNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", selfPrice=" + selfPrice +
                ", sex=" + sex +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                ", lookDoctor=" + lookDoctor +
                ", department=" + department +
                ", doctorId=" + doctorId +
                ", doctor=" + doctor +
                ", note='" + note + '\'' +
                ", status=" + status +
                '}';
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName == null ? null : registerName.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public Double getRegPrice() {
        return regPrice;
    }

    public void setRegPrice(Double regPrice) {
        this.regPrice = regPrice;
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber == null ? null : medicalNumber.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getSelfPrice() {
        return selfPrice;
    }

    public void setSelfPrice(Integer selfPrice) {
        this.selfPrice = selfPrice;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public Integer getLookDoctor() {
        return lookDoctor;
    }

    public void setLookDoctor(Integer lookDoctor) {
        this.lookDoctor = lookDoctor;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}