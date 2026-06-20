package io.holeshot.core.domain.model.training;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"routine", "exercise", "loadRecords"})
@ToString(exclude = {"routine", "exercise", "loadRecords"})
@Entity
@Table(name = "routine_exercises")
@Data
public class RoutineExercise extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(7)
    @Column(name = "day_of_week", nullable = false)
    private Integer dayOfWeek;

    @NotNull
    @Min(1)
    @Column(name = "sets", nullable = false)
    private Integer sets;

    @NotNull
    @Min(1)
    @Column(name = "repetitions", nullable = false)
    private Integer repetitions;

    @Column(name = "suggested_weight", precision = 6, scale = 2)
    private Double suggestedWeight;

    @NotNull
    @Column(name = "execution_order", nullable = false)
    private Integer order;

    @Lob
    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @OneToMany(mappedBy = "routineExercise")
    private List<LoadRecord> loadRecords;
}
