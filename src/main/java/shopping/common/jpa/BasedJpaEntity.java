package shopping.common.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasedJpaEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Instant created_at;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    protected Instant modified_at;

    public void setModified_at(final Instant modified_at) {
        this.modified_at = modified_at;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public Instant getModified_at() {
        return modified_at;
    }
}
