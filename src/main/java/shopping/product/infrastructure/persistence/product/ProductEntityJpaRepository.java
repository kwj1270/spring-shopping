package shopping.product.infrastructure.persistence.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityJpaRepository extends JpaRepository<ProductEntity, Long> {
}
