package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.training.LoadRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRecordRepository extends JpaRepository<LoadRecord, Long> {
}
