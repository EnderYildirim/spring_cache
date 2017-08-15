package com.example.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.spring.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private JdbcTemplate jdbcTemplate;

    private final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id = ?";

    public static class CustomerMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("fname"));
            customer.setLastName(rs.getString("lname"));
            return customer;
        }
    }

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer getCustomer(Integer id) {
        return jdbcTemplate.queryForObject(FIND_CUSTOMER_BY_ID, new Object[] {id}, new CustomerMapper());
    }

}
