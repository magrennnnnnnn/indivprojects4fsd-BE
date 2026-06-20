package com.prolink.prolink.repository;

import com.prolink.prolink.domain.CompanyFollow;
import com.prolink.prolink.entity.CompanyFollowEntity;
import com.prolink.prolink.entity.ProfileEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyFollowRepositoryImpl implements CompanyFollowRepository {
    private final CompanyFollowJpaRepo companyFollowJpaRepo;
    private final ProfileJpaRepo profileJpaRepo;

    public CompanyFollowRepositoryImpl(
            CompanyFollowJpaRepo companyFollowJpaRepo,
            ProfileJpaRepo profileJpaRepo
    ) {
        this.companyFollowJpaRepo = companyFollowJpaRepo;
        this.profileJpaRepo = profileJpaRepo;
    }

    @Override
    public Optional<CompanyFollow> findByFollowerProfileIdAndCompanyProfileId(
            Long followerProfileId,
            Long companyProfileId
    ) {
        return companyFollowJpaRepo
                .findByFollowerProfile_IdProfileAndCompanyProfile_IdProfile(
                        followerProfileId,
                        companyProfileId
                )
                .map(this::toDomain);
    }

    @Override
    public CompanyFollow createFollow(Long followerProfileId, Long companyProfileId) {
        ProfileEntity followerProfile = profileJpaRepo.findByIdProfile(followerProfileId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Follower profile was not found"
                ));

        ProfileEntity companyProfile = profileJpaRepo.findByIdProfile(companyProfileId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Company profile was not found"
                ));

        CompanyFollowEntity entity = new CompanyFollowEntity();
        entity.setFollowerProfile(followerProfile);
        entity.setCompanyProfile(companyProfile);
        entity.setCreatedAt(LocalDateTime.now());

        CompanyFollowEntity savedEntity = companyFollowJpaRepo.save(entity);

        return toDomain(savedEntity);
    }

    @Override
    public void deleteByFollowerProfileIdAndCompanyProfileId(
            Long followerProfileId,
            Long companyProfileId
    ) {
        CompanyFollowEntity follow = companyFollowJpaRepo
                .findByFollowerProfile_IdProfileAndCompanyProfile_IdProfile(
                        followerProfileId,
                        companyProfileId
                )
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Follow relation was not found"
                ));

        companyFollowJpaRepo.delete(follow);
    }

    @Override
    public List<CompanyFollow> findByFollowerProfileId(Long followerProfileId) {
        return companyFollowJpaRepo.findByFollowerProfile_IdProfile(followerProfileId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<CompanyFollow> findByCompanyProfileId(Long companyProfileId) {
        return companyFollowJpaRepo.findByCompanyProfile_IdProfile(companyProfileId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public boolean existsByFollowerProfileIdAndCompanyProfileId(
            Long followerProfileId,
            Long companyProfileId
    ) {
        return companyFollowJpaRepo
                .existsByFollowerProfile_IdProfileAndCompanyProfile_IdProfile(
                        followerProfileId,
                        companyProfileId
                );
    }

    private CompanyFollow toDomain(CompanyFollowEntity entity) {
        return new CompanyFollow(
                entity.getIdCompanyFollow(),
                entity.getFollowerProfile().getId(),
                entity.getFollowerProfile().getName(),
                entity.getCompanyProfile().getId(),
                entity.getCompanyProfile().getName(),
                entity.getCompanyProfile().getLocation(),
                entity.getCreatedAt()
        );
    }
}
