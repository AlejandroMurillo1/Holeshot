package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.club.pks.SessionAttendanceId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Session_Attendances")
@Data
public class SessionAttendance {
    @EmbeddedId
    private SessionAttendanceId id;
    private Boolean attended;

    @ManyToOne @MapsId("clubSessionId")
    private ClubSession clubSession;

    @ManyToOne @MapsId("enrollmentId")
    private Enrollment enrollment;
}
