package shopping.category.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.category.api.dto.CategoryRegistrationHttpRequest;
import shopping.category.api.dto.CategoryRegistrationHttpResponse;
import shopping.category.api.dto.SubCategoryRegistrationHttpRequest;
import shopping.category.application.CategoryRegistrationUseCase;
import shopping.category.application.SubCategoryRegistrationUseCase;
import shopping.category.domain.Category;
import shopping.common.auth.Authorization;
import shopping.common.auth.AuthorizationType;
import shopping.common.auth.AuthorizationUser;

import java.net.URI;

@RequestMapping("/api/categories")
@RestController
public class CategoryApi {

    private final CategoryRegistrationUseCase categoryRegistrationUseCase;
    private final SubCategoryRegistrationUseCase subCategoryRegistrationUseCase;

    public CategoryApi(final CategoryRegistrationUseCase categoryRegistrationUseCase, final SubCategoryRegistrationUseCase subCategoryRegistrationUseCase) {
        this.categoryRegistrationUseCase = categoryRegistrationUseCase;
        this.subCategoryRegistrationUseCase = subCategoryRegistrationUseCase;
    }

    @PostMapping
    public ResponseEntity<CategoryRegistrationHttpResponse> register(
            @RequestBody final CategoryRegistrationHttpRequest request,
            @Authorization({AuthorizationType.ADMIN}) final AuthorizationUser admin
    ) {
        final Category category = categoryRegistrationUseCase.register(request.toCommand(admin.getUserId()));
        return ResponseEntity.created(URI.create("/api/categories/" + category.id()))
                .body(new CategoryRegistrationHttpResponse(category.id()));
    }

    @PostMapping("/{categoryId}/sub")
    public ResponseEntity<?> registerSubCategory(
            @PathVariable(name = "categoryId") final long categoryId,
            @RequestBody final SubCategoryRegistrationHttpRequest request,
            @Authorization({AuthorizationType.ADMIN}) final AuthorizationUser admin
    ) {
        final Category category = subCategoryRegistrationUseCase.registerSub(request.toCommand(categoryId, admin.getUserId()));
        return ResponseEntity.created(URI.create("/api/categories/" + category.id())).build();
    }
}
