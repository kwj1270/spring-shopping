package shopping.category.application;

import shopping.category.application.command.SubCategoryRegistrationCommand;
import shopping.category.application.query.CategoryQuery;
import shopping.common.aspect.MainCategoryLock;

public interface SubCategoryRegistrationUseCase {
    @MainCategoryLock(value = "#command.mainCategoryId()")
    CategoryQuery registerSub(final SubCategoryRegistrationCommand command);
}
