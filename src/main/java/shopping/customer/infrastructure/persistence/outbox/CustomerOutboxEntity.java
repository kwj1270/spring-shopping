package shopping.customer.infrastructure.persistence.outbox;

import jakarta.persistence.*;
import shopping.common.persistence.BasedEntity;
import shopping.customer.domain.event.CustomerDomainEventType;

import java.time.Instant;
import java.util.Objects;

@Table(name = "customers_outbox")
@Entity
public class CustomerOutboxEntity extends BasedEntity {

    @Id
    private String id;

    @Column(name = "payload")
    private String payload;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private CustomerDomainEventType customerDomainEventType;

    @Column(name = "published")
    private boolean published;

    @Column(name = "published_at")
    private Instant publishedAt;

    public CustomerOutboxEntity() {
    }

    public CustomerOutboxEntity(final String id, final String payload, final CustomerDomainEventType customerDomainEventType, final boolean published, final Instant publishedAt) {
        this.id = id;
        this.payload = payload;
        this.customerDomainEventType = customerDomainEventType;
        this.published = published;
        this.publishedAt = publishedAt;
    }

    public void published() {
        this.published = true;
        this.publishedAt = Instant.now();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CustomerOutboxEntity that = (CustomerOutboxEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
