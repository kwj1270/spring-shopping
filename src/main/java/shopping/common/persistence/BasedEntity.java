package shopping.common.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@SQLRestriction("deleted_at = null")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasedEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Instant createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    protected Instant modifiedAt;

    @Column(name = "deleted_at")
    protected Instant deletedAt;

    public void setModifiedAt(final Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setDeletedAt(final Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
