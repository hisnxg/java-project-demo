package com.nxg.vo;

public class QueryDoctorVO {
	private Integer doctorId;
	private String doctorName;
	private String deptName;//科室

	public QueryDoctorVO() {
	}

	public QueryDoctorVO(Integer doctorId, String doctorName, String deptName) {
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.deptName = deptName;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
