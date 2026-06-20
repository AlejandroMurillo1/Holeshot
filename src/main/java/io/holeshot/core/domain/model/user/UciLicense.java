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

@EqualsAndHashCode(callSuper = true, exclude = {"userProfile", "uciCategory"})
@ToString(exclude = {"userProfile", "uciCategory"})
@Entity
@Table(name = "uci_licenses")
@Data
public class UciLicense extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "code", nullable = false, length = 50)
    private String code;

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
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "uci_category_id", nullable = false)
    private UciCategory uciCategory;

    @Transient
    public boolean isExpired() {
        return expirationDate != null && expirationDate.isBefore(LocalDate.now());
    }
}
