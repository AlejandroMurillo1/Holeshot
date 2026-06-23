package io.holeshot.core.domain.model.test;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.club.Club;
import io.holeshot.core.domain.model.user.User;
import io.holeshot.core.domain.model.user.AthleteProfile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true, exclude = {"athleteProfile", "club", "testType", "recordedBy", "timeTrial"})
@ToString(exclude = {"athleteProfile", "club", "testType", "recordedBy", "timeTrial"})
@Entity
@Table(name = "tests")
@Data
public class Test extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Lob
    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private AthleteProfile athleteProfile;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @ManyToOne
    @JoinColumn(name = "test_type_id", nullable = false)
    private TestType testType;

    @ManyToOne
    @JoinColumn(name = "recorded_by", nullable = false)
    private User recordedBy;

    @OneToOne(mappedBy = "test")
    private TimeTrial timeTrial;
}
