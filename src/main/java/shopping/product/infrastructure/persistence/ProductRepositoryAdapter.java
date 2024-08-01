package shopping.product.infrastructure.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shopping.product.domain.product.Product;
import shopping.product.domain.product.ProductRepository;
import shopping.product.infrastructure.persistence.product.ProductEntity;
import shopping.product.infrastructure.persistence.product.ProductEntityJpaRepository;

import static shopping.product.infrastructure.persistence.product.mapper.ProductEntityMapper.domainToEntity;
import static shopping.product.infrastructure.persistence.product.mapper.ProductEntityMapper.entityToDomain;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductEntityJpaRepository productEntityJpaRepository;

    public ProductRepositoryAdapter(final ProductEntityJpaRepository productEntityJpaRepository) {
        this.productEntityJpaRepository = productEntityJpaRepository;
    }

    @Transactional
    @Override
    public Product save(final Product product) {
        final ProductEntity productEntity = productEntityJpaRepository.save(domainToEntity(product));
        return entityToDomain(productEntity);
    }
}
