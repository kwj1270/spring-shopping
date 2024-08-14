package shopping.product.domain.product;

import java.util.List;
import java.util.Objects;

public final class Product {
    private final Long id;
    private final String name;
    private final long amount;
    private final String thumbnailImageUrl;
    private final long subCategoryId;
    private final long shopId;
    private final long sellerId;
    private final List<ProductDetailedImage> detailedImageUrls;

    public Product(
            Long id,
            String name,
            long amount,
            String thumbnailImageUrl,
            long subCategoryId,
            long shopId,
            long sellerId,
            List<ProductDetailedImage> detailedImageUrls
    ) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.subCategoryId = subCategoryId;
        this.shopId = shopId;
        this.sellerId = sellerId;
        this.detailedImageUrls = detailedImageUrls;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public long amount() {
        return amount;
    }

    public String thumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public long subCategoryId() {
        return subCategoryId;
    }

    public long shopId() {
        return shopId;
    }

    public long sellerId() {
        return sellerId;
    }

    public List<ProductDetailedImage> detailedImageUrls() {
        return detailedImageUrls;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
