package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Work;
import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.entity.WorkExperience;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkRepositoryImpl implements WorkRepository {
    private final WorkJpaRepo workJpaRepo;

    public WorkRepositoryImpl(WorkJpaRepo workJpaRepo) {
        this.workJpaRepo = workJpaRepo;
    }

    @Override
    public Optional<Work> findByIdProfileWork(Long idProfileWork) {
        return workJpaRepo.findByIdProfileWork(idProfileWork)
                .map(this::toDomain);
    }

    @Override
    public List<Work> findByProfileId(Long profileId) {
        return workJpaRepo.findByProfileEntity_IdProfile(profileId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Work save(Work work) {
        WorkExperience entity = toEntity(work);
        WorkExperience saved = workJpaRepo.save(entity);
        return toDomain(saved);
    }

    private Work toDomain(WorkExperience entity) {
        return new Work(
                entity.getIdProfileWork(),
                entity.getWorkInstitutionName(),
                entity.getStartDateWork(),
                entity.getEndDateWork(),
                entity.isOnGoingWork(),
                entity.getWorkSkills(),
                entity.getWork(),
                entity.getWorkLocation(),
                entity.getWorkScheduleType(),
                entity.getProfile().getId()
        );
    }

    private WorkExperience toEntity(Work work) {
        WorkExperience entity = new WorkExperience();
        entity.setIdProfileWork(work.getIdProfileWork());
        entity.setWorkInstitutionName(work.getWorkInstitutionName());
        entity.setStartDateWork(work.getStartDateWork());
        entity.setEndDateWork(work.getEndDateWork());
        entity.setOnGoingWork(work.isOnGoingWork());
        entity.setWorkSkills(work.getWorkSkills());
        entity.setWork(work.getWork());
        entity.setWorkLocation(work.getWorkLocation());
        entity.setWorkScheduleType(work.getWorkScheduleType());

        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(work.getProfileId());
        entity.setProfile(profileEntity);

        return entity;
    }

}
