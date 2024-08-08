package shopping.common.domain;

import java.io.Serializable;

public class DomainEvent implements Serializable {
    private String id;

    public DomainEvent() {
    }

    public DomainEvent(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
