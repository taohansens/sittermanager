package org.taohansen.sittermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taohansen.sittermanager.entities.Babysitter;

@Repository
public interface BabysitterRepository extends JpaRepository<Babysitter, Long> {
}