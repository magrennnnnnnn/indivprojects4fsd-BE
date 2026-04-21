package com.prolink.prolink.controller;
import com.prolink.prolink.dto.AddCourseRequest;
import com.prolink.prolink.entity.CoursesEntity;
import com.prolink.prolink.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/test")
    public String test() {
        return "course works";
    }

    @PostMapping
    public CoursesEntity addCourse(@RequestBody AddCourseRequest request) {
        return courseService.addCourseExperience(request);
    }
}
