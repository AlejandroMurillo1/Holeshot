package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.training.RoutineExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineExerciseRepository extends JpaRepository<RoutineExercise, Long> {

}
