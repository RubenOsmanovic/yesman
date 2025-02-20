package com.example.kym.demo.service;

import com.example.kym.demo.hotel_manager.Equipment;
import com.example.kym.demo.service.exception.EQNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/equipment")
public class RCEq {

    private final EqService eqService;
    public EquipmentDTO toDTO(Equipment equipment) {
        return new EquipmentDTO(equipment);
    }
    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getEquipment() {
        return ResponseEntity.ok(
                eqService.getAllEquipment().stream()
                      //  .sorted(Comparator.comparing(Equipment::getName))
                      //  .filter(equipment -> equipment.getName().length() >= 4)
                        .map(this::toDTO)
                        .collect(Collectors.toList())
        );
    }


    @GetMapping("/{name}")
    public ResponseEntity<EntityModel<EquipmentDTO>> getEquipmentByName(@PathVariable String name) {

        return eqService.getByName(name)
                .map(this::toDTO)
                .map(equipmentDTO -> {
                    EntityModel<EquipmentDTO> model = EntityModel.of(equipmentDTO);

                    WebMvcLinkBuilder selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RCEq.class).getEquipmentByName(name));
                    model.add(selfLink.withSelfRel());

                    WebMvcLinkBuilder mvc =WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RCEq.class).getEquipment());
                    model.add(mvc.withRel("equipment-all"));
                    return model ;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<EquipmentDTO> createEquipment(@RequestBody EquipmentDTO equipment) {
        Equipment eq = eqService.createEQ(equipment);

        URI loc = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eq.getId())
                .toUri();

        return ResponseEntity.created(loc).body(toDTO(eq));
    }

    @PutMapping("/{name}")
    public ResponseEntity<EntityModel<EquipmentDTO>> updateEquipment(@PathVariable String name, @RequestBody EquipmentDTO equipmentDTO) {
        return eqService.updateEquipment(name, equipmentDTO)
                .map(this::toDTO)
                .map(equipmentDTO1 -> {

                    EntityModel<EquipmentDTO> model = EntityModel.of(equipmentDTO1);

                    WebMvcLinkBuilder selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateEquipment(name, equipmentDTO));
                    model.add(selfLink.withSelfRel());

                    WebMvcLinkBuilder allLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RCEq.class).getEquipment());
                    model.add(allLink.withRel("equipment-all"));

                    return model;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable String name) {
        eqService.deleteEquipment(name);

         return ResponseEntity.noContent().build();
    }


    // Other stuff


    LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    /*
    List<DiveDTO> processedDives = dives.stream()
            .filter(dive -> dive.getDate().isAfter(startDate)) // Filter by date
            .sorted(Comparator.comparing(Dive::getDate)) // Sort by date
            .map(this::mapToDTO) // Convert to DTO
            .collect(Collectors.toList());

     */

}
