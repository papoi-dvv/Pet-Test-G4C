package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;

import java.util.List;

public interface VisitService {
    /**
     * @param visitDTO
     * @return
     */
    VisitDTO create(VisitDTO visitDTO);

    /**
     * @param visitDTO
     * @return
     */
    VisitDTO update(VisitDTO visitDTO) throws VisitNotFoundException;

    /**
     * @param id
     * @throws VisitNotFoundException
     */
    void delete(Integer id) throws VisitNotFoundException;

    /**
     * @param id
     * @return
     */
    VisitDTO findById(Integer id) throws VisitNotFoundException;

    /**
     * @return
     */
    List<VisitDTO> findAll();

    /**
     * @param petId
     * @return
     */
    List<VisitDTO> findByPetId(Integer petId);

    /**
     * @param vetId
     * @return
     */
    List<VisitDTO> findByVetId(Integer vetId);
}
