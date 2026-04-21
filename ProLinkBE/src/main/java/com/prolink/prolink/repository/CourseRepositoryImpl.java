package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Course;
import com.prolink.prolink.entity.CoursesEntity;
import com.prolink.prolink.entity.ProfileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository{
    private final EducationJpaRepo educationJpaRepo;
    private final CourseJpaRepo courseJpaRepo;

    public CourseRepositoryImpl(EducationJpaRepo educationJpaRepo, CourseJpaRepo courseJpaRepo){
        this.educationJpaRepo=educationJpaRepo;
        this.courseJpaRepo = courseJpaRepo;
    }

    @Override
   public Optional<Course> findByIdProfileCourse(Long idProfileCourse){
        return courseJpaRepo.findByIdProfileCourse(idProfileCourse)
                .map(this::toDomain);
    }

    @Override
    public List<Course> findByProfileId(Long profileId){
     return courseJpaRepo.findByProfileEntity_IdProfile(profileId)
             .stream()
             .map(this::toDomain)
             .toList();
    }

    @Override
    public Course save(Course course){
        CoursesEntity entity = toEntity(course);
        CoursesEntity saved = courseJpaRepo.save(entity);
        return toDomain(saved);
    }

    private Course toDomain(CoursesEntity entity){
        return new Course(
                entity.getIdProfileCourse(),
                entity.getCourseName(),
                entity.getStartDateCourse(),
                entity.getEndDateCourse(),
                entity.getCourseSkills(),
                entity.getCourse(),
                entity.getProfile().getId()
        );
    }

    private CoursesEntity toEntity(Course course){
        CoursesEntity entity = new CoursesEntity();
        entity.setIdProfileCourse(course.getIdProfileCourse());
        entity.setCourseName(course.getCourseName());
        entity.setStartDateCourse(course.getStartDateCourse());
        entity.setEndDateCourse(course.getEndDateCourse());
        entity.setCourseSkills(course.getCourseSkills());
        entity.setCourse(course.getCourse());

        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(course.getProfileId());
        entity.setProfile(profileEntity);

        return entity;
    }

}
