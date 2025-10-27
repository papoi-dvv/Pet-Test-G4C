package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.mappers.VisitMapper;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VisitServiceImpl implements VisitService {

    VisitRepository visitRepository;
    VisitMapper visitMapper;

    public VisitServiceImpl(VisitRepository visitRepository, VisitMapper visitMapper) {
        this.visitRepository = visitRepository;
        this.visitMapper = visitMapper;
    }

    /**
     * @param visitDTO
     * @return
     */
    @Override
    public VisitDTO create(VisitDTO visitDTO) {
        Visit newVisit = visitRepository.save(visitMapper.mapToVisitEntity(visitDTO));
        return visitMapper.mapToVisitDTO(newVisit);
    }

    /**
     * @param visitDTO
     * @return
     * @throws VisitNotFoundException
     */
    @Override
    public VisitDTO update(VisitDTO visitDTO) throws VisitNotFoundException {
        findById(visitDTO.getId());
        Visit updatedVisit = visitRepository.save(visitMapper.mapToVisitEntity(visitDTO));
        return visitMapper.mapToVisitDTO(updatedVisit);
    }

    /**
     * @param id
     * @throws VisitNotFoundException
     */
    @Override
    public void delete(Integer id) throws VisitNotFoundException {
        VisitDTO visit = findById(id);
        visitRepository.delete(visitMapper.mapToVisitEntity(visit));
    }

    /**
     * @param id
     * @return
     * @throws VisitNotFoundException
     */
    @Override
    public VisitDTO findById(Integer id) throws VisitNotFoundException {
        Optional<Visit> visit = visitRepository.findById(id);
        
        if (!visit.isPresent())
            throw new VisitNotFoundException("Visit Not Found...!");
        
        return visitMapper.mapToVisitDTO(visit.get());
    }

    /**
     * @return
     */
    @Override
    public List<VisitDTO> findAll() {
        return visitRepository.findAll()
                .stream()
                .map(visitMapper::mapToVisitDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param petId
     * @return
     */
    @Override
    public List<VisitDTO> findByPetId(Integer petId) {
        List<Visit> visits = visitRepository.findByPetId(petId);
        visits.forEach(visit -> log.info("" + visit));
        return visits
                .stream()
                .map(visitMapper::mapToVisitDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param vetId
     * @return
     */
    @Override
    public List<VisitDTO> findByVetId(Integer vetId) {
        List<Visit> visits = visitRepository.findByVetId(vetId);
        visits.forEach(visit -> log.info("" + visit));
        return visits
                .stream()
                .map(visitMapper::mapToVisitDTO)
                .collect(Collectors.toList());
    }
}
