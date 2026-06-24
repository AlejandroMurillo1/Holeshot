package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingOrLastNameContainsIgnoreCase(String name, String lastName);
    Optional<User> findByDocumentNumber(String prefix);
    Optional<User> findByEmail(String email);
}
