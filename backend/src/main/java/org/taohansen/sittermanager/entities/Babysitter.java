package org.taohansen.sittermanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "tb_babysitter")
public class Babysitter extends Employee {
    private int yearsOfExperience;
    private double maxTravelDistance;

    public Babysitter() {
    }

    public Babysitter(Long id, String name, String cpf, LocalDate dateOfBirth, String address, String phoneNumber, String email, int yearsOfExperience) {
        super(id, name, cpf, dateOfBirth, address, phoneNumber, email);
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public double getMaxTravelDistance() {
        return maxTravelDistance;
    }

    public void setMaxTravelDistance(double maxTravelDistance) {
        this.maxTravelDistance = maxTravelDistance;
    }
}
