package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.test.TimeTrial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTrialRepository extends JpaRepository<TimeTrial, Long> {
}
