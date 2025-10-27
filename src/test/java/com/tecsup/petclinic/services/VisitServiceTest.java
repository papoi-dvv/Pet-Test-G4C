package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
public class VisitServiceTest {

    @Test
    public void DeleteVisitTest(){

        String VISIT_NAME = " ";
        int OWNER_ID = 1;
        int TYPE_ID = 1;

        VisitDTO visitDTO = VisitDTO.builder()
                .name(VISIT_NAME)
                .ownerId(OWNER_ID)
                .typeId(TYPE_ID)
                .build();
        VisitDTO newVisitDTO = this.visitService.create(visitDTO);
        this.visitService.delete(newVisitDTO.getId());
        assertThrows(VisitNotFoundException.class, () -> {
            this.visitService.delete(newVisitDTO.getId());
        });

    }



}
