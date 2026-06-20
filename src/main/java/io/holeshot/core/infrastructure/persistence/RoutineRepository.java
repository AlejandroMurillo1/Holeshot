package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.training.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine,Long> {

}
