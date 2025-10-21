package com.example.studymaterial.controller;

import com.example.studymaterial.model.Material;
import com.example.studymaterial.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * REST API Controller for material operations
 */
@RestController
@RequestMapping("/api/materials")
public class ApiMaterialController {
    
    @Autowired
    private MaterialService materialService;
    
    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> materials = materialService.getAllMaterials();
        return ResponseEntity.ok(materials);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getMaterialById(@PathVariable Long id) {
        Optional<Material> material = materialService.getMaterialById(id);
        if (material.isPresent()) {
            return ResponseEntity.ok(material.get());
        }
        return ResponseEntity.status(404).body("Material not found");
    }
    
    @PostMapping
    public ResponseEntity<?> createMaterial(@RequestBody Material material) {
        Material created = materialService.saveMaterial(material);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMaterial(@PathVariable Long id, @RequestBody Material material) {
        material.setMaterialId(id);
        Material updated = materialService.saveMaterial(material);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.ok("Material deleted successfully");
    }
}
