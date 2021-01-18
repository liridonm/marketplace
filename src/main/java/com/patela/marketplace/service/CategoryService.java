package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.CategoryDTO;
import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> readAll();
    CategoryDTO readBy(Integer id) throws ServiceException;
    CategoryDTO create(CategoryDTO category) throws ServiceException;
    CategoryDTO update(CategoryDTO category) throws ServiceException;
    void deleteById(Integer id) throws ServiceException;
}
