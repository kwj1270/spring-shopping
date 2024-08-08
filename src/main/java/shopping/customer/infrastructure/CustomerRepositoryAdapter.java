package shopping.customer.infrastructure;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shopping.customer.domain.Customer;
import shopping.customer.infrastructure.event.CustomerDomainEventPublisher;
import shopping.customer.domain.repository.CustomerRepository;
import shopping.customer.infrastructure.persistence.CustomerEntity;
import shopping.customer.infrastructure.persistence.CustomerEntityJpaRepository;

import java.time.Instant;

import static shopping.customer.infrastructure.CustomerEntityMapper.domainToEntity;
import static shopping.customer.infrastructure.CustomerEntityMapper.entityToDomain;

@Component
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerEntityJpaRepository repository;
    private final CustomerDomainEventPublisher customerDomainEventPublisher;

    public CustomerRepositoryAdapter(final CustomerEntityJpaRepository repository,
                                     final CustomerDomainEventPublisher customerDomainEventPublisher) {
        this.repository = repository;
        this.customerDomainEventPublisher = customerDomainEventPublisher;
    }

    @Transactional
    @Override
    public Customer save(final Customer customer) {
        final CustomerEntity customerEntity = repository.save(domainToEntity(customer));
        return entityToDomain(customerEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer findByEmail(final String email) {
        return repository.findByEmail(email)
                .map(CustomerEntityMapper::entityToDomain)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Customer findById(final long userId) {
        return repository.findById(userId)
                .map(CustomerEntityMapper::entityToDomain)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    @Override
    public void leave(final Customer customer) {
        final CustomerEntity customerEntity = domainToEntity(customer);
        customerEntity.setDeletedAt(Instant.now());
        repository.save(customerEntity);
        customer.leave().publish(customerDomainEventPublisher);
    }
}
