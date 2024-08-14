package shopping.shop.domain;

import java.util.Objects;

public final class Shop {
    private final Long id;
    private final long sellerId;
    private final String name;

    public Shop(Long id, long sellerId, String name) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
    }

    public Long id() {
        return id;
    }

    public long sellerId() {
        return sellerId;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Shop shop = (Shop) o;
        return Objects.equals(id, shop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
