package org.taohansen.sittermanager.tests;

import org.taohansen.sittermanager.entities.Babysitter;

import java.time.LocalDate;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class Factory {
    private static final Random RANDOM = new Random();

    public static Babysitter createBabySitter() {
        Babysitter entity = new Babysitter();
        entity.setId(RANDOM.nextLong(1000));
        entity.setCpf(RandomStringUtils.randomNumeric(11));
        entity.setFullName("Nome de Teste");
        entity.setDateOfBirth(LocalDate.of(RANDOM.nextInt(1970,1995),
                RANDOM.nextInt(1,12), RANDOM.nextInt(1,25)));
        entity.setAddress(RandomStringUtils.randomAlphabetic(20));
        entity.setPhoneNumber(RandomStringUtils.randomNumeric(11));
        entity.setYearsOfExperience(RANDOM.nextInt(1,15));
        entity.setMaxTravelDistance(RANDOM.nextDouble(1,30.5));
        entity.setWeeklyHours(44);
        entity.setMonthlySalary(RANDOM.nextDouble(2000,6000));
        return entity;
    }

}
