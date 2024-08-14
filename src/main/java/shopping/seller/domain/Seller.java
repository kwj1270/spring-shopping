package shopping.seller.domain;

import java.util.Objects;

public final class Seller {
    private final Long id;
    private final String email;
    private final String name;
    private final String password;
    private final String birth;
    private final String address;
    private final String phone;

    public Seller(Long id, String email, String name, String password, String birth, String address, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
    }

    public boolean isSamePassword(final String password) {
        return this.password.equals(password);
    }

    public Long id() {
        return id;
    }

    public String email() {
        return email;
    }

    public String name() {
        return name;
    }

    public String password() {
        return password;
    }

    public String birth() {
        return birth;
    }

    public String address() {
        return address;
    }

    public String phone() {
        return phone;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Seller seller = (Seller) o;
        return Objects.equals(id, seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}