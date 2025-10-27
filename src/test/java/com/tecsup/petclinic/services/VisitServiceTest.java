package com.tecsup.petclinic.services;
import com.tecsup.petclinic.dtos.VisitDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testDeleteVisit() {

        String DESCRIPTION = "";
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
        log.info(" " + newVisitDTO);

        try {
            this.visitService.delete(newVisitDTO.getId());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            this.visitService.findById(newVisitDTO.getId());
            assertTrue(false, "Visit n");
        } catch (VisitNotFoundException e) {
            assertTrue(true);
            log.info("d");
        }
    }
}
