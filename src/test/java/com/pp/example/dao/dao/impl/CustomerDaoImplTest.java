package com.pp.example.dao.dao.impl;

import com.pp.example.dao.dao.CustomerDao;
import com.pp.example.dao.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDaoImplTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void read_persistedCustomer() throws Exception {
        Optional<Customer> customerOpt = customerDao.read(102L);
        assertTrue(customerOpt.isPresent());
        assertTrue(true);

    }

    @Test
    public void read_unexistentCustomer() throws Exception {
        Optional<Customer> customer = customerDao.read(110L);
        assertTrue(!customer.isPresent());
    }

    //TODO AS - CREATE MATCHERS
    private boolean match(Customer c1, Customer c2) {
        return c1.getId().equals(c2.getId()) &&
        c1.getName().equals(c2.getAddress()) &&
        c1.getReferrerId().equals(c2.getReferrerId()) &&
        c1.getAddress().getId().equals(c2.getAddress().getId()) &&
        c1.getAddress().getAddress().equals(c2.getAddress().getAddress());
    }

}
