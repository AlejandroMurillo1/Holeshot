package io.holeshot.core.domain.model.user.bicycles;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.UserProfile;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true, exclude = {"userProfile", "frameBrand", "frameSize", "tireBrand", "tireSize"})
@ToString(exclude = {"userProfile", "frameBrand", "frameSize", "tireBrand", "tireSize"})
@Entity
@Table(name = "bicycles")
@Data
public class Bicycle extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 80)
    @Column(name = "cranks", length = 80)
    private String cranks;

    @Size(max = 20)
    @Column(name = "gear", length = 20)
    private String gear;

    @Size(max = 20)
    @Column(name = "rear_gear", length = 20)
    private String rearGear;

    @Column(name = "handlebar_inches", precision = 4, scale = 2)
    private Double handlebarInches;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

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
