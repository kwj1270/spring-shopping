package shopping.product.domain.like;

import java.util.Objects;

public final class ProductLike {
    private final long productId;
    private final long customerId;

    public ProductLike(
            long productId,
            long customerId
    ) {
        this.productId = productId;
        this.customerId = customerId;
    }

    public long productId() {
        return productId;
    }

    public long customerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ProductLike) obj;
        return this.productId == that.productId &&
                this.customerId == that.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customerId);
    }
}
