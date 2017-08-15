package com.example.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCacheApplicationTests {

    @Autowired
    private CustomerService customerService;
    
    @Test
    public void testGetCustomer() {
        for (int i = 0; i < 1000; i++) {
            customerService.findCustomer(1);
            System.out.println("Get Customer completed!");
        }
    }

}
