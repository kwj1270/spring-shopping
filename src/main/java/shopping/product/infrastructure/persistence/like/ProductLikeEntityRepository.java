package shopping.product.infrastructure.persistence.like;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLikeEntityRepository extends JpaRepository<ProductLikeEntity, ProductLikeEntityId> {
}
