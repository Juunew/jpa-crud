package com.example.jpacrud.dto.auth;

import com.example.jpacrud.constant.AccountType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserInfo {

    private String accountId;
    private AccountType accountType;

    public UserInfo(HttpServletRequest request) {
        if (request.getHeader("Authentication").isEmpty()) {
            this.accountId = "4";
            this.accountType = AccountType.GENERAL;
        } else {
            String[] authenticationArray = request.getHeader("Authentication").split(" ");
            this.accountId = authenticationArray[1];
            this.accountType = AccountType.valueOf(authenticationArray[0].toUpperCase());
        }
    }
}
