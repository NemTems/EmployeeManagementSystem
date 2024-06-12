package com.system.EmployeeManagementSystem.services;

import com.system.EmployeeManagementSystem.DTOs.IDCardDTO;
import com.system.EmployeeManagementSystem.mapper.IDCardDTOMapper;
import com.system.EmployeeManagementSystem.models.IDCard;
import com.system.EmployeeManagementSystem.repositories.IDCardRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IDCardService {

    private IDCardRepository IDCardRepository;

    public IDCardService(IDCardRepository IDCardRepository) {
        this.IDCardRepository = IDCardRepository;
    }

    public List<IDCardDTO> getAll() {
        return IDCardRepository.findAll().stream()
                .map(IDCardDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IDCardDTO getById(int id) {
        return IDCardRepository.findById(id).map(IDCardDTOMapper::toDTO).get();
    }

    public IDCard save(IDCard IDCard) {
        return IDCardRepository.save(IDCard);
    }

    public IDCard update(IDCard IDCard) {
        return IDCardRepository.save(IDCard);
    }

    public void delete(int id) {
        IDCardRepository.deleteById(id);
    }

}
