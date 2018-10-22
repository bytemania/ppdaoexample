package com.pp.example.dao.dao.impl;

import com.pp.example.dao.dao.CustomerDao;
import com.pp.example.dao.dao.exception.PersistException;
import com.pp.example.dao.model.Address;
import com.pp.example.dao.model.Customer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.pp.example.dao.model.matcher.CustomerMatches.same;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerDaoImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void read_persistedCustomer() throws Exception {
        Customer expectedCustomer = new Customer(
                102L,
                "Bruce Wayne",
                new Address(302L, "1007 Mountain, Dr., Gotham NY 10286"),
                101L);

        Optional<Customer> customerOpt = customerDao.read(102L);
        assertTrue(customerOpt.isPresent());
        assertThat(customerOpt.get(), same(expectedCustomer));
    }

    @Test
    public void read_unexistentCustomer() throws Exception {
        Optional<Customer> customerOpt = customerDao.read(110L);
        assertTrue(!customerOpt.isPresent());
    }

    @Test
    public void read_all() throws Exception {
        List<Customer> result = customerDao.read();
        assertEquals(3, result.size());
    }

    @Test
    public void create() throws Exception {
        Customer customer = new Customer(
                110L,
                "Antonio Silva",
                new Address(310L, "PaddyPower Address"),
                101L);

        Customer persistedCustomer = customerDao.create(customer);

        assertThat(customer, same(persistedCustomer));
    }

    @Test
    public void create_twice() throws Exception {
        Customer customer = new Customer(
                110L,
                "Antonio Silva",
                new Address(310L, "PaddyPower Address"),
                101L);

        customerDao.create(customer);

        thrown.expect(PersistException.class);
        thrown.expectCause(isA(DuplicateKeyException.class));
        customerDao.create(customer);
    }

    @Test
    public void update() throws Exception {
        Customer customer = new Customer(
                101L,
                "Antonio Silva",
                new Address(301L, "PaddyPower Address"),
                103L);

        Customer persistedCustomer = customerDao.update(customer);

        assertThat(customer, same(persistedCustomer));
    }

    @Test
    public void update_inexistent() throws Exception {
        Customer customer = new Customer(
                111L,
                "Antonio Silva",
                new Address(301L, "PaddyPower Address"),
                103L);

        thrown.expect(PersistException.class);
        thrown.expectMessage(startsWith("Customer not found: Customer(id=111,"));
        customerDao.update(customer);
    }

    @Test
    public void delete() throws Exception {
        Customer customer = new Customer(
                110L,
                "Antonio Silva",
                new Address(310L, "PaddyPower Address"),
                101L);
        customerDao.create(customer);

        customerDao.delete(110L);

        Optional<Customer> customerOptional = customerDao.read(110L);
        assertTrue(!customerOptional.isPresent());
    }

    @Test
    public void delete_inexistent() throws Exception {
        customerDao.delete(1000L);
    }
}
