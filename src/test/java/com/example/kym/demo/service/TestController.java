package com.example.kym.demo.service;

import com.example.kym.demo.hotel_manager.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Rc.class)
public class TestController {

    @MockBean
    private EqService eqService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private Equipment eq1;
    private Equipment eq2;

    @BeforeEach
    void setup() {

        assumeThat(eqService).isNotNull();
        assumeThat(mockMvc).isNotNull();

        eq1 = Equipment.builder()
                .name("Ni")
                .amount(10)
                .build();

        eq2 = Equipment.builder()
                .name("Ga")
                .amount(5)
                .build();

    }

    @Test
    public void testWhetherGetAllReturns200() throws Exception {

        when(eqService.getAllEquipment()).thenReturn(List.of(eq1, eq2));

        var request = get("/api/eq")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].name").value("Ni"))
                .andExpect(jsonPath("[0].amount").value(10))
                .andDo(print());

    }

    @Test
    public void testReturn200IfUpdateProperly() throws Exception {

        EquipmentDTO ed = new EquipmentDTO(eq1);

        when(eqService.updateEquipment("Ni", ed)).thenReturn(Optional.of(eq1));

        var request = put("/api/eq/Ni")
                .content(mapper.writeValueAsString(ed))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Ni"))
                .andExpect(jsonPath("$.amount").value(10))
                .andExpect(jsonPath("$._links.equipment-all.href").value("http://localhost/api/eq"))
                .andDo(print());

    }

}
