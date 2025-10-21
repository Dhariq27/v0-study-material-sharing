package com.example.studymaterial.controller;

import com.example.studymaterial.model.Material;
import com.example.studymaterial.service.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * JUnit 5 tests for MaterialController.
 */
@ExtendWith(MockitoExtension.class)
public class MaterialControllerTest {
    
    @Mock
    private MaterialService materialService;
    
    @InjectMocks
    private MaterialController materialController;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(materialController).build();
    }
    
    @Test
    public void testListMaterials() throws Exception {
        List<Material> materials = new ArrayList<>();
        when(materialService.getAllMaterials()).thenReturn(materials);
        
        mockMvc.perform(get("/materials"))
                .andExpect(status().isOk())
                .andExpect(view().name("materials"));
        
        verify(materialService, times(1)).getAllMaterials();
    }
    
    @Test
    public void testListMaterialsBySubject() throws Exception {
        List<Material> materials = new ArrayList<>();
        when(materialService.getMaterialsBySubject("Computer Science")).thenReturn(materials);
        
        mockMvc.perform(get("/materials").param("subject", "Computer Science"))
                .andExpect(status().isOk())
                .andExpect(view().name("materials"));
        
        verify(materialService, times(1)).getMaterialsBySubject("Computer Science");
    }
}
