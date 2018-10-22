package com.pp.example.dao.model.matcher;

import com.pp.example.dao.model.Customer;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class CustomerMatches extends TypeSafeDiagnosingMatcher<Customer> {

    private final Customer c;

    private CustomerMatches(Customer c) {
        this.c = c;
    }

    @Override
    protected boolean matchesSafely(Customer customer, Description description) {
        return validateCustomer(customer, description) && validateAddress(customer, description);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("A customer matches with another");
    }

    public static CustomerMatches same(Customer customer) {
        return new CustomerMatches(customer);
    }

    private boolean validateCustomer(Customer customer, Description description) {
        if (!c.getId().equals(customer.getId())){
            description.appendText("customer.id does not match");
            return false;
        }

        if (!c.getName().equals(customer.getName())) {
            description.appendText("customer.name does not match");
            return false;
        }

        if (c.getReferrerId() == null && customer.getReferrerId() != null ||
            c.getReferrerId() != null && customer.getReferrerId() == null ||
            !c.getReferrerId().equals(customer.getReferrerId())) {
          description.appendText("customer.referrerId does not match");
          return false;
        }

        return true;
    }

    private boolean validateAddress(Customer customer, Description description) {
        if (!c.getAddress().getId().equals(customer.getAddress().getId())) {
            description.appendText("customer.address.id does not match");
            return false;
        }

        if (!c.getAddress().getAddress().equals(c.getAddress().getAddress())) {
            description.appendText("customer.address.address does not match");
            return false;
        }

        return true;
    }
}
