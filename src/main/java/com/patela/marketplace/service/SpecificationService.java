package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.SpecificationDTO;
import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface SpecificationService {
    List<SpecificationDTO> readAll();
    SpecificationDTO readById
            (Integer id) throws ServiceException;
    SpecificationDTO create(SpecificationDTO specification) throws ServiceException;
    SpecificationDTO update(SpecificationDTO specification) throws ServiceException;
    void deleteById(Integer id) throws ServiceException;
}
