package com.example.spring_velocity_ini.service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_velocity_ini.pojo.User;
import com.example.spring_velocity_ini.service.VelocityTemplateService;

@RestController
public class TemplateController {

    @Autowired
    private VelocityTemplateService velocityTemplateService;

    @GetMapping("/generate-ini-file")
    public ResponseEntity<String> generateIniFile() {
        User user = new User("Suman Ghosh", 30, "suman@example.com");
        String iniContent = velocityTemplateService.generateFromTemplate(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user.ini");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/plain");

        return new ResponseEntity<>(iniContent, headers, HttpStatus.OK);
    }
}
