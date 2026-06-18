package io.holeshot.core.domain.model.club;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Club_Sessions")
@Data
public class ClubSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dayOfWeek;
    private LocalTime from;
    private LocalTime to;

    @ManyToOne @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "clubSession")
    private List<SessionAttendance> attendances;
}
