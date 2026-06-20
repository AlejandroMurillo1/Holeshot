package io.holeshot.core.domain.model.test;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(exclude = "test")
@ToString(exclude = "test")
@Entity
@Table(name = "time_trials")
@Data
public class TimeTrial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "total_time", nullable = false, precision = 6, scale = 3)
    private Double totalTime;

    @Column(name = "gate_time", precision = 6, scale = 3)
    private Double gateTime;

    @Column(name = "first_straight_time", precision = 6, scale = 3)
    private Double firstStraightTime;

    @Column(name = "sector_2_time", precision = 6, scale = 3)
    private Double sector2Time;

    @Column(name = "sector_3_time", precision = 6, scale = 3)
    private Double sector3Time;

    @Size(max = 20)
    @Column(name = "track_condition", length = 20)
    private String trackCondition;

    @OneToOne
    @JoinColumn(name = "test_id", unique = true, nullable = false)
    private Test test;
}
