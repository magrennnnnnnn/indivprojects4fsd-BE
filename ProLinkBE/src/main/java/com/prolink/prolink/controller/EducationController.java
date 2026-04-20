package com.prolink.prolink.controller;

import com.prolink.prolink.domain.Education;
import com.prolink.prolink.dto.AddEducationRequest;
import com.prolink.prolink.dto.UpdateEducationRequest;
import com.prolink.prolink.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/education")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Education addEducation(@RequestBody AddEducationRequest request) {
        return educationService.addEducationalExperience(request.getProfileId(), request);
    }

    @PutMapping("/{educationId}")
    public Education updateEducation(@PathVariable Long educationId,
                                     @RequestBody UpdateEducationRequest request) {
        return educationService.updateEducation(educationId, request);
    }
}
