package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.TaxDTO;
import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface TaxService {
    List<TaxDTO> readAll();
    TaxDTO readBy(Integer id) throws ServiceException;
    TaxDTO create(TaxDTO tax) throws ServiceException;
    TaxDTO update(TaxDTO tax) throws ServiceException;
    void deleteById(Integer id) throws ServiceException;
}
