package com.gxuwz.hospital.service;

import com.gxuwz.hospital.entity.Department;
import com.gxuwz.hospital.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll(){
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    public List<Department> findAny(String key){
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().filter(department -> department.toString().contains(key)).collect(Collectors.toList());
    }

    public boolean deleteById(int id){
        departmentRepository.deleteById(id);
        return false;
    }

    public boolean save(Department department) {
        Department save = departmentRepository.save(department);
        if (save!=null){
            return true;
        }
        return false;
    }

    public void update(Department department) {
        departmentRepository.save(department);
    }
}
