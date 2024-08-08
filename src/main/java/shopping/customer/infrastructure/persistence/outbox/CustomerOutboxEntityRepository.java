package shopping.customer.infrastructure.persistence.outbox;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOutboxEntityRepository extends JpaRepository<CustomerOutboxEntity, String> {

}

