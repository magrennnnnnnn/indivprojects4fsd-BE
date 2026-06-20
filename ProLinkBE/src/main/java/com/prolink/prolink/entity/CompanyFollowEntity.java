package com.prolink.prolink.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "company_follows",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"follower_profile_id", "company_profile_id"})
        }
)
public class CompanyFollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompanyFollow;

    @ManyToOne
    @JoinColumn(name = "follower_profile_id", nullable = false)
    private ProfileEntity followerProfile;

    @ManyToOne
    @JoinColumn(name = "company_profile_id", nullable = false)
    private ProfileEntity companyProfile;

    private LocalDateTime createdAt;

    public CompanyFollowEntity() {/**/}

    public Long getIdCompanyFollow() {
        return idCompanyFollow;
    }

    public ProfileEntity getFollowerProfile() {
        return followerProfile;
    }

    public ProfileEntity getCompanyProfile() {
        return companyProfile;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setIdCompanyFollow(Long idCompanyFollow) {
        this.idCompanyFollow = idCompanyFollow;
    }

    public void setFollowerProfile(ProfileEntity followerProfile) {
        this.followerProfile = followerProfile;
    }

    public void setCompanyProfile(ProfileEntity companyProfile) {
        this.companyProfile = companyProfile;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
