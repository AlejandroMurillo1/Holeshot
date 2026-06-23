package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.club.Enrollment;
import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.training.Exercise;
import io.holeshot.core.domain.model.training.Routine;
import io.holeshot.core.domain.model.training.RoutineAssignment;
import io.holeshot.core.domain.model.training.OneRmRecord;
import io.holeshot.core.domain.model.test.Test;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = {"athleteProfile", "enrollments", "physicalMeasurements", "injuriesRecorded", "oneRmRecordsRegistered", "testsRecorded", "exercisesCreated", "routinesCreated", "routineAssignmentsAssigned"})
@ToString(exclude = {"athleteProfile", "enrollments", "physicalMeasurements", "injuriesRecorded", "oneRmRecordsRegistered", "testsRecorded", "exercisesCreated", "routinesCreated", "routineAssignmentsAssigned"})
@SQLRestriction(value = "deleted_at IS NULL")
@Entity
@Table(name = "users")
@Data
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @NotBlank
    @Size(max = 80)
    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 150)
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Size(max = 30)
    @Column(name = "document_number", length = 30)
    private String documentNumber;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Relationships
    @OneToOne(mappedBy = "user")
    private AthleteProfile athleteProfile;

    @OneToMany(mappedBy = "user")
    @OrderBy("startDate ASC")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "recordedBy")
    private List<PhysicalMeasurement> physicalMeasurements;

    @OneToMany(mappedBy = "recordedBy")
    private List<Injury> injuriesRecorded;

    @OneToMany(mappedBy = "recordedBy")
    private List<OneRmRecord> oneRmRecordsRegistered;

    @OneToMany(mappedBy = "recordedBy")
    private List<Test> testsRecorded;

    @OneToMany(mappedBy = "creator")
    private Set<Exercise> exercisesCreated;

    @OneToMany(mappedBy = "creator")
    private List<Routine> routinesCreated;

    @OneToMany(mappedBy = "assignedBy")
    private List<RoutineAssignment> routineAssignmentsAssigned;

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
