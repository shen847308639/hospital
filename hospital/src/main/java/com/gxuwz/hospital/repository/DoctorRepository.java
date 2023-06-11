package com.gxuwz.hospital.repository;

import com.gxuwz.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Modifying
    @Query("UPDATE Doctor d SET d.doctorName = :doctorName, d.departmentId = :departmentId, d.doctorPhone = :doctorPhone, d.doctorEmail = :doctorEmail WHERE d.doctorId = :doctorId")
    void defineMyUpdate(@Param("doctorId") int doctorId, @Param("doctorName") String doctorName, @Param("departmentId") int departmentId, @Param("doctorPhone") String doctorPhone, @Param("doctorEmail") String doctorEmail);
}
