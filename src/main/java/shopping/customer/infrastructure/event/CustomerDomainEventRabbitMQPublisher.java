package shopping.customer.infrastructure.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import shopping.common.event.EventQueueFixture;
import shopping.customer.domain.event.CustomerLeaved;

@Component
public class CustomerDomainEventRabbitMQPublisher {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public CustomerDomainEventRabbitMQPublisher(final ObjectMapper objectMapper, final RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(final CustomerLeaved customerLeaved) throws JsonProcessingException {
        final Message message = new Message(objectMapper.writeValueAsBytes(customerLeaved));
        rabbitTemplate.send(EventQueueFixture.CustomersFixture.CUSTOMERS_EXCHANGE_FANOUT, "", message);
    }
}
