package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Injuries")
@Data
@EqualsAndHashCode(callSuper = true)
public class Injury extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: Completar Campos necesarios de Lesiones
    @ManyToOne @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @ManyToOne @JoinColumn(name = "recorded_by")
    private User user;
}
