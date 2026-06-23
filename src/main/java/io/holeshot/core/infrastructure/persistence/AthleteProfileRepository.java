package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.user.AthleteProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteProfileRepository extends JpaRepository<AthleteProfile, Long> {
    List<AthleteProfile> findByUserName(String name);
    List<AthleteProfile> findByUser_LastName(String lastName);
    List<AthleteProfile> findByUciCategory_Name(String category);

}
