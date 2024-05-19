package org.taohansen.sittermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taohansen.sittermanager.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
