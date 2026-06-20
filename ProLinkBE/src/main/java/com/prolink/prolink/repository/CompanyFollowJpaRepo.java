package com.prolink.prolink.repository;

import com.prolink.prolink.entity.CompanyFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyFollowJpaRepo extends JpaRepository<CompanyFollowEntity, Long> {
    boolean existsByFollowerProfile_IdProfileAndCompanyProfile_IdProfile(
            Long followerProfileId,
            Long companyProfileId
    );

    Optional<CompanyFollowEntity> findByFollowerProfile_IdProfileAndCompanyProfile_IdProfile(
            Long followerProfileId,
            Long companyProfileId
    );

    List<CompanyFollowEntity> findByFollowerProfile_IdProfile(Long followerProfileId);

    List<CompanyFollowEntity> findByCompanyProfile_IdProfile(Long companyProfileId);
}
