package com.example.jpacrud.entity;

import com.example.jpacrud.constant.AccountType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "quit")
    @ColumnDefault("'N'")
    private String quit;
}
