package shopping.wishlist.domain;

import java.util.Objects;

public final class WishList {
    private final Long id;
    private final long productId;
    private final long customerId;

    public WishList(Long id, long productId, long customerId) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
    }

    public Long id() {
        return id;
    }

    public long productId() {
        return productId;
    }

    public long customerId() {
        return customerId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WishList wishList = (WishList) o;
        return Objects.equals(id, wishList.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
