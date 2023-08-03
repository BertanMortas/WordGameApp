package com.promet.repository;

import com.promet.repository.entity.UserProfile;
import com.promet.repository.entity.enums.EStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile,String> {
    Optional<UserProfile> findByAuthId(Long authId);
    Optional<UserProfile> findByEmail(String email);
}
