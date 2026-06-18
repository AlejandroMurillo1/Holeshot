package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "UCI_Licenses")
@Data
public class UciLicense extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private LocalDate expeditionDate;
    private LocalDate expirationDate;
    private String documentUrl;
    private Boolean is_expired;

    //UserProfile, UserCategory
    @OneToOne @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;
}
