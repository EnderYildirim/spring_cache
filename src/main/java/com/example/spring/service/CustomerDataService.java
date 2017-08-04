package com.example.spring.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.spring.model.Customer;

@Component
@CacheConfig(cacheNames={"customer"})
public class CustomerDataService {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_CUSTOMER       = "INSERT INTO customer(fname, lname) VALUES(?, ?)";

    private final String DELETE_CUSTOMER       = "DELETE FROM customer WHERE id = ?";

    private final String UPDATE_CUSTOMER       = "UPDATE customer SET fname = ?, lname = ? WHERE id = ?";

    private final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id = ?";

    private final String ALL_CUSTOMER          = "SELECT * FROM customer";

    public static class CustomerMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("fname"));
            customer.setLastName(rs.getString("lname"));
            return customer;
        }
    }

    public CustomerDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @CachePut
    public void insert(Customer c) {
        jdbcTemplate.update(INSERT_CUSTOMER, c.getFirstName(), c.getLastName());
    }

    @Cacheable
    public Customer getCustomer(Integer id) {
        simulateSlowService(); // Magic is here!
        return jdbcTemplate.queryForObject(SELECT_CUSTOMER_BY_ID, new Object[] {id}, new CustomerMapper());
    }

    @Cacheable
    public List<Customer> listCustomers() {
        return jdbcTemplate.query(ALL_CUSTOMER, new CustomerMapper());
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_CUSTOMER, id);
    }

    @CachePut
    public void update(Customer c) {
        jdbcTemplate.update(UPDATE_CUSTOMER, c.getFirstName(), c.getLastName(), c.getId());
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
