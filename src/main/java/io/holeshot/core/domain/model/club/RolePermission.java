package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.club.pks.RolePermissionId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(exclude = {"role", "permission"})
@ToString(exclude = {"role", "permission"})
@Entity
@Table(name = "role_permissions")
@Data
public class RolePermission {
    @EmbeddedId
    private RolePermissionId id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;
}
