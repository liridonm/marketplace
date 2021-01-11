package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.UomDTO;
import com.patela.marketplace.domain.entities.Uom;
import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface UomService {

    List<UomDTO> readAll();

    UomDTO readById(Integer id) throws ServiceException;

    UomDTO create(UomDTO uom) throws ServiceException;

    UomDTO update(UomDTO uom) throws ServiceException;

    void deleteById(Integer id) throws ServiceException;

}
