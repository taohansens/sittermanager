package org.taohansen.sittermanager.entities.interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.taohansen.sittermanager.dtos.BabySitterDTO;
import org.taohansen.sittermanager.entities.Babysitter;

@Mapper
public interface BabysitterMapper {

    BabysitterMapper INSTANCE = Mappers.getMapper(BabysitterMapper.class);
    @Mapping(source = "dto.fullName", target = "fullName")
    @Mapping(source = "dto.cpf", target = "cpf")
    @Mapping(source = "dto.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "dto.address", target = "address")
    @Mapping(source = "dto.phoneNumber", target = "phoneNumber")
    @Mapping(source = "dto.bonus", target = "bonus")
    @Mapping(source = "dto.weeklyHours", target = "weeklyHours")
    @Mapping(source = "dto.monthlySalary", target = "monthlySalary")
    @Mapping(source = "dto.yearsOfExperience", target = "yearsOfExperience")
    @Mapping(source = "dto.maxTravelDistance", target = "maxTravelDistance")
    Babysitter toEntity(BabySitterDTO dto);

    @Mapping(source = "entity.fullName", target = "fullName")
    @Mapping(source = "entity.cpf", target = "cpf")
    @Mapping(source = "entity.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "entity.address", target = "address")
    @Mapping(source = "entity.phoneNumber", target = "phoneNumber")
    @Mapping(source = "entity.bonus", target = "bonus")
    @Mapping(source = "entity.weeklyHours", target = "weeklyHours")
    @Mapping(source = "entity.monthlySalary", target = "monthlySalary")
    @Mapping(source = "entity.yearsOfExperience", target = "yearsOfExperience")
    @Mapping(source = "entity.maxTravelDistance", target = "maxTravelDistance")
    BabySitterDTO toDto(Babysitter entity);
}
