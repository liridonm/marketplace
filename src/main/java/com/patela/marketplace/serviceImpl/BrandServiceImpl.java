package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.dtos.BrandDTO;
import com.patela.marketplace.domain.entities.Brand;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.repository.BrandRepository;
import com.patela.marketplace.service.BrandService;
import com.patela.marketplace.util.MapperUtil;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final MapperUtil mapperUtil;

    public BrandServiceImpl(BrandRepository brandRepository, MapperUtil mapperUtil){
        this.brandRepository = brandRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<BrandDTO> readAll() {
        return brandRepository.findAll().stream()
                .map(brand -> mapperUtil.convertToDTO(brand, new BrandDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public BrandDTO readById(Integer id) throws ServiceException {
        Brand brand =  brandRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Brand does not exist"));
        return mapperUtil.convertToDTO(brand, new BrandDTO());
    }

    @Override
    public BrandDTO create(BrandDTO brand) throws ServiceException {
        Brand convertBrand = mapperUtil.convertToDTO(brand, new Brand());
        validateBrand(convertBrand);
        Brand createdBrand = brandRepository.save(convertBrand);
        return mapperUtil.convertToDTO(createdBrand, new BrandDTO());
    }

    @Override
    public BrandDTO update(BrandDTO brand) throws ServiceException {
        Brand convertBrand = mapperUtil.convertToEntity(brand, new Brand());
        readById(brand.getId());
        validateBrand(convertBrand);
        Brand updatedBrand = brandRepository.save(convertBrand);

        return mapperUtil.convertToDTO(updatedBrand,new BrandDTO());
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {
        BrandDTO foundBrand = readById(id);
        foundBrand.setIsDeleted(true);
        Brand convertedBrand = mapperUtil.convertToEntity(foundBrand, new Brand());

        brandRepository.save(convertedBrand);
    }

    private void validateBrand(Brand brand) throws ServiceException{
        Optional<Brand> foundBrand = brandRepository.findByName(brand.getName());
        if(foundBrand.isPresent()){
            throw new ServiceException("Brand already exists!");
        }

        if(StringUtils.isEmpty(brand.getName())){
            throw new ServiceException("Name cannot be null");
        }
    }

}
