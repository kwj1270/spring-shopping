package shopping.product.domain.product;

import java.util.Objects;

public final class ProductDetailedImage {
    private final Long id;
    private final String detailedImageUrl;

    public ProductDetailedImage(
            Long id,
            String detailedImageUrl
    ) {
        this.id = id;
        this.detailedImageUrl = detailedImageUrl;
    }

    public Long id() {
        return id;
    }

    public String detailedImageUrl() {
        return detailedImageUrl;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProductDetailedImage that = (ProductDetailedImage) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
