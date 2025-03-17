import java.io.*;
import java.util.*;

public class EmployeeCollection implements ICollection<Employee>, Serializable {
    public static int CAPACITY = 100;
    public int size = 0;
    public Employee[] data;

    public EmployeeCollection(Employee[] data) {
        if (data == null) {
            this.data = new Employee[CAPACITY];
            this.size = 0;
        } else {
            this.data = data;
            this.size = data.length;
        }
    }

    public void sortAlphabetically() {
        Arrays.sort(data, 0, size, Comparator.comparing(e -> e.name));
    }

    public void sortStreet() {
        Arrays.sort(data, 0, size, Comparator.comparing(e -> e.address.street));
    }

    @Override
    public void add(Employee employee) throws InvalidEmployeeDataException
    {
        if (employee == null) {
            throw new NullPointerException("Employee is null");
        }

        if (employee.pesel == null || !employee.pesel.matches("^\\d{11}$")) {
            throw new InvalidEmployeeDataException("Invalid pesel");
        }

        if (employee.birthdayDate == null || employee.birthdayDate.after(new Date())) {
            throw new InvalidEmployeeDataException("Invalid birthday");
        }

        if (employee.employmentDate == null || employee.employmentDate.after(new Date())) {
            throw new InvalidEmployeeDataException("Invalid employment date");
        }

        if (employee.gender == null) {
            throw new InvalidEmployeeDataException("Invalid gender");
        }

        if (employee.position == null) {
            throw new InvalidEmployeeDataException("Invalid position");
        }

        if (employee.name == null) {
            throw new InvalidEmployeeDataException("Invalid first name");
        }

        if (employee.surname == null) {
            throw new InvalidEmployeeDataException("Invalid last name");
        }

        if (employee.address == null) {
            throw new InvalidEmployeeDataException("Invalid address");
        }

        if (size == data.length) {
            int newCapacity = data.length == 0 ? 1 : data.length * 2;
            Employee[] newData = new Employee[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }

        data[size++] = employee;
    }

    @Override
    public boolean remove(Employee employee) {
        if (employee == null) {
            throw new NullPointerException("Employee is null");
        }

        for (int i = 0; i < size; i++) {
            if (data[i] == employee) {
                data[i] = data[size - 1];
                data[size - 1] = null; // Allow garbage collection
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public int findPosition(Employee employee) {
        if (employee == null) {
            throw new NullPointerException("Employee is null");
        }

        for (int i = 0; i < size; i++) {
            if (data[i] == employee) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Employee get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Employee index out of bounds");
        }

        return data[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        data = new Employee[CAPACITY];
    }

    public void saveToTextFile(String filePath) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < size; i++) {
                Employee emp = data[i];
                String type = getEmployeeType(emp);
                String line = type + "," + emp.id + "," + emp.name + "," + emp.surname + "," +
                        emp.pesel + "," + emp.gender + "," + emp.position + "," +
                        DataParser.formatDate(emp.birthdayDate) + "," +
                        DataParser.formatDate(emp.employmentDate) + "," +
                        emp.address.street + "," + emp.address.number + "," +
                        emp.address.city + "," + emp.address.postalCode;

                if (emp instanceof EmployeeContractor contractor) {
                    line += "," + contractor.hourlyRate + "," + contractor.hoursQuantity;
                } else if (emp instanceof EmployeeFullTime fullTime) {
                    line += "," + fullTime.salary + "," + fullTime.salaryBonus + "," + fullTime.yearsWorking;
                }

                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void loadFromTextFile(String filePath) throws IOException {
        List<Employee> loadedEmployees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length < 13) continue;
                String type = split[0];
                Employee emp;

                if ("Basic".equals(type)) {
                    emp = new EmployeeBasic();
                } else if ("Contractor".equals(type)) {
                    emp = new EmployeeContractor();
                } else if ("FullTime".equals(type)) {
                    emp = new EmployeeFullTime();
                } else {
                    continue;
                }

                emp.id = Integer.parseInt(split[1]);
                emp.name = split[2];
                emp.surname = split[3];
                emp.pesel = split[4];
                emp.gender = split[5];
                emp.position = split[6];
                emp.birthdayDate = DataParser.getDate(split[7]);
                emp.employmentDate = DataParser.getDate(split[8]);
                Address address = new Address();
                address.street = split[9];
                address.number = Integer.parseInt(split[10]);
                address.city = split[11];
                address.postalCode = split[12];
                emp.address = address;

                if (emp instanceof EmployeeContractor contractor && split.length >= 15) {
                    contractor.hourlyRate = Integer.parseInt(split[13]);
                    contractor.hoursQuantity = Integer.parseInt(split[14]);
                } else if (emp instanceof EmployeeFullTime fullTime && split.length >= 16) {
                    fullTime.salary = Integer.parseInt(split[13]);
                    fullTime.salaryBonus = Integer.parseInt(split[14]);
                    fullTime.yearsWorking = Integer.parseInt(split[15]);
                }

                loadedEmployees.add(emp);
            }
        }

        data = loadedEmployees.toArray(new Employee[0]);
        size = loadedEmployees.size();
    }

    private String getEmployeeType(Employee emp) {
        if (emp instanceof EmployeeBasic) return "Basic";
        if (emp instanceof EmployeeContractor) return "Contractor";
        if (emp instanceof EmployeeFullTime) return "FullTime";
        return "Unknown";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeCollection that = (EmployeeCollection) o;
        if (size != that.size) return false;
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(data[i], that.data[i])) {
                return false;
            }
        }
        return true;
    }
}