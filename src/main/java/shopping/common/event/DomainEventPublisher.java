package shopping.common.event;

import shopping.common.domain.DomainEvent;

public interface DomainEventPublisher {
    void publish(final DomainEvent domainEvent);
}
