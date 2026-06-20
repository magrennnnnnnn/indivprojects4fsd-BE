package com.prolink.prolink.repository;

import com.prolink.prolink.domain.CompanyFollow;

import java.util.List;
import java.util.Optional;

public interface CompanyFollowRepository {
    Optional<CompanyFollow> findByFollowerProfileIdAndCompanyProfileId(
            Long followerProfileId,
            Long companyProfileId
    );

    CompanyFollow createFollow(Long followerProfileId, Long companyProfileId);

    void deleteByFollowerProfileIdAndCompanyProfileId(
            Long followerProfileId,
            Long companyProfileId
    );

    List<CompanyFollow> findByFollowerProfileId(Long followerProfileId);

    List<CompanyFollow> findByCompanyProfileId(Long companyProfileId);

    boolean existsByFollowerProfileIdAndCompanyProfileId(
            Long followerProfileId,
            Long companyProfileId
    );
}
