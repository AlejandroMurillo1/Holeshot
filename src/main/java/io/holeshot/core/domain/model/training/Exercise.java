package io.holeshot.core.domain.model.training;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = {"exerciseType", "muscleGroup", "creator", "routineExercises", "oneRmRecords"})
@ToString(exclude = {"exerciseType", "muscleGroup", "creator", "routineExercises", "oneRmRecords"})
@Entity
@Table(name = "exercises")
@Data
public class Exercise extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "is_global", nullable = false)
    private Boolean isGlobal = false;

    @ManyToOne
    @JoinColumn(name = "exercise_type_id", nullable = false)
    private ExerciseType exerciseType;

    @ManyToOne
    @JoinColumn(name = "muscle_group_id", nullable = false)
    private MuscleGroup muscleGroup;

    @ManyToOne
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    private User creator;

    @OneToMany(mappedBy = "exercise")
    private Set<RoutineExercise> routineExercises;

    @OneToMany(mappedBy = "exercise")
    @OrderBy("date DESC")
    private List<OneRmRecord> oneRmRecords;
}
