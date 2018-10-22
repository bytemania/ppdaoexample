package com.pp.example.dao.dao.impl;

import com.pp.example.dao.dao.CustomerDao;
import com.pp.example.dao.model.Address;
import com.pp.example.dao.model.Customer;
import com.pp.example.dao.model.matcher.CustomerMatches;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.pp.example.dao.model.matcher.CustomerMatches.same;
import static org.junit.Assert.*;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDaoImplTest {

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
}
