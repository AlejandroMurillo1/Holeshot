package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.training.Routine;
import io.holeshot.core.domain.model.test.Test;
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
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = {"enrollments", "clubSessions", "routines", "tests"})
@ToString(exclude = {"enrollments", "clubSessions", "routines", "tests"})
@SQLRestriction(value = "deleted_at IS NULL")
@Entity
@Table(name = "clubs")
@Data
public class Club extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Size(max = 500)
    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @NotNull
    @Column(name = "monthly_payment", nullable = false, precision = 10, scale = 2)
    private Double monthlyPayment;

    @Size(max = 80)
    @Column(name = "city", length = 80)
    private String city;

    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @Size(max = 200)
    @Column(name = "address", length = 200)
    private String address;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @NotNull
    @Column(name = "is_department_team", nullable = false)
    private Boolean isDepartmentTeam = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "club")
    @OrderBy("startDate ASC")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "club")
    private Set<ClubSession> clubSessions;

    @OneToMany(mappedBy = "club")
    private List<Routine> routines;

    @OneToMany(mappedBy = "club")
    private List<Test> tests;

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
