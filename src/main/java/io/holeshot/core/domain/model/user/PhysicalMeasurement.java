package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

//TODO: Completar datos de medidas antropométricas
@Entity
@Table(name = "Physical_Measurements")
@Data
@EqualsAndHashCode(callSuper = true)
public class PhysicalMeasurement extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float weight;
    private Float height;
    private LocalDate registeredOn;

    @ManyToOne @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @ManyToOne @JoinColumn(name = "recorded_by")
    private User user;

}
