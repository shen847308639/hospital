package com.gxuwz.hospital.entity;

import javax.persistence.*;
import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.persistence.GeneratedValue;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "users")
@Entity
@Alias("User")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String password;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "patient_id")
    private Integer patientId;

    @Transient
    private Role role;

    @Transient
    private Patient patient;
}
