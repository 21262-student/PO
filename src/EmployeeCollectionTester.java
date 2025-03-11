public class EmployeeCollectionTester {
    public static void testCollection(EmployeeCollection employees) {
        System.out.println("=== TEST KOLEKCJI PRACOWNIKÓW ===");

        System.out.println("Początkowa wielkość kolekcji: " + employees.size());

        Employee newEmployee = createNewEmployee();
        testAddEmployee(employees, newEmployee);

        System.out.println("Przykladowy pracownik:");
        newEmployee.Print();

        testFindPosition(employees, newEmployee);

        testGetEmployee(employees, 10);

        testRemoveEmployee(employees, newEmployee);

        testSorting(employees);

        testClearCollection(employees);

        testAddNullEmployee(employees);
        testRemoveNullEmployee(employees);
        testGetEmployeeAtInvalidIndex(employees, 999);
    }

    private static Employee createNewEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.id = 1001;
        newEmployee.name = "Michał";
        newEmployee.surname = "Mazurek";
        newEmployee.pesel = "12312312311";
        newEmployee.gender = "Male";
        newEmployee.position = "Janitor";
        newEmployee.birthdayDate = DataParser.getDate("11.07.2003");
        newEmployee.employmentDate = DataParser.getDate("11.07.2018");
        newEmployee.address = new Address();
        newEmployee.address.city = "Moscow";
        newEmployee.address.postalCode = "12345";
        newEmployee.address.street = "Putin";
        newEmployee.address.number = 12;
        return newEmployee;
    }

    private static void testAddEmployee(EmployeeCollection employees, Employee employee) {
        System.out.println("= TEST DODAWANIA PRACOWNIKA =");
        if (employee != null) {
            employees.add(employee);
            System.out.println("Dodano pracownika.");
        } else {
            System.out.println("Nie można dodać null jako pracownika.");
        }
        System.out.println("Wielkość kolekcji po dodaniu: " + employees.size());
    }

    private static void testFindPosition(EmployeeCollection employees, Employee employee) {
        System.out.println("= TEST ZNAJDOWANIA PRACOWNIKA =");
        long position = employees.findPosition(employee);
        System.out.println("Pozycja pracownika: " + position);
    }

    private static void testGetEmployee(EmployeeCollection employees, int index) {
        System.out.println("= TEST ZWRACANIA PRACOWNIKA =");
        Employee employee = employees.get(index);
        if (employee != null) {
            System.out.println("Pracownik na indeksie " + index + ":");
            employee.Print();
        } else {
            System.out.println("Brak pracownika na indeksie " + index + ".");
        }
    }

    private static void testRemoveEmployee(EmployeeCollection employees, Employee employee) {
        System.out.println("= TEST USUWANIA PRACOWNIKA =");
        boolean removed = (employee != null) ? employees.remove(employee) : false;
        System.out.println("Pracownik usunięty: " + removed);
        System.out.println("Wielkość kolekcji po usunięciu: " + employees.size());
    }

    private static void testSorting(EmployeeCollection employees) {
        System.out.println("= TEST SORTOWANIA PRACOWNIKA =");
        System.out.println("10 pierwszych przykładowych pracowników");
        for (int i = 0; i < 10; i++)
        {
            employees.get(i).Print();
        }

        System.out.println("= SORTOWANIE ALFABETYCZNE PO IMIONACH =");
        employees.sortAlphabetically();
        System.out.println("10 pierwszych przykładowych pracowników");
        for (int i = 0; i < 10; i++)
        {
            employees.get(i).Print();
        }

        System.out.println("= SORTOWANIE ALFABETYCZNE PO NUMERZE ULICY =");
        employees.sortStreet();
        System.out.println("10 pierwszych przykładowych pracowników");
        for (int i = 0; i < 10; i++)
        {
            employees.get(i).Print();
        }
    }

    private static void testClearCollection(EmployeeCollection employees) {
        System.out.println("= TEST CZYSZCZENIA KOLEKCJI =");
        employees.clear();
        System.out.println("Wielkość kolekcji po wyczyszczeniu: " + employees.size());
    }

    private static void testAddNullEmployee(EmployeeCollection employees) {
        System.out.println("= DODAWANIE PRACOWNIKA JAKO NULL =");
        try {
            employees.add(null);
            System.out.println("Dodano null.");
        } catch (Exception e) {
            System.out.println("Błąd przy dodawaniu: " + e.getMessage());
        }
        System.out.println("Wielkość kolekcji: " + employees.size());
    }

    private static void testRemoveNullEmployee(EmployeeCollection employees) {
        System.out.println("= USUWANIE PRACOWNIKA JAKO NULL =");
        try {
            boolean removed = employees.remove(null);
            System.out.println("Usunięcie null: " + removed);
        } catch (Exception e) {
            System.out.println("Błąd przy usuwaniu null: " + e.getMessage());
        }
        System.out.println("Wielkość kolekcji: " + employees.size());
    }

    private static void testGetEmployeeAtInvalidIndex(EmployeeCollection employees, int index) {
        System.out.println("= ZWRACANIE PRACOWNIKA PRZY NIEISTNIEJACYM INDEKSIE =");
        try {
            Employee employee = employees.get(index);
            if (employee != null) {
                System.out.println("Pracownik na indeksie " + index + ":");
                employee.Print();
            } else {
                System.out.println("Brak pracownika na indeksie " + index + ".");
            }
        } catch (Exception e) {
            System.out.println("Błąd przy zwracaniu pracownika: " + e.getMessage());
        }
    }
}
