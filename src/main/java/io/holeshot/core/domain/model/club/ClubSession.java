package io.holeshot.core.domain.model.club;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@EqualsAndHashCode(exclude = {"club", "attendances"})
@ToString(exclude = {"club", "attendances"})
@Entity
@Table(name = "club_sessions")
@Data
public class ClubSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(7)
    @Column(name = "day_of_week", nullable = false)
    private Integer dayOfWeek;

    @NotNull
    @Column(name = "from_time", nullable = false)
    private LocalTime from;

    @NotNull
    @Column(name = "to_time", nullable = false)
    private LocalTime to;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @OneToMany(mappedBy = "clubSession")
    private List<SessionAttendance> attendances;
}
