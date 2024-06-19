package com.system.EmployeeManagementSystem.controllers;


import com.system.EmployeeManagementSystem.DTOs.IDCardDTO;
import com.system.EmployeeManagementSystem.services.implementation.IDCardServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/idCards")
public class IDCardController {
    private final IDCardServiceImpl idCardService;

    public IDCardController(IDCardServiceImpl idCardService) {
        this.idCardService = idCardService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<IDCardDTO>> getAllIDCards(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(idCardService.getAllIdCards(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IDCardDTO> getIDCardById(@PathVariable int id) {
        return new ResponseEntity<>(idCardService.getIdCardById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addIDCard(@RequestBody IDCardDTO idCardDTO) {
        return new ResponseEntity<>(idCardService.createIdCard(idCardDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateIDCard(@PathVariable int id, @RequestBody IDCardDTO idCardDTO) {
        return new ResponseEntity<>(idCardService.updateIdCard(idCardDTO, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteIDCard(@PathVariable int id) {
        idCardService.deleteIdCardById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
