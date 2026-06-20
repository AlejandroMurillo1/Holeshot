package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.club.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
