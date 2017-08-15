package com.example.spring.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.spring.dao.CustomerDAO;
import com.example.spring.model.Customer;

@Service
@CacheConfig(cacheNames={"customer"})
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDao;

    public CustomerServiceImpl(CustomerDAO customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    @Cacheable
    public Customer findCustomer(Integer id) {
        simulateSlowService(); // Magic is here! Some business logic.
        return customerDao.getCustomer(id);
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 1000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
