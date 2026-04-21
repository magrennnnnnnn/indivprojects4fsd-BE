package com.prolink.prolink.controller;
import com.prolink.prolink.domain.Course;
import com.prolink.prolink.dto.AddCourseRequest;
import com.prolink.prolink.dto.UpdateCourseRequest;
import com.prolink.prolink.entity.CoursesEntity;
import com.prolink.prolink.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course addCourse(@RequestBody AddCourseRequest request){
        return courseService.addCourseExperience(request.getProfileId(),request);
    }

    @PutMapping("/{courseId}")
    public Course updateCourse(@PathVariable Long courseId,
                               @RequestBody UpdateCourseRequest request){
        return courseService.updateCourse(courseId,request);
    }

}
