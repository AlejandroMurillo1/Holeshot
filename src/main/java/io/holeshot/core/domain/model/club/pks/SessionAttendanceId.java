package io.holeshot.core.domain.model.club.pks;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionAttendanceId implements Serializable {
    private Long clubSessionId;
    private Long enrollmentId;
    private LocalDate date;
}
