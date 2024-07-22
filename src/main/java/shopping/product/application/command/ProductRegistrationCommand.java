package shopping.product.application.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import shopping.common.CommandValidating;

public record ProductRegistrationCommand(
        @Pattern(regexp = "^[a-zA-Z0-9가-힣()+\\[\\]&/_-]*$") @NotBlank @Length(min = 1, max = 15) String name,
        @Min(1) long amount,
        @URL String imageUrl,
        @Min(1) long subCategoryId,
        @Min(1) long shopId,
        @Min(1) long sellerId
) implements CommandValidating<ProductRegistrationCommand> {

    public ProductRegistrationCommand(final String name, final long amount, final String imageUrl, final long subCategoryId, final long shopId, final long sellerId) {
        this.name = name;
        this.amount = amount;
        this.imageUrl = imageUrl;
        this.subCategoryId = subCategoryId;
        this.shopId = shopId;
        this.sellerId = sellerId;
        validateSelf(this);
    }
}
