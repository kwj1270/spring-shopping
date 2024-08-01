package shopping.product.application.command;

import jakarta.validation.constraints.Min;
import shopping.common.CommandValidating;

public record ProductLikeCommand(
        @Min(1) long productId,
        @Min(1) long customerId
) implements CommandValidating<ProductLikeCommand> {
    public ProductLikeCommand(final long productId, final long customerId) {
        this.productId = productId;
        this.customerId = customerId;
        validateSelf(this);
    }
}
