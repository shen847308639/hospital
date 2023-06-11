package com.gxuwz.hospital.entity;

import javax.persistence.*;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "schedules")
@Alias("Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private int scheduleId;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "schedule_date")
    private Date scheduleDate;

    @Column(name = "schedule_time")
    private String scheduleTime;

    @Column(name = "schedule_end_time")
    private String scheduleEndTime;

    @Transient
    private Doctor doctor;
}
