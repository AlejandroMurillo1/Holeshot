package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Enrollments",  uniqueConstraints =
        @UniqueConstraint(columnNames = {
                "user_id",
                "club_id",
                "role_id"
        })
)
@Data
@EqualsAndHashCode(callSuper = true)
public class Enrollment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "enrollment")
    private List<SessionAttendance> sessionAttendances;

    @OneToMany(mappedBy = "enrollment")
    private List<MonthlyPayment> monthlyPayments;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne @JoinColumn(name = "role_id")
    private Role role;

    //TODO: Training relations.
}
