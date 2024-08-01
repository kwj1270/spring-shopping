package shopping.product.application;

import shopping.product.application.command.ProductLikeCommand;

public interface ProductLikeUseCase {
    void like(ProductLikeCommand command);
}
