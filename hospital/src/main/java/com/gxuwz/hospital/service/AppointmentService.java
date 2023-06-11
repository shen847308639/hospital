package com.gxuwz.hospital.service;

import com.gxuwz.hospital.entity.Appointment;
import com.gxuwz.hospital.entity.Schedule;
import com.gxuwz.hospital.entity.User;
import com.gxuwz.hospital.mapper.AssociationMapper;
import com.gxuwz.hospital.repository.AppointmentRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AssociationMapper associationMapper;

    public List<Appointment> findAny(String key){
        List<Appointment> appointments = associationMapper.findAllAppointment();
        return appointments.stream().filter(appointment -> appointment.toString().contains(key)).collect(Collectors.toList());
    }

    public boolean deleteById(int id){
        appointmentRepository.deleteById(id);
        return false;
    }

    public Long save(Schedule schedule) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        long sevenDaysAgoTome = LocalDateTime.now().minusDays(7).toEpochSecond(ZoneOffset.UTC);
        User user = associationMapper.findUser(username);
        Appointment appointment = Appointment.builder()
                .appointmentDateTime(timestamp)
                .scheduleId(schedule.getScheduleId())
                .doctorId(schedule.getDoctorId())
                .patientId(user.getPatientId())
                .appointmentStatus("等待取号")
                .build();
        Appointment save = appointmentRepository.save(appointment);
        long time = timestamp.getTime();
        List<Appointment> allByScheduleId = appointmentRepository.findAllByScheduleId(schedule.getScheduleId());
        long count = allByScheduleId.stream().filter(a -> a.getAppointmentDateTime().getTime() < time && sevenDaysAgoTome < a.getAppointmentDateTime().getTime() ).count();
        if (allByScheduleId != null){
            return count;
        }
        return 0L;
    }

    public List<Appointment> findAppointmentById(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = associationMapper.findUser(username);
        associationMapper.findAllAppointmentByPatientId(user.getPatientId());
        List<Appointment> appointments = associationMapper.findAllAppointmentByPatientId(user.getPatientId());
        return appointments;
    }

    public void update(Appointment appointment) {
        appointmentRepository.save(appointment);
    }
}
