package shopping.admin.domain;

import java.util.Objects;

public class Admin {
    private final Long id;
    private final String name;
    private final String emai;
    private final String password;

    public Admin(final Long id, final String name, final String emai, final String password) {
        this.id = id;
        this.name = name;
        this.emai = emai;
        this.password = password;
    }

    public boolean isSamePassword(final String password) {
        return this.password.equals(password);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Admin admin = (Admin) o;
        return Objects.equals(id, admin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
