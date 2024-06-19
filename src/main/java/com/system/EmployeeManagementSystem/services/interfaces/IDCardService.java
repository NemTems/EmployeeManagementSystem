package com.system.EmployeeManagementSystem.services.interfaces;

import com.system.EmployeeManagementSystem.DTOs.IDCardDTO;

import java.util.List;

public interface IDCardService {

    List<IDCardDTO> getAllIdCards(int pageNo, int pageSize);

    IDCardDTO getIdCardById(int id);

    String createIdCard(IDCardDTO idCardDTO);

    String updateIdCard(IDCardDTO idCardDTO, int id);

    void deleteIdCardById(int id);
}
