package io.holeshot.core.domain.model.user.bicycles;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.UserProfile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Bicycles")
@Data
@EqualsAndHashCode(callSuper = true)
public class Bicycle extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cranks;
    private Float gear;
    private Integer rearGear;
    private Float handlebarInches;

    @ManyToOne @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @ManyToOne @JoinColumn(name = "frame_brand_id")
    private FrameBrand frameBrand;

    @ManyToOne @JoinColumn(name = "frame_size_id")
    private FrameSize frameSize;

    @ManyToOne @JoinColumn(name = "tire_brand_id")
    private TireBrand tireBrand;

    @ManyToOne @JoinColumn(name = "tire_size_id")
    private TireSize tireSize;

}
