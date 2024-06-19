package com.system.EmployeeManagementSystem.services.implementation;

import com.system.EmployeeManagementSystem.DTOs.IDCardDTO;
import com.system.EmployeeManagementSystem.exceptions.APIRequestException;
import com.system.EmployeeManagementSystem.mapper.IDCardDTOMapper;
import com.system.EmployeeManagementSystem.models.IDCard;
import com.system.EmployeeManagementSystem.repositories.IDCardRepository;
import com.system.EmployeeManagementSystem.services.interfaces.IDCardService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IDCardServiceImpl implements IDCardService {

    private final IDCardRepository idCardRepository;

    public IDCardServiceImpl(IDCardRepository idCardRepository) {
        this.idCardRepository = idCardRepository;
    }

    @Override
    public List<IDCardDTO> getAllIdCards(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return idCardRepository.findAll(pageable).getContent().stream()
                .map(IDCardDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IDCardDTO getIdCardById(int id) {
        return idCardRepository.findById(id).map(IDCardDTOMapper::toDTO)
                .orElseThrow(() -> new APIRequestException("Employee cannot be found by provided id"));
    }

    @Override
    public String createIdCard(IDCardDTO idCardDTO) {
        try {
            idCardRepository.save(IDCardDTOMapper.toEntity(idCardDTO));
            return "Saved";
        }
        catch (Exception e){
            throw new APIRequestException("IDCard cannot be created", e);
        }
    }

    @Override
    public String updateIdCard(IDCardDTO idCardDTO, int id) {
        IDCard foundIdCard = idCardRepository.findById(id)
                .orElseThrow(() -> new APIRequestException("IDCard cannot be found by provided id"));

        try {
            foundIdCard.setIssue_date(idCardDTO.getIssueDate());
            foundIdCard.setExpiry_date(idCardDTO.getExpiryDate());
            foundIdCard.setEmployee(IDCardDTOMapper.EmployeeToEntity(idCardDTO.getEmployee()));

            return "Updated";
        }
        catch (Exception e){
            throw new APIRequestException("Employee cannot be updated", e);
        }
    }

    @Override
    public void deleteIdCardById(int id) {
        try {
            idCardRepository.deleteById(id);
        } catch (Exception e){
            throw new APIRequestException("IDCard cannot be deleted or found by provided id", e);
        }
    }

}
