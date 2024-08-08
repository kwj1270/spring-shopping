package shopping.common.domain;

import shopping.common.event.DomainEventPublisher;

import java.util.ArrayList;
import java.util.List;

public class AggregateRoot<T> {
    private final List<DomainEvent> eventStorage;

    public AggregateRoot() {
        this(new ArrayList<>());
    }

    public AggregateRoot(final List<DomainEvent> eventStorage) {
        this.eventStorage = eventStorage;
    }

    protected void addEvent(final DomainEvent domainEvent) {
        eventStorage.add(domainEvent);
    }

    public void publish(final DomainEventPublisher domainEventPublisher) {
        eventStorage.forEach(domainEventPublisher::publish);
    }
}
