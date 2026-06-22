package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.user.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
