package shopping.customer.domain.repository;

import shopping.customer.domain.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);

    Customer findByEmail(final String email);

    Customer findById(long userId);

    void leave(Customer customer);
}