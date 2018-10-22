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
import java.sql.SQLException;
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
        map.put("referrer_id", customer.getReferrerId());
        map.put("name", customer.getName());
        map.put("addressId", customer.getAddress().getId());
        map.put("address", customer.getAddress().getAddress());

        return map;
    };

    private static final String READ =
            "SELECT C.ID, C.NAME, C.ADDRESS_ID, A.ADDRESS, C.REFERRER_ID " +
            "FROM CUSTOMER C INNER JOIN ADDRESS A ON C.ADDRESS_ID = A.ID " +
            "WHERE C.ID = :id";

    private static final String CREATE_CUSTOMER =
            "INSERT INTO CUSTOMER(ID, NAME, ADDRESS_ID, REFERRER_ID) VALUES (:id, :name, :addressId, :referrer_id)";

    private static final String CREATE_ADDRESS =
            "INSERT INTO ADDRESS(ID, ADDRESS) VALUES (:addressId, :address)";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CustomerDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Customer create(Customer customer) throws PersistException {
        return null;
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
