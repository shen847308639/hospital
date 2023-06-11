package com.gxuwz.hospital.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxuwz.hospital.entity.Department;
import com.gxuwz.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping ("/findDepartment")
    public String toDepartment(Model model,String key) throws JsonProcessingException {
        List<Department> departments = departmentService.findAny(key);
        ObjectMapper objectMapper = new ObjectMapper();
        model.addAttribute("departments",departments);
        return "/pages/adminPages/departmentInfo";
    }

    @GetMapping ("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id, HttpServletResponse httpServletResponse){
        departmentService.deleteById(id);
        return "redirect:/findDepartment?key=";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@RequestBody Department department){
        departmentService.save(department);
        return "redirect:/findDepartment?key=";
    }

    @PostMapping("/editDepartment")
    public String editDepartment(@RequestBody Department department){
        departmentService.update(department);
        return "redirect:/findDepartment?Key=";
    }
}
