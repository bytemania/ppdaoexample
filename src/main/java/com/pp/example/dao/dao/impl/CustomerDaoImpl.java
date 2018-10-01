package com.pp.example.dao.dao.impl;

import com.pp.example.dao.dao.CustomerDao;
import com.pp.example.dao.model.Address;
import com.pp.example.dao.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {



    private final static RowMapper<Customer> CUSTOMER_ROW_MAPPER = (resultSet, rowMapper) -> {

        Long id = resultSet.getLong("id");
        Long referrerId = resultSet.getLong("referrer_id");
        String name = resultSet.getString("name");
        Long addressId = resultSet.getLong("addressId");
        String address = resultSet.getString("address");

        return new Customer(id, name, new Address(addressId, address), referrerId);
    };

    private final static Unmapper<Customer> CUSTOMER_UNMAPPER = customer -> {
        Map<String, Object> map = new HashMap<>();

        map.put("id", customer.getId());
        map.put("referrer_id", customer.getReferrerId());
        map.put("name", customer.getName());
        map.put("addressId", customer.getAddress().getId());
        map.put("address", customer.getAddress().getAddress());

        return map;
    };


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CustomerDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Customer create(Customer customer) throws SQLException {
        return null;
    }

    @Override
    public Optional<Customer> read(Long id) throws IllegalArgumentException, SQLException {
        return Optional.empty();
    }

    @Override
    public List<Customer> read() throws IllegalArgumentException, SQLException {
        return null;
    }

    @Override
    public void update(Customer customer) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
