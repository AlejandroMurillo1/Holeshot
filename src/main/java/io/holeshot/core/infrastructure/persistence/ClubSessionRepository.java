package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.club.ClubSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubSessionRepository extends JpaRepository<ClubSession, Long> {
}
