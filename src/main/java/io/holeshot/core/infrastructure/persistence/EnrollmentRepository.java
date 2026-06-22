package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.club.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUserId(Long userId);
    List<Enrollment> findByClubIdAndIsActiveTrue(Long clubId);
}
