import java.io.Serializable;
import java.util.Objects;

public class EmployeeFullTime extends Employee implements Serializable {
    public int salary;
    public int salaryBonus;
    public int yearsWorking;

    public EmployeeFullTime() {
        salary = 0;
        salaryBonus = 0;
        yearsWorking = 0;
    }

    @Override
    public int getSalary() {
        return salary + salaryBonus;
    }

    @Override
    public void print() {
        System.out.println("Employee: " + name + " " + surname + " (ID: " + id + ")");
        System.out.println("PESEL: " + pesel + ", Gender: " + gender);
        System.out.println("Position: " + position);
        System.out.println("Date of birth: " + birthdayDate + ", Employment date: " + employmentDate);
        System.out.println("Address: " + address.street + " " + address.number + ", " + address.city + " " + address.postalCode);
        System.out.println("Salary: " + salary + ", Bonus: " + salaryBonus + ", Years worked: " + yearsWorking + ", Total salary: " + getSalary());
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        EmployeeFullTime that = (EmployeeFullTime) o;
        return salary == that.salary &&
                salaryBonus == that.salaryBonus &&
                yearsWorking == that.yearsWorking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, salaryBonus, yearsWorking);
    }
}