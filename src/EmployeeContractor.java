import java.io.Serializable;
import java.util.Objects;

public class EmployeeContractor extends Employee implements Serializable {
    public int hourlyRate;
    public int hoursQuantity;

    public EmployeeContractor() {
        hourlyRate = 0;
        hoursQuantity = 0;
    }

    @Override
    public int getSalary() {
        return hourlyRate * hoursQuantity;
    }

    @Override
    public void print() {
        System.out.println("Employee: " + name + " " + surname + " (ID: " + id + ")");
        System.out.println("PESEL: " + pesel + ", Gender: " + gender);
        System.out.println("Position: " + position);
        System.out.println("Date of birth: " + birthdayDate + ", Employment date: " + employmentDate);
        System.out.println("Address: " + address.street + " " + address.number + ", " + address.city + " " + address.postalCode);
        System.out.println("Hourly rate: " + hourlyRate + ", Hours worked: " + hoursQuantity + ", Salary: " + getSalary());
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        EmployeeContractor that = (EmployeeContractor) o;
        return hourlyRate == that.hourlyRate &&
                hoursQuantity == that.hoursQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hourlyRate, hoursQuantity);
    }
}