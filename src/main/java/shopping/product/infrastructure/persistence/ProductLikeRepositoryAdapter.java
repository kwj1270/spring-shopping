package shopping.product.infrastructure.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shopping.product.domain.like.ProductLike;
import shopping.product.domain.like.ProductLikeRepository;
import shopping.product.infrastructure.persistence.like.ProductLikeEntity;
import shopping.product.infrastructure.persistence.like.ProductLikeEntityRepository;

@Component
public class ProductLikeRepositoryAdapter implements ProductLikeRepository {

    private final ProductLikeEntityRepository productLikeEntityRepository;

    public ProductLikeRepositoryAdapter(final ProductLikeEntityRepository productLikeEntityRepository) {
        this.productLikeEntityRepository = productLikeEntityRepository;
    }

    @Transactional
    @Override
    public ProductLike save(final ProductLike productLike) {
        productLikeEntityRepository.save(new ProductLikeEntity(productLike.productId(), productLike.customerId()));
        return productLike;
    }
}
