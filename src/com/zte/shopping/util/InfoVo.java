package com.zte.shopping.util;

import com.zte.shopping.util.MD5Util;

public class InfoVo {
    private String id;
    private String staffName;
    private String loginName;
    private String phone;
    private String email;
    private String role;
    private String deptName;

    public InfoVo() {}

    public InfoVo(String id, String staffName, String loginName, String phone, String email, String role, String deptName) {
        this.id = id;
        this.staffName = staffName;
        this.loginName = loginName;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.deptName = deptName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
