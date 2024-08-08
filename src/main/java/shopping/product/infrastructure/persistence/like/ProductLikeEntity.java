package shopping.product.infrastructure.persistence.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import shopping.common.persistence.BasedEntity;

import java.util.Objects;

@Table(name = "product_likes")
@Entity
public class ProductLikeEntity extends BasedEntity {

    @EmbeddedId
    private ProductLikeEntityId id;

    public ProductLikeEntity() {
    }

    public ProductLikeEntity(final long productId, final long customerId) {
        this(new ProductLikeEntityId(productId, customerId));
    }

    public ProductLikeEntity(final ProductLikeEntityId id) {
        this.id = id;
    }

    public ProductLikeEntityId getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProductLikeEntity that = (ProductLikeEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
