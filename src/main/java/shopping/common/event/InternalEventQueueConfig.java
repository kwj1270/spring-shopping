package shopping.common.event;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

import java.util.Map;

import static shopping.common.event.EventQueueFixture.X_DEAD_LETTER_EXCHANGE;
import static shopping.common.event.EventQueueFixture.X_DEAD_LETTER_ROUTING_KEY;


@EnableRabbit
@Configuration
public class InternalEventQueueConfig {

    private final CachingConnectionFactory cachingConnectionFactory;

    public InternalEventQueueConfig(final CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @ConditionalOnMissingBean
    @Bean(name = "customerDeclarables")
    public Declarables customerDeclarables() {
        final Queue customerLeavedOutboxQueue = customerLeavedOutboxQueue();
        final Queue customerLeavedExternalQueue = customerLeavedExternalQueue();
        final FanoutExchange fanoutExchange = new FanoutExchange(EventQueueFixture.CustomersFixture.CUSTOMERS_EXCHANGE_FANOUT);
        final Binding customerRetirementLikeBinding = BindingBuilder.bind(customerLeavedOutboxQueue).to(fanoutExchange);
        final Binding customerRetirementTempBinding = BindingBuilder.bind(customerLeavedExternalQueue).to(fanoutExchange);
        return new Declarables(
                customerLeavedOutboxQueue,
                customerLeavedExternalQueue,
                fanoutExchange,
                customerRetirementLikeBinding,
                customerRetirementTempBinding
        );
    }

    private Queue customerLeavedOutboxQueue() {
        return new Queue(EventQueueFixture.CustomersFixture.CUSTOMERS_LEAVED_OUTBOX_QUEUE,
                true,
                false,
                false,
                arguments(
                        EventQueueFixture.CustomersFixture.CUSTOMERS_EXCHANGE_DLX,
                        EventQueueFixture.CustomersFixture.CUSTOMERS_QUEUE_DLT
                )
        );
    }

    private Queue customerLeavedExternalQueue() {
        return new Queue(EventQueueFixture.CustomersFixture.CUSTOMERS_LEAVED_EXTERNAL_QUEUE,
                true,
                false,
                false,
                arguments(
                        EventQueueFixture.CustomersFixture.CUSTOMERS_EXCHANGE_DLX,
                        EventQueueFixture.CustomersFixture.CUSTOMERS_QUEUE_DLT
                )
        );
    }



    private Map<String, Object> arguments(final String exchange, final String routingKey) {
        return Map.of(
                X_DEAD_LETTER_EXCHANGE, exchange,
                X_DEAD_LETTER_ROUTING_KEY, routingKey
        );
    }

    @ConditionalOnMissingBean
    @Bean(name = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter messageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setConfirmCallback(confirmCallBack());
        return rabbitTemplate;
    }

    @ConditionalOnMissingBean
    @Bean(name = "messageConverter")
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @ConditionalOnMissingBean
    @Bean(name = "retryInterceptor")
    public RetryOperationsInterceptor retryInterceptor() {
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(3)
                .backOffOptions(2000, 2.0, 100000)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }

    private ConfirmCallback confirmCallBack() {
        return (correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("Message received");
                return;
            }
            final ReturnedMessage returnedMessage = correlationData.getReturned();
            System.out.println(returnedMessage);
        };
    }
}
