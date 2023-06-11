package com.gxuwz.hospital.mapper;

import com.gxuwz.hospital.entity.Appointment;
import com.gxuwz.hospital.entity.Doctor;
import com.gxuwz.hospital.entity.Schedule;
import com.gxuwz.hospital.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssociationMapper {

    List<Appointment> findAllAppointment();

    List<Doctor> findAllDoctors();

    List<User> findAllUser();

    User findUser(String username);

    List<Schedule> findAllSchedule();

    List<Appointment> findAllAppointmentByPatientId(int patientId);
}
