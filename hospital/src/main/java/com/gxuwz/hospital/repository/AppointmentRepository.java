package com.gxuwz.hospital.repository;

import com.gxuwz.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findAllByScheduleId(int scheduleId);

    List<Appointment> findAllByPatientId(int appointmentId);

}
