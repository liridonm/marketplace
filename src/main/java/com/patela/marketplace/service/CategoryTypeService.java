package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.CategoryTypeDTO;
import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface CategoryTypeService {
    List<CategoryTypeDTO> readAll() throws ServiceException;
    CategoryTypeDTO readBy(Integer id) throws ServiceException;
    CategoryTypeDTO create(CategoryTypeDTO categoryType) throws ServiceException;
    CategoryTypeDTO update(CategoryTypeDTO categoryType) throws ServiceException;
    void deleteById(Integer id) throws ServiceException;
}
