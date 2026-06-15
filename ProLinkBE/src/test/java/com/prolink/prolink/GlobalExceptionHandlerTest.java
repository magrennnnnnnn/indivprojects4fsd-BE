package com.prolink.prolink;

import com.prolink.prolink.exceptionhandler.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleEmailAlreadyExists_ShouldReturnConflict() {
        EmailAlreadyExistsException exception =
                new EmailAlreadyExistsException("Email already exists");

        ResponseEntity<String> response =
                handler.handleEmailAlreadyExists(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email already exists", response.getBody());
    }

    @Test
    void handlePasswordIsIncorrect_ShouldReturnUnauthorized() {
        PasswordIsIncorrectException exception =
                new PasswordIsIncorrectException("Invalid email or password");

        ResponseEntity<String> response =
                handler.handlePasswordIsIncorrect(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid email or password", response.getBody());
    }

    @Test
    void handleInvalidEmail_ShouldReturnBadRequest() {
        InvalidEmailException exception =
                new InvalidEmailException("Invalid email");

        ResponseEntity<String> response =
                handler.handleInvalidEmail(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid email", response.getBody());
    }

    @Test
    void handleInvalidPassword_ShouldReturnBadRequest() {
        InvalidPasswordException exception =
                new InvalidPasswordException("Invalid password");

        ResponseEntity<String> response =
                handler.handleInvalidPassword(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid password", response.getBody());
    }

    @Test
    void handleInvalidProfileName_ShouldReturnBadRequest() {
        InvalidProfileNameException exception =
                new InvalidProfileNameException("Invalid profile name");

        ResponseEntity<String> response =
                handler.handleInvalidProfileName(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid profile name", response.getBody());
    }

    @Test
    void handleInvalidProfileLocation_ShouldReturnBadRequest() {
        InvalidProfileLocationException exception =
                new InvalidProfileLocationException("Invalid profile location");

        ResponseEntity<String> response =
                handler.handleInvalidProfileLocation(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid profile location", response.getBody());
    }

    @Test
    void handleInvalidPersonalDetails_ShouldReturnBadRequest() {
        InvalidProfilePersonalDetailsException exception =
                new InvalidProfilePersonalDetailsException("Invalid personal details");

        ResponseEntity<String> response =
                handler.handleInvalidPersonalDetails(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid personal details", response.getBody());
    }

    @Test
    void handleInvalidWorkEndDate_ShouldReturnBadRequest() {
        InvalidWorkEndDateException exception =
                new InvalidWorkEndDateException("Invalid work end date");

        ResponseEntity<String> response =
                handler.handleInvalidWorkEndDate(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid work end date", response.getBody());
    }

    @Test
    void handleInvalidWorkInstitutionName_ShouldReturnBadRequest() {
        InvalidWorkInstitutionNameException exception =
                new InvalidWorkInstitutionNameException("Invalid work institution name");

        ResponseEntity<String> response =
                handler.handleInvalidWorkInstitutionName(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid work institution name", response.getBody());
    }

    @Test
    void handleInvalidWorkSkills_ShouldReturnBadRequest() {
        InvalidWorkSkillsException exception =
                new InvalidWorkSkillsException("Invalid work skills");

        ResponseEntity<String> response =
                handler.handleInvalidWorkSkills(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid work skills", response.getBody());
    }

    @Test
    void handleInvalidWorkStartDate_ShouldReturnBadRequest() {
        InvalidWorkStartDateException exception =
                new InvalidWorkStartDateException("Invalid work start date");

        ResponseEntity<String> response =
                handler.handleInvalidWorkStartDate(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid work start date", response.getBody());
    }

    @Test
    void handleInvalidPostTitle_ShouldReturnBadRequest() {
        InvalidPostTitleException exception =
                new InvalidPostTitleException("Invalid post title");

        ResponseEntity<String> response =
                handler.handleInvalidPostTitle(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid post title", response.getBody());
    }

    @Test
    void handleInvalidPostText_ShouldReturnBadRequest() {
        InvalidPostTextException exception =
                new InvalidPostTextException("Invalid post text");

        ResponseEntity<String> response =
                handler.handleInvalidPostText(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid post text", response.getBody());
    }

    @Test
    void handleInvalidCourseEndDate_ShouldReturnBadRequest() {
        InvalidCourseEndDateException exception =
                new InvalidCourseEndDateException("Invalid course end date");

        ResponseEntity<String> response =
                handler.handleInvalidCourseEndDate(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid course end date", response.getBody());
    }

    @Test
    void handleInvalidCourseName_ShouldReturnBadRequest() {
        InvalidCourseNameException exception =
                new InvalidCourseNameException("Invalid course name");

        ResponseEntity<String> response =
                handler.handleInvalidCourseName(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid course name", response.getBody());
    }

    @Test
    void handleInvalidCourseSkills_ShouldReturnBadRequest() {
        InvalidCourseSkillsException exception =
                new InvalidCourseSkillsException("Invalid course skills");

        ResponseEntity<String> response =
                handler.handleInvalidCourseSkills(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid course skills", response.getBody());
    }

    @Test
    void handleInvalidCourseStartDate_ShouldReturnBadRequest() {
        InvalidCourseStartDateException exception =
                new InvalidCourseStartDateException("Invalid course start date");

        ResponseEntity<String> response =
                handler.handleInvalidCourseStartDate(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid course start date", response.getBody());
    }

    @Test
    void handleInvalidEducationEndDate_ShouldReturnBadRequest() {
        InvalidEducationEndDateException exception =
                new InvalidEducationEndDateException("Invalid education end date");

        ResponseEntity<String> response =
                handler.handleInvalidEducationEndDate(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid education end date", response.getBody());
    }

    @Test
    void handleInvalidEducationInstitutionName_ShouldReturnBadRequest() {
        InvalidEducationInstitutionNameException exception =
                new InvalidEducationInstitutionNameException("Invalid education institution name");

        ResponseEntity<String> response =
                handler.handleInvalidEducationInstitutionName(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid education institution name", response.getBody());
    }

    @Test
    void handleInvalidEducationSkills_ShouldReturnBadRequest() {
        InvalidEducationSkillsException exception =
                new InvalidEducationSkillsException("Invalid education skills");

        ResponseEntity<String> response =
                handler.handleInvalidEducationSkills(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid education skills", response.getBody());
    }

    @Test
    void handleInvalidEducationStartDate_ShouldReturnBadRequest() {
        InvalidEducationStartDateException exception =
                new InvalidEducationStartDateException("Invalid education start date");

        ResponseEntity<String> response =
                handler.handleInvalidEducationStartDate(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid education start date", response.getBody());
    }
}