package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.user.UciLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UciLicenseRepository extends JpaRepository<UciLicense, Long> {
}
