package shopping.category.domain;

import java.util.Objects;

public final class SubCategory {
    private final Long id;
    private final String name;
    private final long order;
    private final long createdBy;
    private final long modifiedBy;

    public SubCategory(Long id, String name, long order, long createdBy, long modifiedBy) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public long order() {
        return order;
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
        final SubCategory that = (SubCategory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
