package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.club.pks.SessionAttendanceId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(exclude = {"clubSession", "enrollment"})
@ToString(exclude = {"clubSession", "enrollment"})
@Entity
@Table(name = "session_attendances")
@Data
public class SessionAttendance {
    @EmbeddedId
    private SessionAttendanceId id;

    @Column(name = "attended")
    private Boolean attended;

    @ManyToOne
    @MapsId("clubSessionId")
    @JoinColumn(name = "club_session_id", nullable = false)
    private ClubSession clubSession;

    @ManyToOne
    @MapsId("enrollmentId")
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;
}
