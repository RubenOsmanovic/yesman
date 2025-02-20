package com.example.kym.demo.service;

import com.example.kym.demo.hotel_manager.Equipment;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/eq")
public class Rc {

    private final EqService eqService;

    public EquipmentDTO toDTO(Equipment equipment) {
        return new EquipmentDTO(equipment);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<EquipmentDTO>>> findAll() {

        return ResponseEntity.ok(
                eqService.getAllEquipment().stream()
                        .map(this::toDTO)
                        .map(equipmentDTO -> {

                            EntityModel<EquipmentDTO> model = EntityModel.of(equipmentDTO);

                            WebMvcLinkBuilder selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).findAll());

                            model.add(selfLink.withRel("equipment-all"));

                            return model;

                        })
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<EquipmentDTO> save(@RequestBody EquipmentDTO equipment) {

        Equipment eq = eqService.createEQ(equipment);

        URI loc = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eq.getId())
                .toUri();

        return ResponseEntity.created(loc).body(toDTO(eq));

    }

    @PutMapping("/{name}")
    public ResponseEntity<EntityModel<EquipmentDTO>> update(@PathVariable String name, @RequestBody EquipmentDTO equipment) {

        return eqService.updateEquipment(name, equipment)
                .map(this::toDTO)
                .map(equipment1 -> {
                    EntityModel<EquipmentDTO> model = EntityModel.of(equipment);

                    WebMvcLinkBuilder selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).update(name, equipment));
                    WebMvcLinkBuilder linkToAll = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());

                    model.add(selfLink.withSelfRel());
                    model.add(linkToAll.withRel("equipment-all"));
                    return model;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}
