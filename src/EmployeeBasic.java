import java.io.Serializable;

public class EmployeeBasic extends Employee implements Serializable {
    @Override
    public void print() {
        System.out.println("Employee: " + name + " " + surname + " (ID: " + id + ")");
        System.out.println("PESEL: " + pesel + ", Gender: " + gender);
        System.out.println("Position: " + position);
        System.out.println("Date of birth: " + birthdayDate + ", Employment date: " + employmentDate);
        System.out.println("Address: " + address.street + " " + address.number + ", " + address.city + " " + address.postalCode);
    }

    @Override
    public int getSalary() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}