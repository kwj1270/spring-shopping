package shopping.customer.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.common.auth.Authorization;
import shopping.common.auth.AuthorizationType;
import shopping.common.auth.AuthorizationUser;
import shopping.customer.application.CustomerRetirementUseCase;
import shopping.customer.application.CustomerSignInUseCase;
import shopping.customer.application.CustomerSignOutUseCase;
import shopping.customer.application.CustomerSignUpUseCase;
import shopping.customer.infrastructure.api.dto.CustomerSignInHttpRequest;
import shopping.customer.infrastructure.api.dto.CustomerSignInHttpResponse;
import shopping.customer.infrastructure.api.dto.CustomerSignUpHttpRequest;

import java.net.URI;

@RequestMapping("/api/customers")
@RestController
public class CustomerApi {

    private final CustomerSignInUseCase customerSignInUseCase;
    private final CustomerSignUpUseCase customerSignUpUseCase;
    private final CustomerSignOutUseCase customerSignOutUseCase;
    private final CustomerRetirementUseCase customerRetirementUseCase;

    public CustomerApi(final CustomerSignInUseCase customerSignInUseCase, final CustomerSignUpUseCase customerSignUpUseCase,
                       final CustomerSignOutUseCase customerSignOutUseCase, final CustomerRetirementUseCase customerRetirementUseCase) {
        this.customerSignInUseCase = customerSignInUseCase;
        this.customerSignUpUseCase = customerSignUpUseCase;
        this.customerSignOutUseCase = customerSignOutUseCase;
        this.customerRetirementUseCase = customerRetirementUseCase;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<URI> signUp(@RequestBody final CustomerSignUpHttpRequest request) {
        customerSignUpUseCase.signUp(request.toCommand());
        return ResponseEntity.created(URI.create("")).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<CustomerSignInHttpResponse> signIn(@RequestBody final CustomerSignInHttpRequest request) {
        final String accessToken = customerSignInUseCase.signIn(request.toCommand());
        return ResponseEntity.ok(new CustomerSignInHttpResponse(accessToken));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(
            @Authorization({AuthorizationType.CUSTOMER}) final AuthorizationUser authorizationUser
    ) {
        customerSignOutUseCase.signOut(authorizationUser.userId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> leave(
            @Authorization({AuthorizationType.CUSTOMER}) final AuthorizationUser authorizationUser
    ) {
        customerRetirementUseCase.leave(authorizationUser.userId());
        return ResponseEntity.ok().build();
    }
}
