package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.training.OneRmRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneRmRepository extends JpaRepository<OneRmRecord, Long> {
}
