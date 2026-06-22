package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.club.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
