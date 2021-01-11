package com.patela.marketplace.service;

import com.patela.marketplace.domain.dtos.CurrencyDTO;
import com.patela.marketplace.domain.entities.Currency;
import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDTO> readAll();

    CurrencyDTO readById(Integer id) throws ServiceException;

    CurrencyDTO create(CurrencyDTO currency) throws ServiceException;

    CurrencyDTO update(CurrencyDTO currency) throws ServiceException;

    void deleteById(Integer id) throws ServiceException;
}
