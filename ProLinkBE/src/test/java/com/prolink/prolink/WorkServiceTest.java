package com.prolink.prolink;

import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.domain.Work;
import com.prolink.prolink.dto.AddWorkRequest;
import com.prolink.prolink.dto.UpdateWorkRequest;
import com.prolink.prolink.enums.WorkLocation;
import com.prolink.prolink.enums.WorkScheduleType;
import com.prolink.prolink.enums.WorkType;
import com.prolink.prolink.repository.ProfileRepository;
import com.prolink.prolink.repository.WorkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import com.prolink.prolink.service.WorkService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkServiceTest {

    @Mock
    private WorkRepository workRepository;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private WorkService workService;

    @Test
    void addWorkExperience_ShouldCreateWork_WhenProfileExists() {
        Long profileId = 1L;

        AddWorkRequest request = new AddWorkRequest();
        request.setProfileId(profileId);
        request.setWorkInstitutionName("Google");
        request.setStartDateWork(LocalDate.of(2024, 1, 1));
        request.setEndDateWork(LocalDate.of(2024, 12, 1));
        request.setOnGoingWork(false);
        request.setWorkSkills("Backend development");
        request.setWork(WorkType.SOFTWARE_DEVELOPMENT);
        request.setWorkLocation(WorkLocation.REMOTE);
        request.setWorkScheduleType(WorkScheduleType.FULL_TIME);

        Profile profile = new Profile(
                profileId,
                "John Doe",
                "Eindhoven",
                "Software developer student",
                1L
        );

        Work savedWork = new Work(
                1L,
                "Google",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 12, 1),
                false,
                "Backend development",
                WorkType.SOFTWARE_DEVELOPMENT,
                WorkLocation.REMOTE,
                WorkScheduleType.FULL_TIME,
                profileId
        );

        when(profileRepository.findByIdProfile(profileId)).thenReturn(Optional.of(profile));
        when(workRepository.save(any(Work.class))).thenReturn(savedWork);

        Work result = workService.addWorkExperience(profileId, request);

        assertEquals("Google", result.getWorkInstitutionName());
        assertEquals("Backend development", result.getWorkSkills());
        assertEquals(WorkType.SOFTWARE_DEVELOPMENT, result.getWork());

        verify(profileRepository).findByIdProfile(profileId);
        verify(workRepository).save(any(Work.class));
    }

    @Test
    void addWorkExperience_ShouldThrowException_WhenProfileDoesNotExist() {
        Long profileId = 1L;

        AddWorkRequest request = new AddWorkRequest();
        request.setProfileId(profileId);
        request.setWorkInstitutionName("Google");
        request.setStartDateWork(LocalDate.of(2024, 1, 1));
        request.setEndDateWork(LocalDate.of(2024, 12, 1));
        request.setOnGoingWork(false);
        request.setWorkSkills("Backend development");
        request.setWork(WorkType.SOFTWARE_DEVELOPMENT);
        request.setWorkLocation(WorkLocation.REMOTE);
        request.setWorkScheduleType(WorkScheduleType.FULL_TIME);

        when(profileRepository.findByIdProfile(profileId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> workService.addWorkExperience(profileId, request)
        );

        verify(profileRepository).findByIdProfile(profileId);
        verify(workRepository, never()).save(any(Work.class));
    }

    @Test
    void updateWork_ShouldUpdateWork_WhenWorkExists() {
        Long workId = 1L;
        Long profileId = 1L;

        UpdateWorkRequest request = new UpdateWorkRequest();
        request.setWorkInstitutionName("Microsoft");
        request.setStartDateWork(LocalDate.of(2024, 1, 1));
        request.setEndDateWork(LocalDate.of(2025, 1, 1));
        request.setOnGoingWork(false);
        request.setWorkSkills("Cloud development");
        request.setWork(WorkType.CLOUD_COMPUTING);
        request.setWorkLocation(WorkLocation.HYBRID);
        request.setWorkScheduleType(WorkScheduleType.FULL_TIME);

        Work existingWork = new Work(
                workId,
                "Google",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 1),
                false,
                "Backend",
                WorkType.SOFTWARE_DEVELOPMENT,
                WorkLocation.REMOTE,
                WorkScheduleType.FULL_TIME,
                profileId
        );

        Work updatedWork = new Work(
                workId,
                "Microsoft",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2025, 1, 1),
                false,
                "Cloud development",
                WorkType.CLOUD_COMPUTING,
                WorkLocation.HYBRID,
                WorkScheduleType.FULL_TIME,
                profileId
        );

        when(workRepository.findByIdProfileWork(workId)).thenReturn(Optional.of(existingWork));
        when(workRepository.save(any(Work.class))).thenReturn(updatedWork);

        Work result = workService.updateWork(workId, request);

        assertEquals("Microsoft", result.getWorkInstitutionName());
        assertEquals("Cloud development", result.getWorkSkills());
        assertEquals(WorkType.CLOUD_COMPUTING, result.getWork());

        verify(workRepository).findByIdProfileWork(workId);
        verify(workRepository).save(any(Work.class));
    }

    @Test
    void updateWork_ShouldThrowException_WhenWorkDoesNotExist() {
        Long workId = 1L;

        UpdateWorkRequest request = new UpdateWorkRequest();

        when(workRepository.findByIdProfileWork(workId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> workService.updateWork(workId, request)
        );

        verify(workRepository).findByIdProfileWork(workId);
        verify(workRepository, never()).save(any(Work.class));
    }
}