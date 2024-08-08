package shopping.customer.domain.event;

import shopping.common.domain.DomainEvent;

public class CustomerDomainEvent extends DomainEvent {
    private CustomerDomainEventType customerDomainEventType;

    public CustomerDomainEvent() {
    }

    public CustomerDomainEvent(final String id, final CustomerDomainEventType customerDomainEventType) {
        super(id);
        this.customerDomainEventType = customerDomainEventType;
    }

    public CustomerDomainEventType getCustomerDomainEventType() {
        return customerDomainEventType;
    }
}
