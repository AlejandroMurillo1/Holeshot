package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.User;
import io.holeshot.core.domain.model.training.RoutineAssignment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"sessionAttendances", "monthlyPayments", "routineAssignments", "user", "club", "role"})
@ToString(exclude = {"sessionAttendances", "monthlyPayments", "routineAssignments", "user", "club", "role"})
@SQLRestriction(value = "deleted_at IS NULL")
@Entity
@Table(name = "enrollments", uniqueConstraints =
        @UniqueConstraint(columnNames = {
                "user_id",
                "club_id",
                "role_id"
        })
)
@Data
public class Enrollment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "enrollment")
    @OrderBy("id ASC")
    private List<SessionAttendance> sessionAttendances;

    @OneToMany(mappedBy = "enrollment")
    @OrderBy("date DESC")
    private List<MonthlyPayment> monthlyPayments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "enrollment")
    @OrderBy("startDate DESC")
    private List<RoutineAssignment> routineAssignments;

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
