package io.holeshot.core.domain.model.training;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.UserProfile;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true, exclude = {"routineAssignment", "routineExercise", "userProfile"})
@ToString(exclude = {"routineAssignment", "routineExercise", "userProfile"})
@Entity
@Table(name = "load_records")
@Data
public class LoadRecord extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Min(1)
    @Column(name = "set_number", nullable = false)
    private Integer setNumber;

    @NotNull
    @Min(0)
    @Column(name = "reps_executed", nullable = false)
    private Integer repsExecuted;

    @NotNull
    @Min(0)
    @Column(name = "weight_executed", nullable = false, precision = 6, scale = 2)
    private Double weightExecuted;

    @ManyToOne
    @JoinColumn(name = "routine_assignment_id", nullable = false)
    private RoutineAssignment routineAssignment;

    @ManyToOne
    @JoinColumn(name = "routine_exercise_id", nullable = false)
    private RoutineExercise routineExercise;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;
}
