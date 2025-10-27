package com.tecsup.petclinic.mappers;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import org.springframework.stereotype.Component;

@Component
public class VisitMapper {
    
    /**
     * Convierte DTO a Entity
     * @param visitDTO
     * @return
     */
    public Visit mapToVisitEntity(VisitDTO visitDTO) {
        if (visitDTO == null) return null;
        return new Visit(
                visitDTO.getId(),
                visitDTO.getPetId(),
                visitDTO.getVetId(),
                visitDTO.getVisitDate(),
                visitDTO.getDescription(),
                visitDTO.getCost()
        );
    }

    /**
     * Convierte Entity a DTO
     * @param entity
     * @return
     */
    public VisitDTO mapToVisitDTO(Visit entity) {
        if (entity == null) return null;
        return new VisitDTO(
                entity.getId(),
                entity.getPetId(),
                entity.getVetId(),
                entity.getVisitDate(),
                entity.getDescription(),
                entity.getCost()
        );
    }
}
