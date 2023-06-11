package com.gxuwz.hospital.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxuwz.hospital.entity.Doctor;
import com.gxuwz.hospital.entity.Patient;
import com.gxuwz.hospital.entity.Role;
import com.gxuwz.hospital.entity.User;
import com.gxuwz.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping ("/findUser")
    public String toUser(Model model,String key) throws JsonProcessingException {
        JsonNode rootJson = userService.findAny(key);

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(rootJson.get("usersJson").toString(),new TypeReference<List<User>>(){});
        JsonNode roles = rootJson.get("rolesJson");
        JsonNode patients = rootJson.get("patientsJson");

        model.addAttribute("users",users);
        model.addAttribute("roles",roles);
        model.addAttribute("patients",patients);
        return "/pages/adminPages/userInfo";
    }


    @GetMapping ("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteById(id);
        return "redirect:/findUser?key=";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        userService.save(user);
        return "redirect:/findUser?key=";
    }

}
