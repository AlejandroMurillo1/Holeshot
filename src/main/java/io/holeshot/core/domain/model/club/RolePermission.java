package io.holeshot.core.domain.model.club;

import io.holeshot.core.domain.model.club.pks.RolePermissionId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Role_Permissions")
@Data
public class RolePermission {
    @EmbeddedId
    private RolePermissionId id;

    @ManyToOne
    @MapsId(value = "roleId")
    private Role role;

    @ManyToOne
    @MapsId(value = "permissionId")
    private Permission permission;
}
