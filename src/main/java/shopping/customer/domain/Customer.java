package shopping.customer.domain;

import shopping.common.domain.AggregateRoot;
import shopping.customer.domain.event.CustomerLeaved;

public class Customer extends AggregateRoot<Customer> {

    private final Long id;
    private final String email;
    private final String name;
    private final String password;
    private final String birth;
    private final String address;
    private final String phone;

    public Customer(final Long id, final String email, final String name,
                    final String password, final String birth, final String address, final String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
    }

    public Customer leave() {
        assert id() != null;
        addEvent(new CustomerLeaved(id));
        return this;
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
}
