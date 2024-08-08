package shopping.customer.infrastructure.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shopping.common.event.EventQueueFixture;
import shopping.customer.domain.event.CustomerDomainEvent;
import shopping.customer.domain.event.CustomerLeaved;
import shopping.customer.infrastructure.persistence.outbox.CustomerOutboxEntity;
import shopping.customer.infrastructure.persistence.outbox.CustomerOutboxEntityRepository;

import java.io.IOException;

@Component
public class CustomerDomainEventListener {

    private final ObjectMapper objectMapper;
    private final CustomerOutboxEntityRepository customerOutboxEntityRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CustomerDomainEventListener(final ObjectMapper objectMapper, final CustomerOutboxEntityRepository customerOutboxEntityRepository, final KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.customerOutboxEntityRepository = customerOutboxEntityRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    @RabbitListener(queues = EventQueueFixture.CustomersFixture.CUSTOMERS_LEAVED_OUTBOX_QUEUE)
    public void handleRetirementLike(final CustomerDomainEvent customerDomainEvent) {
        final CustomerOutboxEntity customerOutboxEntity = customerOutboxEntityRepository.findById(customerDomainEvent.getId()).orElseThrow(() -> new RuntimeException("Customer outbox not found"));
        customerOutboxEntity.published();
        customerOutboxEntityRepository.save(customerOutboxEntity);
    }

    @Async
    @Transactional
    @RabbitListener(queues = EventQueueFixture.CustomersFixture.CUSTOMERS_LEAVED_EXTERNAL_QUEUE)
    public void handleRetirementExternal(final Message message) throws IOException {
        final CustomerLeaved customerLeaved = objectMapper.readValue(message.getBody(), CustomerLeaved.class);
        final String kafkaMessage = objectMapper.writeValueAsString(customerLeaved);
        kafkaTemplate.send("customers", customerLeaved.getId(), kafkaMessage);
    }
}
