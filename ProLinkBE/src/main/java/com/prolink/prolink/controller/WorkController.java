package com.prolink.prolink.controller;

import com.prolink.prolink.domain.Work;
import com.prolink.prolink.dto.AddWorkRequest;
import com.prolink.prolink.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/work")
public class WorkController {
    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/test")
    public String test() {
        return "work works";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Work addWork(@RequestBody AddWorkRequest request) {
        return workService.addWorkExperience(request.getProfileId(), request);
    }
}
