package woowacourse.shoppingcart.domain;

public class Customer {

    private final Long id;
    private final String name;
    private final String email;
    private final String password;

    public Customer(final Long id, final String name, final String email, final String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Customer(final String name, final String email, final String password) {
        this(null, name, email, password);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}