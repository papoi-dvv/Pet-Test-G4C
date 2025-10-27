package com.tecsup.petclinic.mappers;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import org.springframework.stereotype.Component;

@Component
public class VetMapper {
    /**
     * Convierte DTO a Entity
     * @param vetDTO
     * @return
     */
    public Vet mapToVetEntity(VetDTO vetDTO) {
        if (vetDTO == null) return null;
        return new Vet(
                vetDTO.getId(),
                vetDTO.getFirstName(),
                vetDTO.getLastName(),
                vetDTO.getEmail(),
                vetDTO.getPhone(),
                vetDTO.getActive()
        );
    }

    /**
     * Convierte Entity a DTO
     * @param entity
     * @return
     */
    public VetDTO mapToVetDTO(Vet entity) {
        if (entity == null) return null;
        return new VetDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getActive()
        );
    }
}
