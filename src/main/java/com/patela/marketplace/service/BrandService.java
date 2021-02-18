package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.BrandDTO;
import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface BrandService {
    List<BrandDTO> readAll();
    BrandDTO readById(Integer id) throws ServiceException;
    BrandDTO readByName(String name) throws ServiceException;
    BrandDTO create(BrandDTO brand) throws ServiceException;
    BrandDTO update(BrandDTO brand) throws ServiceException;
    void deleteById(Integer id) throws ServiceException;
}
