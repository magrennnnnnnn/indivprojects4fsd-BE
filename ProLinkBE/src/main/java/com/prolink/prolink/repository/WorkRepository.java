package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Work;

import java.util.List;
import java.util.Optional;

public interface WorkRepository {
    Optional<Work> findByIdProfileWork(Long idProfileWork);
    List<Work> findByProfileId(Long profileId);
    Work save(Work work);
}
