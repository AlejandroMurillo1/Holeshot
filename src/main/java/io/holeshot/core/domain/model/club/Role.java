package io.holeshot.core.domain.model.club;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@EqualsAndHashCode(exclude = "rolePermissions")
@ToString(exclude = "rolePermissions")
@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<RolePermission> rolePermissions;
}
