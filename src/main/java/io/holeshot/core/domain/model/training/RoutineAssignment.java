package io.holeshot.core.domain.model.training;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.club.Enrollment;
import io.holeshot.core.domain.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"routine", "enrollment", "assignedBy", "loadRecords"})
@ToString(exclude = {"routine", "enrollment", "assignedBy", "loadRecords"})
@Entity
@Table(name = "routine_assignments")
@Data
public class RoutineAssignment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "is_group", nullable = false)
    private Boolean isGroup = false;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "assigned_by", nullable = false)
    private User assignedBy;

    @OneToMany(mappedBy = "routineAssignment")
    private List<LoadRecord> loadRecords;
}
