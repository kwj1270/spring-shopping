package shopping.product.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductInfo {
    @JsonProperty("product_name")
    private String productName;
    private long amount;
    @JsonProperty("thumbnail_image_url")
    private String thumbnailImageUrl;
    @JsonProperty("like_count")
    private long likeCount;

    public ProductInfo() {
    }

    public ProductInfo(final String productName, final long amount, final String thumbnailImageUrl, final long likeCount) {
        this.productName = productName;
        this.amount = amount;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.likeCount = likeCount;
    }

    public String getProductName() {
        return productName;
    }

    public long getAmount() {
        return amount;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public long getLikeCount() {
        return likeCount;
    }
}
