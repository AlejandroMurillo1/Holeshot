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

@EqualsAndHashCode(callSuper = true, exclude = "athleteProfile")
@ToString(exclude = "athleteProfile")
@Entity
@Table(name = "insurances")
@Data
public class Insurance extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(name = "code", nullable = false, length = 80)
    private String code;

    @NotBlank
    @Size(max = 100)
    @Column(name = "entity", nullable = false, length = 100)
    private String entity;

    @NotNull
    @Column(name = "expedition_date", nullable = false)
    private LocalDate expeditionDate;

    @NotNull
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Size(max = 500)
    @Column(name = "document_url", length = 500)
    private String documentUrl;

    @OneToOne
    @JoinColumn(name = "user_profile_id", unique = true, nullable = false)
    private AthleteProfile athleteProfile;
}
