package com.example.spring;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.conf.CachingConfig;
import com.example.spring.conf.DataSourceConfiguration;
import com.example.spring.conf.ServiceConfig;
import com.example.spring.model.Customer;
import com.example.spring.service.CustomerDataService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { DataSourceConfiguration.class, 
        CachingConfig.class, ServiceConfig.class })
@EnableCaching
@ActiveProfiles("dev")
public class SpringCacheApplicationTests {

    @Autowired
    private CustomerDataService customerDataService;
    
    @Test
    public void testGetCustomer() {
        for (int i = 0; i < 1000; i++) {
            customerDataService.getCustomer(1);
            System.out.println("Get Customer completed!");
        }
    }

    @Test
    public void testAddCustomer() {
        Customer c = new Customer();
        c.setFirstName("A6");
        c.setLastName("S6");
        customerDataService.insert(c);
        System.out.println("Customer added.");
    }

    @Test
    public void testUpdateCustomer() {
        Customer c = customerDataService.getCustomer(1);
        c.setFirstName("A1_Updated");
        customerDataService.update(c);
        System.out.println("Customer updated.");
    }

    @Test
    public void testDeleteCustomer() {
        customerDataService.delete(3);
        System.out.println("Customer deleted.");
    }

    @Test
    public void testListCustomer() {
        List<Customer> customers = customerDataService.listCustomers();
        System.out.println("-------------- CUSTOMERS --------------");
        for (Customer c : customers) {
            System.out.format("Customer ID         = %d\n", c.getId());
            System.out.format("Customer First Name = %s\n", c.getFirstName());
            System.out.format("Customer Last Name  = %s\n", c.getLastName());
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

}
