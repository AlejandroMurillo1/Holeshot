package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true, exclude = {"userProfile", "recordedBy"})
@ToString(exclude = {"userProfile", "recordedBy"})
@Entity
@Table(name = "injuries")
@Data
public class Injury extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(name = "type", nullable = false, length = 80)
    private String type;

    @NotBlank
    @Size(max = 60)
    @Column(name = "body_area", nullable = false, length = 60)
    private String bodyArea;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "estimated_end_date")
    private LocalDate estimatedEndDate;

    @Column(name = "actual_end_date")
    private LocalDate actualEndDate;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Lob
    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "recorded_by", nullable = false)
    private User recordedBy;
}
