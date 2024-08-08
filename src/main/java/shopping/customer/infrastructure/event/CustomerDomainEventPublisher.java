package shopping.customer.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import shopping.common.domain.DomainEvent;
import shopping.common.event.DomainEventPublisher;

@Component
public class CustomerDomainEventPublisher implements DomainEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomerDomainEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(final DomainEvent domainEvent) {
        applicationEventPublisher.publishEvent(domainEvent);
    }
}
