package io.holeshot.core.domain.model.training;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.User;
import io.holeshot.core.domain.model.user.UserProfile;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true, exclude = {"userProfile", "exercise", "recordedBy"})
@ToString(exclude = {"userProfile", "exercise", "recordedBy"})
@Entity
@Table(name = "one_rm_records")
@Data
public class OneRmRecord extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(0)
    @Column(name = "weight", nullable = false, precision = 6, scale = 2)
    private Double weight;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "recorded_by", nullable = false)
    private User recordedBy;
}
