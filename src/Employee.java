import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class Employee implements Serializable {
    public int id;
    public String name;
    public String surname;
    public String pesel;
    public String gender;
    public String position;
    public Date birthdayDate;
    public Date employmentDate;
    public Address address;

    public Employee() {
        id = 0;
        address = new Address();
        birthdayDate = new Date();
        employmentDate = new Date();
    }

    public abstract void print();
    public abstract int getSalary();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname) &&
                Objects.equals(pesel, employee.pesel) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(birthdayDate, employee.birthdayDate) &&
                Objects.equals(employmentDate, employee.employmentDate) &&
                Objects.equals(address, employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, pesel, gender, position, birthdayDate, employmentDate, address);
    }
}
