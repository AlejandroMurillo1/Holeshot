package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.bicycles.Bicycle;
import io.holeshot.core.domain.model.training.LoadRecord;
import io.holeshot.core.domain.model.training.OneRmRecord;
import io.holeshot.core.domain.model.test.Test;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"uciLicense", "insurance", "bicycles", "physicalMeasurements", "injuries", "loadRecords", "oneRmRecords", "tests", "user"})
@ToString(exclude = {"uciLicense", "insurance", "bicycles", "physicalMeasurements", "injuries", "loadRecords", "oneRmRecords", "tests", "user"})
@Entity
@Table(name = "user_profiles")
@Data
public class AthleteProfile extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 500)
    @Column(name = "document_url", length = 500)
    private String documentUrl;

    @Size(max = 500)
    @Column(name = "photo_url", length = 500)
    private String photoUrl;

    @Column(name = "weight", precision = 5, scale = 2)
    private Double weight;

    @Column(name = "height", precision = 5, scale = 2)
    private Double height;

    @OneToOne(mappedBy = "userProfile")
    private UciLicense uciLicense;

    @OneToOne(mappedBy = "userProfile")
    private Insurance insurance;

    @OneToMany(mappedBy = "userProfile")
    @OrderBy("id ASC")
    private List<Bicycle> bicycles;

    @OneToMany(mappedBy = "userProfile")
    @OrderBy("date DESC")
    private List<PhysicalMeasurement> physicalMeasurements;

    @OneToMany(mappedBy = "userProfile")
    @OrderBy("startDate DESC")
    private List<Injury> injuries;

    @OneToMany(mappedBy = "userProfile")
    @OrderBy("date DESC")
    private List<LoadRecord> loadRecords;

    @OneToMany(mappedBy = "userProfile")
    @OrderBy("date DESC")
    private List<OneRmRecord> oneRmRecords;

    @OneToMany(mappedBy = "userProfile")
    @OrderBy("date DESC")
    private List<Test> tests;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "uci_category_id")
    private UciCategory uciCategory;
}
