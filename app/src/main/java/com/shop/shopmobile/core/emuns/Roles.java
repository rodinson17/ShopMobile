package com.shop.shopmobile.core.emuns;

public enum Roles {

    ROLE_CUSTOMER("Customer"),
    ROLE_ADMIN("Administrator"),
    ROLE_EMPLOYEE("Employee");

    private String role;

    Roles(String role) {
        this.role = role;
    }

//    public static Roles getByString(String role) {
//
//        switch (role)
//    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
