package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    List<UserProfile> findByUserName(String name);
    List<UserProfile> findByUser_LastName(String lastName);
    List<UserProfile> findByUciCategory_Name(String category);

}
