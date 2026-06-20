package com.prolink.prolink.domain;

import java.time.LocalDateTime;

public class CompanyFollow {
    private Long idCompanyFollow;

    private Long followerProfileId;
    private String followerProfileName;

    private Long companyProfileId;
    private String companyProfileName;
    private String companyProfileLocation;

    private LocalDateTime createdAt;

    public CompanyFollow(Long idCompanyFollow, Long followerProfileId, String followerProfileName, Long companyProfileId, String companyProfileName, String companyProfileLocation, LocalDateTime createdAt) {
        this.idCompanyFollow = idCompanyFollow;
        this.followerProfileId = followerProfileId;
        this.followerProfileName = followerProfileName;
        this.companyProfileId = companyProfileId;
        this.companyProfileName = companyProfileName;
        this.companyProfileLocation = companyProfileLocation;
        this.createdAt = createdAt;
    }

    public Long getIdCompanyFollow() {return idCompanyFollow;}

    public Long getFollowerProfileId() {return followerProfileId;}

    public String getFollowerProfileName() {return followerProfileName;}

    public Long getCompanyProfileId() {return companyProfileId;}

    public String getCompanyProfileName() {return companyProfileName;}

    public String getCompanyProfileLocation() {return companyProfileLocation;}

    public LocalDateTime getCreatedAt() {return createdAt;}
}
