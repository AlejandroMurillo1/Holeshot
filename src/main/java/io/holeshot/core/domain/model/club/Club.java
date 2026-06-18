package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Clubs")
@Data
@EqualsAndHashCode(callSuper = true)
public class Club extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String logoUrl;
    private Float monthlyPayment;
    private String city;
    private String email;
    private String address;
    private String phone;
    private Boolean isDepartmentTeam;
    @Column(nullable = true) private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "club")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "club")
    private List<ClubSession> sessions;
}
