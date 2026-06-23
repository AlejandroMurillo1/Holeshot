package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true, exclude = {"athleteProfile", "recordedBy"})
@ToString(exclude = {"athleteProfile", "recordedBy"})
@Entity
@Table(name = "physical_measurements")
@Data
public class PhysicalMeasurement extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "weight", nullable = false, precision = 5, scale = 2)
    private Double weight;

    @NotNull
    @Column(name = "height", nullable = false, precision = 5, scale = 2)
    private Double height;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private AthleteProfile athleteProfile;

    @ManyToOne
    @JoinColumn(name = "recorded_by", nullable = false)
    private User recordedBy;
}
