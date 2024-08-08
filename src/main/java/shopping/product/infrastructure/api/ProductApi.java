package shopping.product.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.common.auth.Authorization;
import shopping.common.auth.AuthorizationType;
import shopping.common.auth.AuthorizationUser;
import shopping.product.application.ProductLikeUseCase;
import shopping.product.application.ProductRegistrationUseCase;
import shopping.product.application.command.ProductLikeCommand;
import shopping.product.application.query.ProductRegistrationQuery;
import shopping.product.infrastructure.api.dto.ProductRegistrationHttpRequest;
import shopping.product.infrastructure.api.dto.ProductRegistrationHttpResponse;
import shopping.customer.infrastructure.event.CustomerDomainEventRabbitMQPublisher;

import java.net.URI;

@RequestMapping("/internal-api/shops")
@RestController
public class ProductApi {

    private final ProductRegistrationUseCase productRegistrationUseCase;
    private final ProductLikeUseCase productLikeUseCase;
    private final CustomerDomainEventRabbitMQPublisher customerDomainEventRabbitMQPublisher;

    public ProductApi(final ProductRegistrationUseCase productRegistrationUseCase, final ProductLikeUseCase productLikeUseCase, final CustomerDomainEventRabbitMQPublisher customerDomainEventRabbitMQPublisher) {
        this.productRegistrationUseCase = productRegistrationUseCase;
        this.productLikeUseCase = productLikeUseCase;
        this.customerDomainEventRabbitMQPublisher = customerDomainEventRabbitMQPublisher;
    }

    @PostMapping("/{shopId}/products")
    public ResponseEntity<ProductRegistrationHttpResponse> registerProduct(
            @PathVariable(name = "shopId") final long shopId,
            @Authorization({AuthorizationType.SELLER}) AuthorizationUser authorizationUser,
            @RequestBody final ProductRegistrationHttpRequest request
    ) {
        final ProductRegistrationQuery productRegistrationQuery = productRegistrationUseCase.register(request.toCommand(shopId, authorizationUser.userId()));
        return ResponseEntity.created(URI.create("/internal-api/shops/" + shopId + "/products/" + productRegistrationQuery.id()))
                .body(new ProductRegistrationHttpResponse(productRegistrationQuery.id()));
    }

    @PostMapping("/{shopId}/products/{productId}/likes")
    public ResponseEntity<Void> likeProduct(
            @PathVariable(name = "productId") final long productId,
            @Authorization({AuthorizationType.CUSTOMER}) AuthorizationUser authorizationUser
    ) {
        productLikeUseCase.like(new ProductLikeCommand(productId, authorizationUser.userId()));
        return ResponseEntity.ok().build();
    }
}
