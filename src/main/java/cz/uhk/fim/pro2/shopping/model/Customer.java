package cz.uhk.fim.pro2.shopping.model;

/**
 * Modelova trida predstavujici Zakaznika
 */
public class Customer {
    private int customerId;
    private double budget;
    private String username;
    private String email;
    private String password;
    private Address address;
    private ShoppingCart cart;
    private boolean vatPayer;

    public Customer() {
    }

    public Customer(int customerId, double budget, String username, String email, String password, Address address, ShoppingCart cart, boolean vatPayer) {
        this.customerId = customerId;
        this.budget = budget;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.cart = cart;
        this.vatPayer = vatPayer;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public boolean isVatPayer() {
        return vatPayer;
    }

    public void setVatPayer(boolean vatPayer) {
        this.vatPayer = vatPayer;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", budget=" + budget +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", cart=" + cart +
                ", vatPayer=" + vatPayer +
                '}';
    }
}
