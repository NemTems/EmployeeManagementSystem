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

    private final IDCardRepository idCardRepository;

    public IDCardService(IDCardRepository idCardRepository) {
        this.idCardRepository = idCardRepository;
    }

    public List<IDCardDTO> getAll() {
        return idCardRepository.findAll().stream()
                .map(IDCardDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IDCardDTO getById(int id) {
        return idCardRepository.findById(id).map(IDCardDTOMapper::toDTO).get();
    }

    public String save(IDCardDTO idCardDTO) {
        idCardRepository.save(IDCardDTOMapper.toEntity(idCardDTO));
        return "Saved";
    }

    public String update(IDCardDTO idCardDTO, int id) {
        IDCard foundIdCard = idCardRepository.findById(id).get();
        foundIdCard.setIssue_date(idCardDTO.getIssueDate());
        foundIdCard.setExpiry_date(idCardDTO.getExpiryDate());
        foundIdCard.setEmployee(IDCardDTOMapper.EmployeeToEntity(idCardDTO.getEmployee()));

        return "Updated";
    }

    public void delete(int id) {
        idCardRepository.deleteById(id);
    }

}
