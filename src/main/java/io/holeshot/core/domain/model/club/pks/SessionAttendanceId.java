package io.holeshot.core.domain.model.club.pks;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class SessionAttendanceId {
    private Long clubSessionId;
    private Long enrollmentId;
}
