package com.example.kym.demo.service;

import com.example.kym.demo.hotel_manager.Equipment;
import com.example.kym.demo.hotel_manager.EquipmentRep;
import com.example.kym.demo.service.exception.EQNotFound;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RCEq.class)
public class TestRestController {

    private Equipment equipment1;
    private Equipment equipment2;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EqService eqService;

    @BeforeEach
    void setup() {
        assumeThat(mockMvc).isNotNull();
        assumeThat(eqService).isNotNull();
       equipment1 = Equipment.builder()
               .name("Ninjago")
               .amount(10)
               .build();
       equipment2 = Equipment.builder()
               .name("Chicken")
               .amount(11)
               .build();


    }

    @Test
    public void getAllEquipment_ShouldReturnAllEquipment() throws Exception {

        when(eqService.getAllEquipment()).thenReturn(List.of(equipment1, equipment2));

        var request = get("/api/equipment").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[1].name").value(equipment1.getName()))
                .andExpect(jsonPath("[1].amount").value(equipment1.getAmount()))
                .andDo(print());

    }

    @Test
    public void getEquipmentByName_ShouldReturnEquipment_WhenFound() throws Exception {

        when(eqService.getByName(equipment1.getName())).thenReturn(Optional.of(equipment1));

        var request = get("/api/equipment/" + equipment1.getName()).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(equipment1.getName()))
                .andExpect(jsonPath("$._links.self.href").value("http://localhost/api/equipment/" + equipment1.getName()))
                .andExpect(jsonPath("$._links.equipment-all.href").value("http://localhost/api/equipment"))
                .andDo(print());

    }

    @Test
    public void getEquipmentByName_ShouldReturnNotFound_WhenNotFound() throws Exception {
        when(eqService.getByName(any())).thenReturn(Optional.empty());

        var request = get("/api/equipment/" + "").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void createEquipment_ShouldReturnEquipment_WhenCreated() throws Exception {
        EquipmentDTO ed = new EquipmentDTO(equipment1);
        when(eqService.createEQ(ed)).thenReturn(equipment1);

        var request = post("/api/equipment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ed))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated()) // Verify that the status code is 201
                .andExpect(header().string("Location", "http://localhost/api/equipment/"))
                .andExpect(jsonPath("$.name").value(equipment1.getName()))
                .andExpect(jsonPath("$.amount").value(equipment1.getAmount()))
                .andDo(print());
    }

    @Test
    public void updateEquipment_ShouldReturnEquipment_WhenUpdated() throws Exception {

        EquipmentDTO ed = new EquipmentDTO(equipment1);
        when(eqService.updateEquipment("Ninjago", ed)).thenReturn(Optional.of(equipment1));

        var request = put("/api/equipment/Ninjago")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ed))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(equipment1.getName()))
                .andExpect(jsonPath("$._links.self.href").value("http://localhost/api/equipment/" + equipment1.getName()))
                .andExpect(jsonPath("$._links.equipment-all.href").value("http://localhost/api/equipment"))
                .andDo(print());
    }

    @Test
    void deleteEquipment_ShouldReturnEquipment_WhenDeleted() throws Exception {

        var requst = delete("/api/equipment/" + equipment1.getName());

        mockMvc.perform(requst)
                .andExpect(status().isNoContent())
                .andDo(print());

    }

    @Test
    void exceptionhandling() throws Exception {
        when(eqService.getByName("nonexistentName")).thenThrow(new EQNotFound("Equipment not found"));

        mockMvc.perform(get("/api/equipment/nonexistentName"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Equipment not found"))
                .andExpect(jsonPath("$.time").exists());
    }

    @Test
    void exceptionhandlingBadRequest() throws Exception {

        mockMvc.perform(post("/api/equipment"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Bad Request"));
    }

}
