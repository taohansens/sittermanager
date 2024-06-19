package org.taohansen.sittermanager.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.taohansen.sittermanager.entities.Babysitter;
import org.taohansen.sittermanager.tests.Factory;

import java.util.Optional;

@DataJpaTest
public class BabysitterRepositoryTests {

    @Autowired
    private BabysitterRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalBabySitters;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalBabySitters = repository.count();
    }
    @Test
    public void findByIdShouldReturnOptionalNonEmptyWhenIdExists(){
        Optional<Babysitter> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnOptionalEmptyWhenIdNonExists(){
        Optional<Babysitter> result = repository.findById(nonExistingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull(){
        Babysitter babysitter = Factory.createBabySitter();
        babysitter.setId(null);

        babysitter = repository.save(babysitter);

        Assertions.assertNotNull(babysitter.getId());
        Assertions.assertEquals(countTotalBabySitters + 1, babysitter.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        repository.deleteById(existingId);
        Optional<Babysitter> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }
}
