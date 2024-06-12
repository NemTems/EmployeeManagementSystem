package com.system.EmployeeManagementSystem.controllers;


import com.system.EmployeeManagementSystem.DTOs.IDCardDTO;
import com.system.EmployeeManagementSystem.models.IDCard;
import com.system.EmployeeManagementSystem.services.IDCardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/idCards")
public class IDCardController {
    private final IDCardService idCardService;

    public IDCardController(IDCardService idCardService) {
        this.idCardService = idCardService;
    }

    @GetMapping("/showAll")
    public List<IDCardDTO> getAllIDCards() {
        return idCardService.getAll();
    }

    @GetMapping("/{id}")
    public IDCardDTO getIDCardById(@PathVariable int id) {
        return idCardService.getById(id);
    }

    @PostMapping("/add")
    public IDCard addIDCard(@RequestBody IDCard department) {
        return idCardService.save(department);
    }

    @PutMapping("/update")
    public IDCard updateIDCard(@RequestBody IDCard department) {
        return idCardService.update(department);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteIDCard(@PathVariable int id) {
        idCardService.delete(id);
    }
}
