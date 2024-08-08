package shopping.customer.infrastructure.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import shopping.customer.domain.event.CustomerDomainEvent;
import shopping.customer.domain.event.CustomerLeaved;
import shopping.customer.infrastructure.persistence.outbox.CustomerOutboxEntity;
import shopping.customer.infrastructure.persistence.outbox.CustomerOutboxEntityRepository;

@Component
public class CustomerDomainEventHandler {

    private final CustomerOutboxEntityRepository customerOutboxEntityRepository;
    private final CustomerDomainEventRabbitMQPublisher customerDomainEventRabbitMQPublisher;
    private final ObjectMapper objectMapper;

    public CustomerDomainEventHandler(final CustomerOutboxEntityRepository customerOutboxEntityRepository,
                                      final CustomerDomainEventRabbitMQPublisher customerDomainEventRabbitMQPublisher,
                                      final ObjectMapper objectMapper) {
        this.customerOutboxEntityRepository = customerOutboxEntityRepository;
        this.customerDomainEventRabbitMQPublisher = customerDomainEventRabbitMQPublisher;
        this.objectMapper = objectMapper;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void persistOutBox(final CustomerDomainEvent customerDomainEvent) throws JsonProcessingException {
        final String payload = objectMapper.writeValueAsString(customerDomainEvent);
        final CustomerOutboxEntity customerOutboxEntity = new CustomerOutboxEntity(
                customerDomainEvent.getId(),
                payload,
                customerDomainEvent.getCustomerDomainEventType(),
                false,
                null
        );
        customerOutboxEntityRepository.save(customerOutboxEntity);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void leaved(final CustomerLeaved customerLeaved) throws JsonProcessingException {
        customerDomainEventRabbitMQPublisher.publish(customerLeaved);
    }
}
