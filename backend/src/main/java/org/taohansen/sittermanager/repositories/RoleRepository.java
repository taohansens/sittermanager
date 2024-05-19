package org.taohansen.sittermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taohansen.sittermanager.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String authority);
}