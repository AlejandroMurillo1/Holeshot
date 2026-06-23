package io.holeshot.core.domain.model.user.bicycles;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.AthleteProfile;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true, exclude = {"athleteProfile", "frameBrand", "frameSize", "tireBrand", "tireSize"})
@ToString(exclude = {"athleteProfile", "frameBrand", "frameSize", "tireBrand", "tireSize"})
@Entity
@Table(name = "bicycles")
@Data
public class Bicycle extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Column(name = "cranks")
    private Double cranks;

    @Min(30) @Max(60)
    @Column(name = "gear")
    private Double gear;

    @Positive
    @Column(name = "rear_gear")
    private Integer rearGear;

    @Column(name = "handlebar_inches", precision = 4, scale = 2)
    private Double handlebarInches;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private AthleteProfile athleteProfile;

    @ManyToOne
    @JoinColumn(name = "frame_brand_id")
    private FrameBrand frameBrand;

    @ManyToOne
    @JoinColumn(name = "frame_size_id")
    private FrameSize frameSize;

    @ManyToOne
    @JoinColumn(name = "tire_brand_id")
    private TireBrand tireBrand;

    @ManyToOne
    @JoinColumn(name = "tire_size_id")
    private TireSize tireSize;
}
