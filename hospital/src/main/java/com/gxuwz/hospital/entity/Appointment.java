package com.gxuwz.hospital.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "appointments")
@Alias("Appointment")
@Entity
public class Appointment {

    @Id
    @Column(name = "appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @Column(name = "appointment_date_time")
    private Timestamp appointmentDateTime;

    @Column(name = "appointment_status")
    private String appointmentStatus;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "schedule_id")
    private int scheduleId;

    @Transient
    private Patient patient;

    @Transient
    private Doctor doctor;

    @Transient
    private Schedule schedule;
}
