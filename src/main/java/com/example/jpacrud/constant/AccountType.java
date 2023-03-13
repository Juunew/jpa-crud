package com.example.jpacrud.constant;

public enum AccountType {
    LESSOR("임대인"),
    REALTOR("공인중개사"),
    LESSEE("임차인"),
    GENERAL("외부사용자");

    private final String userRole;

    AccountType(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return this.userRole;
    }
}
