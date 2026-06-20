package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.training.RoutineAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineAssignmentRepository extends JpaRepository<RoutineAssignment, Long> {
}
