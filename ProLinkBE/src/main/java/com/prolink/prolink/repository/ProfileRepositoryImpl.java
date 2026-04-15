package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfileJpaRepo profileJpaRepo;

    public ProfileRepositoryImpl(ProfileJpaRepo profileJpaRepo) {
        this.profileJpaRepo = profileJpaRepo;
    }

    @Override
    public Optional<Profile> findByIdProfile(Long idProfile) {
        return profileJpaRepo.findByIdProfile(idProfile)
                .map(this::toDomain);
    }

    @Override
    public Optional<Profile> findByUserId(Long userId) {
        return profileJpaRepo.findByUserEntity_Id(userId)
                .map(this::toDomain);
    }

    @Override
    public Profile save(Profile profile) {
        ProfileEntity entity = toEntity(profile);
        ProfileEntity saved = profileJpaRepo.save(entity);
        return toDomain(saved);
    }

    private Profile toDomain(ProfileEntity profileEntity) {
        return new Profile(
                profileEntity.getId(),
                profileEntity.getName(),
                profileEntity.getLocation(),
                profileEntity.getPersonalDetails(),
                profileEntity.getUser().getId()
        );
    }

    private ProfileEntity toEntity(Profile profile) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(profile.getIdProfile());
        profileEntity.setName(profile.getName());
        profileEntity.setLocation(profile.getLocation());
        profileEntity.setPersonalDetails(profile.getPersonalDetails());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(profile.getUserId());
        profileEntity.setUser(userEntity);
        return profileEntity;
    }
}