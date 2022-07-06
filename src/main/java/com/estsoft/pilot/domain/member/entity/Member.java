package com.estsoft.pilot.domain.member.entity;

import com.estsoft.pilot.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Builder
    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        // this.memberType = memberType;
        this.password = password;
        // this.role = role;
    }
}
