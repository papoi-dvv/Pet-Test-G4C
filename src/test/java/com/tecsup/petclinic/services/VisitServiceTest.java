package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.dtos.VisitDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.exceptions.VisitNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testCreateVisit() {

        String DESCRIPTION = "General checkup";
        double COST = 75.00;
        Integer PET_ID = 1;
        Integer VET_ID = 1;
        LocalDate VISIT_DATE = LocalDate.of(2024, 12, 10);

        VisitDTO visitDTO = VisitDTO.builder()
                .description(DESCRIPTION)
                .cost(COST)
                .petId(PET_ID)
                .vetId(VET_ID)
                .visitDate(VISIT_DATE)
                .build();

        VisitDTO newVisitDTO = this.visitService.create(visitDTO);

        log.info("VISIT CREATED: " + newVisitDTO.toString());

        assertNotNull(newVisitDTO.getId());
        assertEquals(DESCRIPTION, newVisitDTO.getDescription());
        assertEquals(COST, newVisitDTO.getCost());
        assertEquals(PET_ID, newVisitDTO.getPetId());
        assertEquals(VET_ID, newVisitDTO.getVetId());
        assertEquals(VISIT_DATE, newVisitDTO.getVisitDate());
    }

    @Test
    public void testFindVisitById() {

        String DESCRIPTION_EXPECTED = "rabies shot";
        Integer ID = 1;

        VisitDTO visit = null;

        try {
            visit = this.visitService.findById(ID);
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }

        assertNotNull(visit);
        assertEquals(DESCRIPTION_EXPECTED, visit.getDescription());
    }


    @Test
    public void testFindVisitsByPetId() {

        Integer PET_ID = 7;
        int SIZE_EXPECTED = 2;

        List<VisitDTO> visits = this.visitService.findByPetId(PET_ID);

        assertEquals(SIZE_EXPECTED, visits.size());
        visits.forEach(visit -> {
            assertEquals(PET_ID, visit.getPetId());
            log.info("Visit for pet {}: {}", PET_ID, visit);
        });
    }


    @Test
    public void testFindVisitsByVetId() {

        Integer VET_ID = 2;
        int SIZE_EXPECTED = 2;

        List<VisitDTO> visits = this.visitService.findByVetId(VET_ID);

        assertEquals(SIZE_EXPECTED, visits.size());
        visits.forEach(visit -> {
            assertEquals(VET_ID, visit.getVetId());
            log.info("Visit for vet {}: {}", VET_ID, visit);
        });
    }


    @Test
    public void testDeleteVisit() {

        String DESCRIPTION = "Test visit for deletion";
        double COST = 50.00;
        Integer PET_ID = 1;
        Integer VET_ID = 1;
        LocalDate VISIT_DATE = LocalDate.now();

        VisitDTO visitDTO = VisitDTO.builder()
                .description(DESCRIPTION)
                .cost(COST)
                .petId(PET_ID)
                .vetId(VET_ID)
                .visitDate(VISIT_DATE)
                .build();

        VisitDTO newVisitDTO = this.visitService.create(visitDTO);
        log.info("VISIT CREATED: " + newVisitDTO);

        try {
            this.visitService.delete(newVisitDTO.getId());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            this.visitService.findById(newVisitDTO.getId());
            assertTrue(false, "Visit should not be found after deletion");
        } catch (VisitNotFoundException e) {
            assertTrue(true);
            log.info("Visit successfully deleted and not found");
        }
    }
}