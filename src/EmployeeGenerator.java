import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeeGenerator {
    public Employee[] generate() {
        List<String> lines = readLinesFromFile("data/data2.csv");
        if (lines == null || lines.isEmpty()) {
            System.out.println("No data to process");
            return new Employee[0];
        }

        Employee[] employees = new Employee[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(",");
            if (split.length < 11) {
                System.out.println("Invalid data format at line " + (i + 1));
                continue;
            }
            try {
                Employee employee = createEmployee(i, split);
                employees[i] = employee;
            } catch (Exception e) {
                System.out.println("Error creating employee from line " + (i + 1) + ": " + e.getMessage());
            }
        }
        return employees;
    }

    private List<String> readLinesFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading data from " + filePath + ": " + e.getMessage());
            return null;
        }
    }

    private Employee createEmployee(int id, String[] data) {
        Employee employee = new EmployeeBasic();
        employee.id = id;
        employee.name = data[0].trim();
        employee.surname = data[1].trim();
        employee.pesel = data[2].trim();
        employee.gender = data[3].trim();
        employee.position = data[4].trim();
        employee.birthdayDate = DataParser.getDate(data[5].trim());
        employee.employmentDate = DataParser.getDate(data[6].trim());
        Address address = new Address();
        address.street = data[7].trim();
        address.number = Integer.parseInt(data[8].trim());
        address.city = data[9].trim();
        address.postalCode = data[10].trim();
        employee.address = address;
        return employee;
    }
}