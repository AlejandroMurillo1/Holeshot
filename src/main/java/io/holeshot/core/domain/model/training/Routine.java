package io.holeshot.core.domain.model.training;

import io.holeshot.core.domain.model.club.Club;
import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"club", "creator", "parentRoutine", "routineExercises", "assignments", "childVersions"})
@ToString(exclude = {"club", "creator", "parentRoutine", "routineExercises", "assignments", "childVersions"})
@SQLRestriction(value = "deleted_at IS NULL")
@Entity
@Table(name = "routines")
@Data
public class Routine extends Auditable {
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
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @NotNull
    @Column(name = "version", nullable = false)
    private Integer version = 1;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false, insertable = false, updatable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "parent_routine_id")
    private Routine parentRoutine;

    @OneToMany(mappedBy = "routine")
    @OrderBy("order ASC")
    private List<RoutineExercise> routineExercises;

    @OneToMany(mappedBy = "routine")
    @OrderBy("startDate DESC")
    private List<RoutineAssignment> assignments;

    @OneToMany(mappedBy = "parentRoutine")
    @OrderBy("version ASC")
    private List<Routine> childVersions;

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
