package shopping.category.domain;

import java.util.List;
import java.util.Objects;

public final class Category {
    private final Long id;
    private final String name;
    private final int order;
    private final List<SubCategory> subCategories;
    private final long createdBy;
    private final long modifiedBy;

    public Category(
            Long id,
            String name,
            int order,
            List<SubCategory> subCategories,
            long createdBy,
            long modifiedBy
    ) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.subCategories = subCategories;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public void addSubCategory(final String name, final int order, final long adminId) {
        subCategories.add(new SubCategory(null, name, order, adminId, adminId));
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int order() {
        return order;
    }

    public List<SubCategory> subCategories() {
        return subCategories;
    }

    public long createdBy() {
        return createdBy;
    }

    public long modifiedBy() {
        return modifiedBy;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
