package com.gxuwz.hospital.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gxuwz.hospital.entity.Patient;
import com.gxuwz.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/findPatient")
    public String toPatient(Model model, String key) throws JsonProcessingException {
        List<Patient> patients = patientService.findAny(key);
        model.addAttribute("patients",patients);
        return "/pages/adminPages/patientInfo";
    }

    @GetMapping ("/deletePatient/{id}")
    public String deletePatient(@PathVariable("id") Integer id, HttpServletResponse httpServletResponse){
        patientService.deleteById(id);
        return "redirect:/findPatient?key=";
    }

    @PostMapping("/addPatient")
    public String addPatient(@RequestBody Patient patient){
        patientService.save(patient);
        return "redirect:/findPatient?key=";
    }

    @PostMapping("/editPatient")
    public String editPatient(@RequestBody Patient patient){
        patientService.update(patient);
        return "redirect:/findPatient?Key=";
    }
}
