package com.gxuwz.hospital.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gxuwz.hospital.entity.Department;
import com.gxuwz.hospital.entity.Doctor;
import com.gxuwz.hospital.mapper.AssociationMapper;
import com.gxuwz.hospital.repository.DepartmentRepository;
import com.gxuwz.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AssociationMapper associationMapper;

    public JsonNode findAny(String key){
        List<Doctor> doctors = null;
        if (key != null && key != "") {
            doctors = associationMapper.findAllDoctors().stream().filter(doctor -> doctor.toString().contains(key)).collect(Collectors.toList());
        }else {
            doctors = associationMapper.findAllDoctors();
        }
        List<Department> departments = departmentRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode doctorsJson = objectMapper.valueToTree(doctors);
        JsonNode departmentsJson = objectMapper.valueToTree(departments);

        ObjectNode resultJson = objectMapper.createObjectNode();
        resultJson.set("doctorsJson", doctorsJson);
        resultJson.set("departmentsJson", departmentsJson);

        return resultJson;
    }

    public boolean deleteById(int id){
        doctorRepository.deleteById(id);
        return false;
    }

    public boolean save(Doctor doctor) {
        Doctor save = doctorRepository.save(doctor);
        if (save!=null){
            return true;
        }
        return false;
    }

    public void update(Doctor doctor) {
        doctorRepository.defineMyUpdate(doctor.getDoctorId(), doctor.getDoctorName(), doctor.getDepartmentId(), doctor.getDoctorPhone(), doctor.getDoctorEmail());
    }
}
