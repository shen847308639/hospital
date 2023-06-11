package com.gxuwz.hospital.entity;

import javax.persistence.*;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "doctors")
@Entity
@Alias("Doctor")
public class Doctor {
    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "doctor_phone")
    private String doctorPhone;

    @Column(name = "doctor_email")
    private String doctorEmail;

    @Transient
    private Department department;
}
