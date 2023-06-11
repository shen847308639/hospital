package com.gxuwz.hospital.service;

import com.gxuwz.hospital.entity.Patient;
import com.gxuwz.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll(){
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    public List<Patient> findAny(String key){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().filter(patient -> patient.toString().contains(key)).collect(Collectors.toList());
    }

    public boolean deleteById(int id){
        patientRepository.deleteById(id);
        return false;
    }

    public boolean save(Patient patient) {
        Patient save = patientRepository.save(patient);
        if (save!=null){
            return true;
        }
        return false;
    }

    public void update(Patient patient) {
        patientRepository.save(patient);
    }
}
