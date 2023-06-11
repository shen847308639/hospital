package com.gxuwz.hospital.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gxuwz.hospital.entity.Patient;
import com.gxuwz.hospital.entity.Role;
import com.gxuwz.hospital.entity.User;
import com.gxuwz.hospital.mapper.AssociationMapper;
import com.gxuwz.hospital.repository.PatientRepository;
import com.gxuwz.hospital.repository.RoleRepository;
import com.gxuwz.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AssociationMapper associationMapper;

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public JsonNode findAny(String key){
        List<User> users = associationMapper.findAllUser().stream().filter(user -> user.toString().contains(key)).collect(Collectors.toList());
        List<Role> roles = roleRepository.findAll();
        List<Patient> patients = patientRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootJson = objectMapper.createObjectNode();

        JsonNode usersJson = objectMapper.valueToTree(users);
        JsonNode rolesJson = objectMapper.valueToTree(roles);
        JsonNode patientsJson = objectMapper.valueToTree(patients);

        rootJson.set("usersJson",usersJson);
        rootJson.set("rolesJson",rolesJson);
        rootJson.set("patientsJson", patientsJson);

        return rootJson;
    }

    public boolean deleteById(int id){
        userRepository.deleteById(id);
        return false;
    }

    public boolean save(User user) {
        if (user.getPatient()!=null) {
            Patient resultPatient = patientRepository.save(user.getPatient());
            user.setPatientId(resultPatient.getPatientId());
        }
        User save = userRepository.save(user);
        if (save!=null){
            return true;
        }
        return false;
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public Boolean isAdminUser(String username,String password){
        User user = userRepository.findUserByUsername(username);
        if (password.equals(user.getPassword())){
            return true;
        }
        return false;
    }
}
