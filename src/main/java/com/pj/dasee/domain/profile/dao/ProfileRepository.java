package com.pj.dasee.domain.profile.dao;

import com.pj.dasee.domain.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
