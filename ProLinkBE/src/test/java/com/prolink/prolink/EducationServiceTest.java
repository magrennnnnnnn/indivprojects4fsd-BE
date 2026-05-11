package com.prolink.prolink;
import com.prolink.prolink.service.EducationService;
import com.prolink.prolink.domain.Education;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.AddEducationRequest;
import com.prolink.prolink.dto.UpdateEducationRequest;
import com.prolink.prolink.enums.DegreeType;
import com.prolink.prolink.repository.EducationRepository;
import com.prolink.prolink.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EducationServiceTest {

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private EducationService educationService;

    @Test
    void addEducationalExperience_ShouldCreateEducation_WhenProfileExists() {
        Long profileId = 1L;

        AddEducationRequest request = new AddEducationRequest();
        request.setProfileId(profileId);
        request.setInstitutionName("Fontys");
        request.setStartDateSchool(LocalDate.of(2023, 9, 1));
        request.setEndDateSchool(LocalDate.of(2027, 7, 1));
        request.setOnGoingSchool(false);
        request.setEducationalSkills("Software engineering");
        request.setDegree(DegreeType.BACHELOR);

        Profile profile = new Profile(
                profileId,
                "John Doe",
                "Eindhoven",
                "Software developer student",
                1L
        );

        Education savedEducation = new Education(
                1L,
                "Fontys",
                LocalDate.of(2023, 9, 1),
                LocalDate.of(2027, 7, 1),
                false,
                "Software engineering",
                DegreeType.BACHELOR,
                profileId
        );

        when(profileRepository.findByIdProfile(profileId)).thenReturn(Optional.of(profile));
        when(educationRepository.save(any(Education.class))).thenReturn(savedEducation);

        Education result = educationService.addEducationalExperience(profileId, request);

        assertEquals("Fontys", result.getInstitutionName());
        assertEquals("Software engineering", result.getEducationalSkills());
        assertEquals(DegreeType.BACHELOR, result.getDegree());

        verify(profileRepository).findByIdProfile(profileId);
        verify(educationRepository).save(any(Education.class));
    }

    @Test
    void addEducationalExperience_ShouldThrowException_WhenProfileDoesNotExist() {
        Long profileId = 1L;

        AddEducationRequest request = new AddEducationRequest();
        request.setProfileId(profileId);
        request.setInstitutionName("Fontys");
        request.setStartDateSchool(LocalDate.of(2023, 9, 1));
        request.setEndDateSchool(LocalDate.of(2027, 7, 1));
        request.setOnGoingSchool(false);
        request.setEducationalSkills("Software engineering");
        request.setDegree(DegreeType.BACHELOR);

        when(profileRepository.findByIdProfile(profileId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> educationService.addEducationalExperience(profileId, request)
        );

        verify(profileRepository).findByIdProfile(profileId);
        verify(educationRepository, never()).save(any(Education.class));
    }

    @Test
    void updateEducation_ShouldUpdateEducation_WhenEducationExists() {
        Long educationId = 1L;
        Long profileId = 1L;

        UpdateEducationRequest request = new UpdateEducationRequest();
        request.setInstitutionName("TU Eindhoven");
        request.setStartDateSchool(LocalDate.of(2024, 9, 1));
        request.setEndDateSchool(LocalDate.of(2026, 7, 1));
        request.setOnGoingSchool(false);
        request.setEducationalSkills("AI and data science");
        request.setDegree(DegreeType.MASTER);

        Education existingEducation = new Education(
                educationId,
                "Fontys",
                LocalDate.of(2023, 9, 1),
                LocalDate.of(2027, 7, 1),
                false,
                "Software engineering",
                DegreeType.BACHELOR,
                profileId
        );

        Education updatedEducation = new Education(
                educationId,
                "TU Eindhoven",
                LocalDate.of(2024, 9, 1),
                LocalDate.of(2026, 7, 1),
                false,
                "AI and data science",
                DegreeType.MASTER,
                profileId
        );

        when(educationRepository.findByIdProfileEducation(educationId)).thenReturn(Optional.of(existingEducation));
        when(educationRepository.save(any(Education.class))).thenReturn(updatedEducation);

        Education result = educationService.updateEducation(educationId, request);

        assertEquals("TU Eindhoven", result.getInstitutionName());
        assertEquals("AI and data science", result.getEducationalSkills());
        assertEquals(DegreeType.MASTER, result.getDegree());

        verify(educationRepository).findByIdProfileEducation(educationId);
        verify(educationRepository).save(any(Education.class));
    }

    @Test
    void updateEducation_ShouldThrowException_WhenEducationDoesNotExist() {
        Long educationId = 1L;

        UpdateEducationRequest request = new UpdateEducationRequest();

        when(educationRepository.findByIdProfileEducation(educationId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> educationService.updateEducation(educationId, request)
        );

        verify(educationRepository).findByIdProfileEducation(educationId);
        verify(educationRepository, never()).save(any(Education.class));
    }
}