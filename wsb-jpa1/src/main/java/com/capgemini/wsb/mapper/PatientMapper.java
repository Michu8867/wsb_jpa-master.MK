package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public static PatientTO toTO(PatientEntity entity) {
        if (entity == null) {
            return null;
        }
        PatientTO dto = new PatientTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getTelephoneNumber());
        dto.setDateOfBirth(entity.getDateOfBirth());
        return dto;
    }

    public static PatientEntity fromTO(PatientTO dto) {
        if (dto == null) {
            return null;
        }
        PatientEntity entity = new PatientEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setTelephoneNumber(dto.getPhoneNumber());
        entity.setDateOfBirth(dto.getDateOfBirth());
        return entity;
    }
}
