package io.holeshot.core.domain.model.user;

import io.holeshot.core.domain.model.club.Enrollment;
import io.holeshot.core.domain.model.shared.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SQLRestriction(value = "deleted_at IS NULL")
@Entity
@Table(name = "Users")
@Data
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String phoneNumber;
    private String documentNumber;
    private LocalDateTime deletedAt;

    //UserProfile y Enrollments
    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<Enrollment> enrollments;

    public void softDelete(){
        this.deletedAt = LocalDateTime.now();
    }

}
