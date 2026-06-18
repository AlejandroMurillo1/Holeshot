package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "Insurances")
@Data
@EqualsAndHashCode(callSuper = true)
public class Insurance extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String healthEntity;
    private LocalDate expeditionDate;
    private LocalDate expirationDate;
    private String documentUrl;

    //UserProfile
    @OneToOne @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;
}
