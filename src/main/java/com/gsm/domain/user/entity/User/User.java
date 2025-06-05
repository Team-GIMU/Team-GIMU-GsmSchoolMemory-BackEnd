package com.gsm.domain.user.entity.User;


import com.gsm.domain.user.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "student_name" , nullable = false)
    private String name;

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
