package io.holeshot.core.infrastructure.persistence;

import io.holeshot.core.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingOrLastNameContainsIgnoreCase(String name, String lastName);
    List<User> findByDocumentNumberContaining(String prefix);
}
