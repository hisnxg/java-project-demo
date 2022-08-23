package com.kkb.bean;



public class User {
    private Integer uId;

    private String uLoginname;

    private String uPassword;

    private String uTruename;

    private String uEmail;

    private Integer uState;

    private Integer rId;

    private Role role;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuLoginname() {
        return uLoginname;
    }

    public void setuLoginname(String uLoginname) {
        this.uLoginname = uLoginname == null ? null : uLoginname.trim();
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {

        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public String getuTruename() {
        return uTruename;
    }

    public void setuTruename(String uTruename) {
        this.uTruename = uTruename == null ? null : uTruename.trim();
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail == null ? null : uEmail.trim();
    }

    public Integer getuState() {
        return uState;
    }

    public void setuState(Integer uState) {
        this.uState = uState;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}