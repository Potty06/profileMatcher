package com.gameloft.profileMatcher.repository;

import com.gameloft.profileMatcher.model.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ProfileRepository.java
@Repository
public interface ProfileRepository extends JpaRepository<PlayerProfile, String> {

}
