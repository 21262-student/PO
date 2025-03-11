import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    public int id;
    public String name;
    public String surname;
    public String pesel;
    public String gender;
    public String position;
    public Date birthdayDate;
    public Date employmentDate;
    public Address address;

    public void Print() {

        System.out.println(name + " " + surname + " (" + id + ")" + " " + pesel + " " + gender);
        System.out.println("Stanowisko: " + position +
                " | Data urodzenia: " + birthdayDate + " | Data zatrudnienia: " + employmentDate +
                " | Adres: " + address.street + " " + address.number + ", " + address.city + " " + address.postalCode);
    }
};
