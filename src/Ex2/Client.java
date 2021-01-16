package Ex2;

import java.util.Objects;

public class Client {
    private int ID;
    private String name;
    private String lastName;
    private String country;
    private double shoppingAmount;

    public Client(int ID, String name, String lastName, String country, double shoppingAmount) {
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.shoppingAmount = shoppingAmount;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getShoppingAmount() {
        return shoppingAmount;
    }

    public void setShoppingAmount(double shoppingAmount) {
        this.shoppingAmount = shoppingAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return ID == client.ID &&
                Double.compare(client.shoppingAmount, shoppingAmount) == 0 &&
                Objects.equals(name, client.name) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(country, client.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, lastName, country, shoppingAmount);
    }

    @Override
    public String toString() {
        return
                ID + ": " +
                name + " " +
                lastName + ", " +
                country + ", " +
                shoppingAmount;
    }
}
