package shopping.product.infrastructure.persistence.like;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
class ProductLikeEntityId implements Serializable {

    @Column(name = "product_id")
    private long productId;

    @Column(name = "customer_id")
    private long customerId;

    public ProductLikeEntityId() {
    }

    public ProductLikeEntityId(final long productId, final long customerId) {
        this.productId = productId;
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public long getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProductLikeEntityId that = (ProductLikeEntityId) o;
        return getProductId() == that.getProductId() && getCustomerId() == that.getCustomerId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getCustomerId());
    }
}