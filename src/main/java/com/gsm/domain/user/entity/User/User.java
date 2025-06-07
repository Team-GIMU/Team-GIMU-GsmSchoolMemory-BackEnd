package com.gsm.domain.user.entity.User;


import com.gsm.domain.user.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "student_name" , nullable = false)
    private String name;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "grade" , nullable = false)
    private int grade;

    @Column(name = "class_num" , nullable = false)
    private int classNum;

    @Column(name = "student_num" , nullable = false)
    private int stuNum;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public void updateRole(Role role) {
        this.role = role;
    }
}
