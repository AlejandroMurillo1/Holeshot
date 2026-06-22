package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.club.SessionAttendance;
import io.holeshot.core.domain.model.club.pks.SessionAttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionAttendanceRepository extends JpaRepository<SessionAttendance, SessionAttendanceId> {
}
