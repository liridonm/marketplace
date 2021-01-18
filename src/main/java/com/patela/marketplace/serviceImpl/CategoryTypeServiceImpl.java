package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.CategoryTypeDTO;
import com.patela.marketplace.domain.entities.CategoryType;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.CategoryTypeRepository;
import com.patela.marketplace.service.CategoryTypeService;
import com.patela.marketplace.util.MapperUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryTypeServiceImpl implements CategoryTypeService {
    private final CategoryTypeRepository categoryTypeRepository;
    private final MapperUtil mapperUtil;

    public CategoryTypeServiceImpl(CategoryTypeRepository categoryTypeRepository, MapperUtil mapperUtil){
        this.categoryTypeRepository = categoryTypeRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CategoryTypeDTO> readAll() throws ServiceException {
        return categoryTypeRepository.findAll().stream()
                .map(categoryType -> mapperUtil.convertToDTO(categoryType, new CategoryTypeDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryTypeDTO readBy(Integer id) throws ServiceException {
        CategoryType categoryType = categoryTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Category Type doesn't exit"));
        return mapperUtil.convertToDTO(categoryType, new CategoryTypeDTO());

    }

    @Override
    public CategoryTypeDTO create(CategoryTypeDTO categoryType) throws ServiceException {
        CategoryType convertCategoryType = mapperUtil.convertToDTO(categoryType, new CategoryType());
        validateCategoryType(convertCategoryType);
        CategoryType createdCategoryType = categoryTypeRepository.save(convertCategoryType);
        return mapperUtil.convertToDTO(categoryType, new CategoryTypeDTO());
    }

    @Override
    public CategoryTypeDTO update(CategoryTypeDTO categoryType) throws ServiceException {
        CategoryType convertCategoryType = mapperUtil.convertToDTO(categoryType, new CategoryType());
        readBy(categoryType.getId());
        validateCategoryType(convertCategoryType);
        CategoryType updatedCategoryType = categoryTypeRepository.save(convertCategoryType);

        return mapperUtil.convertToDTO(updatedCategoryType, new CategoryTypeDTO());
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        CategoryTypeDTO foundCategoryType = readBy(id);
        foundCategoryType.setIsDeleted(true);
        CategoryType convertedCategoryType = mapperUtil.convertToEntity(foundCategoryType, new CategoryType());

        categoryTypeRepository.save(convertedCategoryType);
    }

    public void validateCategoryType(CategoryType categoryType) throws ServiceException{
        Optional<CategoryType> foundCategoryType = categoryTypeRepository.findByName(categoryType.getName());
        if(foundCategoryType.isPresent()){
            throw new ServiceException("Category Type already exists");
        }

        if(categoryType.getName().isEmpty()){
            throw new ServiceException("Name cannot be null");
        }
    }
}
