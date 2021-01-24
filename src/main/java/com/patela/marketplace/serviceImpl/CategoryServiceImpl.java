package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.CategoryDTO;
import com.patela.marketplace.domain.entities.Category;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.CategoryRepository;
import com.patela.marketplace.service.CategoryService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil){
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CategoryDTO> readAll() {
        return categoryRepository.findAll().stream()
                .map(category -> mapperUtil.convertToDTO(category, new CategoryDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO readBy(Integer id) throws ServiceException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Category doesn't exist"));
        return mapperUtil.convertToDTO(category, new CategoryDTO());
    }

    @Override
    public CategoryDTO create(CategoryDTO category) throws ServiceException {
        Category convertCategory = mapperUtil.convertToDTO(category, new Category());
        validateCategory(convertCategory);
        Category createdCategory = categoryRepository.save(convertCategory);

        return mapperUtil.convertToDTO(category, new CategoryDTO());
    }

    @Override
    public CategoryDTO update(CategoryDTO category) throws ServiceException {
        Category convertCategory = mapperUtil.convertToDTO(category, new Category());
        readBy(category.getId());
        validateCategory(convertCategory);
        Category updatedCategory = categoryRepository.save(convertCategory);

        return mapperUtil.convertToDTO(updatedCategory, new CategoryDTO());
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        CategoryDTO foundCategory = readBy(id);
        foundCategory.setIsDeleted(true);
        Category convertedCategory = mapperUtil.convertToEntity(foundCategory, new Category());

        categoryRepository.save(convertedCategory);
    }

    private void validateCategory(Category category) throws ServiceException{
        Optional<Category> foundCategory = categoryRepository.findByName(category.getName());

        if(foundCategory.isPresent()){
            throw new ServiceException("Category already exists");
        }

        if(category.getName().isEmpty()){
            throw new ServiceException("Name cannot be null");
        }
    }
}
