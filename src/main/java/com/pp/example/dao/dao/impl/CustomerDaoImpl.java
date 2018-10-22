package com.pp.example.dao.dao.impl;

import com.google.common.collect.ImmutableMap;
import com.pp.example.dao.dao.CustomerDao;
import com.pp.example.dao.dao.exception.PersistException;
import com.pp.example.dao.dao.exception.ReadException;
import com.pp.example.dao.model.Address;
import com.pp.example.dao.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private static final RowMapper<Customer> CUSTOMER_ROW_MAPPER = (resultSet, rowMapper) -> {

        Long id = resultSet.getLong("ID");
        Long referrerId = resultSet.getLong("REFERRER_ID");
        String name = resultSet.getString("NAME");
        Long addressId = resultSet.getLong("ADDRESS_ID");
        String address = resultSet.getString("ADDRESS");

        return new Customer(id, name, new Address(addressId, address), referrerId);
    };

    private static final Unmapper<Customer> CUSTOMER_UNMAPPER = customer -> {
        Map<String, Object> map = new HashMap<>();

        map.put("id", customer.getId());
        map.put("referrerId", customer.getReferrerId());
        map.put("name", customer.getName());
        map.put("addressId", customer.getAddress().getId());
        map.put("address", customer.getAddress().getAddress());

        return map;
    };

    private static final String READ =
            "SELECT C.ID, C.NAME, C.ADDRESS_ID, A.ADDRESS, C.REFERRER_ID " +
            "FROM CUSTOMER C INNER JOIN ADDRESS A ON C.ADDRESS_ID = A.ID " +
            "WHERE C.ID = :id";
    private static final String READ_ALL =
            "SELECT C.ID, C.NAME, C.ADDRESS_ID, A.ADDRESS, C.REFERRER_ID " +
            "FROM CUSTOMER C INNER JOIN ADDRESS A ON C.ADDRESS_ID = A.ID";

    private static final String CREATE_CUSTOMER =
        "INSERT INTO CUSTOMER(ID, NAME, ADDRESS_ID, REFERRER_ID) VALUES (:id, :name, :addressId, :referrerId)";
    private static final String CREATE_ADDRESS =
        "INSERT INTO ADDRESS(ID, ADDRESS) VALUES (:addressId, :address)";

    private static final String UPDATE_CUSTOMER =
            "UPDATE CUSTOMER SET NAME = :name, ADDRESS_ID = :addressId, REFERRER_ID = :referrerId WHERE ID = :id";
    private static final String UPDATE_ADDRESS =
            "UPDATE ADDRESS SET ADDRESS = :address WHERE ID = :addressId";

    private static final String DELETE_CUSTOMER =
            "DELETE FROM CUSTOMER WHERE ID = :id";
    private static final String DELETE_ADDRESS =
            "DELETE FROM ADDRESS WHERE ID = :addressId";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CustomerDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Customer create(@NotNull Customer customer) throws PersistException {
        try {
            Map<String, Object> params = CUSTOMER_UNMAPPER.unmapper(customer);
            namedParameterJdbcTemplate.update(CREATE_ADDRESS, params);
            namedParameterJdbcTemplate.update(CREATE_CUSTOMER, params);
            return customer;
        } catch (DataAccessException e) {
            throw new PersistException(e);
        }
    }

    @Override
    public Optional<Customer> read(@NotNull Long id) throws IllegalArgumentException, ReadException {
        try {
             return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(READ, ImmutableMap.<String, Object>builder().put("id", id).build(),
                    CUSTOMER_ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new ReadException(e);
        }
    }

    @Override
    public List<Customer> read() throws IllegalArgumentException, ReadException {
        try {
            return namedParameterJdbcTemplate.query(READ_ALL, CUSTOMER_ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new ReadException(e);
        }
    }

    @Override
    public Customer update(Customer customer) throws PersistException {
        try {
            Map<String, Object> params = CUSTOMER_UNMAPPER.unmapper(customer);
            int numAddress = namedParameterJdbcTemplate.update(UPDATE_ADDRESS, params);
            int numCustomers = namedParameterJdbcTemplate.update(UPDATE_CUSTOMER, params);

            if (numAddress != 1 || numCustomers != 1) {
                throw new PersistException("Customer not found: {0}", customer);
            } else {
                return customer;
            }
        } catch (DataAccessException e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(@NotNull Long id) throws PersistException {
        try {
            Optional<Customer> customerOptional = read(id);
            if (customerOptional.isPresent()) {
               namedParameterJdbcTemplate.update(DELETE_CUSTOMER,
                       ImmutableMap.<String, Object>builder().put("id", customerOptional.get().getId()).build());
                namedParameterJdbcTemplate.update(DELETE_ADDRESS,
                        ImmutableMap.<String, Object>builder().put("addressId",
                                customerOptional.get().getAddress().getId()).build());
            }
        } catch (ReadException | DataAccessException e) {
            throw new PersistException(e);
        }

    }
}
