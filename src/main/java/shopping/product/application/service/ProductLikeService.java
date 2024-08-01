package shopping.product.application.service;

import org.springframework.stereotype.Service;
import shopping.product.application.ProductLikeUseCase;
import shopping.product.application.command.ProductLikeCommand;
import shopping.product.domain.like.ProductLike;
import shopping.product.domain.like.ProductLikeRepository;

@Service
public class ProductLikeService implements ProductLikeUseCase {

    private final ProductLikeRepository productLikeRepository;

    public ProductLikeService(final ProductLikeRepository productLikeRepository) {
        this.productLikeRepository = productLikeRepository;
    }

    @Override
    public void like(final ProductLikeCommand command) {
        productLikeRepository.save(new ProductLike(command.productId(), command.customerId()));
    }
}
