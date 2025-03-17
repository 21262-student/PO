import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    public String street;
    public int number;
    public String city;
    public String postalCode;

    public Address() {
        street = "";
        number = 0;
        city = "";
        postalCode = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city, postalCode);
    }
}