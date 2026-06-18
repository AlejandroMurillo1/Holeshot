package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.shared.Auditable;
import io.holeshot.core.domain.model.user.bicycles.Bicycle;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.processing.Generated;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "User_Profiles")
@Data
public class UserProfile extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private DocumentType documentType;
    private String documentUrl;
    private String photoUrl;
    private Float weight;
    private Float height;

    //User, UciCategory, Insurance, Bicycles,

    @OneToOne(mappedBy = "userProfile")
    private UciLicense uciLicense;

    @OneToOne(mappedBy = "userProfile")
    private Insurance insurance;

    @OneToMany(mappedBy = "userProfile")
    private List<Bicycle> bicycles;

    @OneToMany(mappedBy = "userProfile")
    private List<PhysicalMeasurement> physicalMeasurements;

    @OneToMany(mappedBy = "userProfile")
    private List<Injury> injuries;

    @OneToOne @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne @JoinColumn(name = "uci_category_id")
    private UciCategory category;


}
