package com.zte.shopping.util;

public class UserVo {
    private String userid;
    private String userName;
    private String loginName;
    private String phone;
    private String address;

    public UserVo() { }

    public UserVo(String userid, String userName, String loginName, String phone, String address) {
        this.userid = userid;
        this.userName = userName;
        this.loginName = loginName;
        this.phone = phone;
        this.address = address;

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
