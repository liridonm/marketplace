package com.patela.marketplace.service;

import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface BaseService<T, ID> {

    List<T> readAll();

    T readById(ID id) throws ServiceException;

    T create(T obj) throws ServiceException;

    T update(T obj) throws ServiceException;

    void deleteById(ID id) throws ServiceException;

}
