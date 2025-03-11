import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeeGenerator
{
    public Employee[] generate()
    {
        Employee[] employees = null;
        List<String> lines = new ArrayList<>();
        try
        {
            lines = Files.readAllLines(Paths.get("data/data2.csv"));
            employees = new Employee[lines.size()];
        }
        catch (IOException e)
        {
            System.out.println("Error reading data");
        }

        if (lines.isEmpty())
            return employees;

        for (int i = 0; i < lines.size(); i++)
        {
            String[] split = lines.get(i).split(",");
            employees[i] = new Employee();
            employees[i].id = i;
            employees[i].name = split[0];
            employees[i].surname = split[1];
            employees[i].pesel = split[2];
            employees[i].gender = split[3];
            employees[i].position = split[4];
            employees[i].birthdayDate = DataParser.getDate(split[5]);
            employees[i].employmentDate = DataParser.getDate(split[6]);
            Address address = new Address();
            address.street = split[7];
            address.number = Integer.parseInt(split[8]);
            address.city = split[9];
            address.postalCode = split[10];
            employees[i].address = address;
        }

        return employees;
    }
}
