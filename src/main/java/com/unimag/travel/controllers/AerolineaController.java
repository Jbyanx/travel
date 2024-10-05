package com.unimag.travel.controllers;

import com.unimag.travel.dto.request.SaveAerolinea;
import com.unimag.travel.dto.response.GetAerolinea;
import com.unimag.travel.services.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/aerolineas")
public class AerolineaController {
    private AerolineaService aerolineaService;

    @Autowired
    public AerolineaController(AerolineaService aerolineaService){
        this.aerolineaService = aerolineaService;
    }

    @GetMapping
    public ResponseEntity<List<GetAerolinea>> getAllAerolineas(@RequestParam(required = false) String name){
        if(StringUtils.hasText(name)){
            return ResponseEntity.ok(
                        aerolineaService.getAerolineaByName(name)
                                .stream()
                                .toList()
            );
        }else{
            return ResponseEntity.ok(aerolineaService.getAllAerolineas());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAerolinea> getAerolineaById(@PathVariable Long id){
        return aerolineaService.getAerolineaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<GetAerolinea> createOneAerolinea(@RequestBody SaveAerolinea saveAerolinea){
        GetAerolinea createdAerolinea = aerolineaService.saveAerolinea(saveAerolinea);

        URI newLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAerolinea.id())
                .toUri();

        return ResponseEntity.created(newLocation).body(createdAerolinea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetAerolinea> updateAerolineaById(@RequestBody SaveAerolinea saveAerolinea, @PathVariable Long id){
        GetAerolinea aerolinea;

        try{
            aerolinea = aerolineaService.updateAerolineaById(id,saveAerolinea);
            return ResponseEntity.ok(aerolinea);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAerolinea(@PathVariable Long id) {
        aerolineaService.deleteAerolineaById(id);
        return ResponseEntity.noContent().build();
    }
}
