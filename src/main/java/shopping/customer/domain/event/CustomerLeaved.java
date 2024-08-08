package shopping.customer.domain.event;

import java.util.UUID;

public class CustomerLeaved extends CustomerDomainEvent {
    private Long customerId;

    public CustomerLeaved() {
    }

    public CustomerLeaved(final Long customerId) {
        this(customerId, UUID.randomUUID().toString(), CustomerDomainEventType.LEAVED);
    }

    private CustomerLeaved(final Long customerId,
                           final String id,
                           final CustomerDomainEventType customerDomainEventType) {
        super(id, customerDomainEventType);
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
