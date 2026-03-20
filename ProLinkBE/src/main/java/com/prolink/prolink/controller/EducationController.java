package com.prolink.prolink.controller;

import com.prolink.prolink.dto.AddEducationRequest;
import com.prolink.prolink.entity.EducationalExperience;
import com.prolink.prolink.service.EducationService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/education")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/test")
    public String test() {
        return "education works";
    }

    @PostMapping
    public EducationalExperience addEducation(@RequestBody AddEducationRequest request) {
        return educationService.addEducation(request);
    }
}
