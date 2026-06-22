package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.training.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
