package shopping.category.application;

import org.springframework.stereotype.Service;
import shopping.category.application.command.CategoryRegistrationCommand;
import shopping.category.application.command.SubCategoryRegistrationCommand;
import shopping.category.application.query.CategoryQuery;
import shopping.category.domain.Category;
import shopping.category.domain.repository.CategoryRepository;
import shopping.common.aspect.MainCategoryLock;

import java.util.List;

@Service
public class CategoryService implements CategoryRegistrationUseCase, SubCategoryRegistrationUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryQuery register(final CategoryRegistrationCommand command) {
        final Category category = categoryRepository.save(init(command));
        return new CategoryQuery(category);
    }

    @MainCategoryLock(value = "#command.mainCategoryId()")
    @Override
    public CategoryQuery registerSub(final SubCategoryRegistrationCommand command) {
        final Category category = categoryRepository.findById(command.mainCategoryId());
        category.addSubCategory(command.name(), command.order(), command.adminId());
        final Category updatedCategory = categoryRepository.save(category);
        return new CategoryQuery(updatedCategory);
    }

    private Category init(final CategoryRegistrationCommand command) {
        return new Category(
                null,
                command.name(),
                command.order(),
                List.of(),
                command.adminId(),
                command.adminId()
        );
    }
}
