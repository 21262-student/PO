public class EmployeeCollectionTester {
    public static void testCollection(EmployeeCollection employees) {
        System.out.println("Initial collection size: " + employees.size());

        Employee newEmployee = createNewEmployee();

        testAddEmployee(employees, newEmployee);
        System.out.println("Added employee details:");
        newEmployee.print();
        System.out.println();

        testRemoveEmployee(employees, newEmployee);
        System.out.println();

        testAddMultipleEmployees(employees);
        System.out.println();

        testSorting(employees);
        System.out.println();

        testClearCollection(employees);
        System.out.println();

        testAddNullEmployee(employees);
        System.out.println();

        testRemoveNullEmployee(employees);
        System.out.println();

        testGetEmployeeAtInvalidIndex(employees, 999);
        System.out.println();

        testAddInvalidEmployees(employees);
        System.out.println();
    }

    private static Employee createNewEmployee() {
        EmployeeFullTime newEmployee = new EmployeeFullTime();
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
        newEmployee.yearsWorking = 30;
        newEmployee.salary = 8000;
        newEmployee.salaryBonus = 500;
        return newEmployee;
    }

    private static void testAddEmployee(EmployeeCollection employees, Employee employee) {
        System.out.println("=== Test: Adding a Valid Employee ===");
        System.out.println("- Adding employee: " + employee.name + " " + employee.surname);
        int initialSize = employees.size();
        System.out.println("- Collection size before: " + initialSize);

        try {
            employees.add(employee);
            int newSize = employees.size();
            System.out.println("- Collection size after: " + newSize);
            int position = employees.findPosition(employee);
            if (newSize == initialSize + 1 && position != -1) {
                System.out.println("- Employee found at position: " + position);
                System.out.println("=== Test: SUCCESS ===");
            } else {
                System.out.println("=== Test: FAILURE === - Employee not added or not found");
            }
        } catch (Exception e) {
            System.out.println("=== Test: FAILURE === - Exception: " + e.getMessage());
        }
    }

    private static void testRemoveEmployee(EmployeeCollection employees, Employee employee) {
        System.out.println("=== Test: Removing an Employee ===");
        System.out.println("- Removing employee: " + employee.name + " " + employee.surname);
        int initialSize = employees.size();
        System.out.println("- Collection size before: " + initialSize);

        boolean removed = employees.remove(employee);
        int newSize = employees.size();
        System.out.println("- Collection size after: " + newSize);
        if (removed && newSize == initialSize - 1) {
            int position = employees.findPosition(employee);
            if (position == -1) {
                System.out.println("- Employee no longer in collection");
                System.out.println("=== Test: SUCCESS ===");
            } else {
                System.out.println("=== Test: FAILURE === - Employee still found at position: " + position);
            }
        } else {
            System.out.println("=== Test: FAILURE === - Remove returned false or size unchanged");
        }
    }

    private static void testAddMultipleEmployees(EmployeeCollection employees) {
        System.out.println("=== Test: Adding Multiple Employees ===");
        int initialSize = employees.size();
        System.out.println("- Collection size before: " + initialSize);

        Employee emp1 = createNewEmployee(); emp1.id = 1002; emp1.name = "Anna"; emp1.address.street = "Cherry";
        Employee emp2 = createNewEmployee(); emp2.id = 1003; emp2.name = "Jan"; emp2.address.street = "Banana";
        Employee emp3 = createNewEmployee(); emp3.id = 1004; emp3.name = "Zofia"; emp3.address.street = "Apple";

        boolean allAdded = true;
        try {
            employees.add(emp1);
            System.out.println("- Added employee 1: " + emp1.name);
            employees.add(emp2);
            System.out.println("- Added employee 2: " + emp2.name);
            employees.add(emp3);
            System.out.println("- Added employee 3: " + emp3.name);
        } catch (Exception e) {
            System.out.println("- Exception caught while adding employees: " + e.getMessage());
            allAdded = false;
        }

        int newSize = employees.size();
        System.out.println("- Collection size after attempting to add 3 employees: " + newSize);

        if (allAdded && newSize == initialSize + 3) {
            System.out.println("=== Test: SUCCESS ===");
        } else {
            System.out.println("=== Test: FAILURE === - " +
                    (allAdded ? "Expected size increase by 3" : "Exception occurred"));
        }
    }

    private static void testSorting(EmployeeCollection employees) {
        System.out.println("=== Test: Sorting Employees ===");
        System.out.println("- Sorting alphabetically by name");
        employees.sortAlphabetically();
        boolean nameSorted = true;
        for (int i = 0; i < employees.size() - 1; i++) {
            String name1 = employees.get(i).name;
            String name2 = employees.get(i + 1).name;
            if (name1.compareTo(name2) > 0) {
                nameSorted = false;
                break;
            }
        }
        System.out.println("- First 3 employees after name sort:");
        for (int i = 0; i < Math.min(3, employees.size()); i++) {
            System.out.println("  " + employees.get(i).name);
        }
        System.out.println(nameSorted ? "=== Test: SUCCESS ===" : "=== Test: FAILURE === - Names not sorted");

        System.out.println("- Sorting alphabetically by street");
        employees.sortStreet();
        boolean streetSorted = true;
        for (int i = 0; i < employees.size() - 1; i++) {
            String street1 = employees.get(i).address.street;
            String street2 = employees.get(i + 1).address.street;
            if (street1.compareTo(street2) > 0) {
                streetSorted = false;
                break;
            }
        }
        System.out.println("- First 3 streets after street sort:");
        for (int i = 0; i < Math.min(3, employees.size()); i++) {
            System.out.println("  " + employees.get(i).address.street);
        }
        System.out.println(streetSorted ? "=== Test: SUCCESS ===" : "=== Test: FAILURE === - Streets not sorted");
    }

    private static void testClearCollection(EmployeeCollection employees) {
        System.out.println("=== Test: Clearing Collection ===");
        int initialSize = employees.size();
        System.out.println("- Collection size before: " + initialSize);
        employees.clear();
        int newSize = employees.size();
        System.out.println("- Collection size after: " + newSize);
        System.out.println(newSize == 0 ? "=== Test: SUCCESS ===" : "=== Test: FAILURE === - Collection not cleared");
    }

    private static void testAddNullEmployee(EmployeeCollection employees) {
        System.out.println("=== Test: Adding Null Employee ===");
        int initialSize = employees.size();
        try {
            employees.add(null);
            System.out.println("=== Test: FAILURE === - Null added unexpectedly");
        } catch (Exception e) {
            System.out.println("- Exception caught: " + e.getMessage());
            System.out.println(employees.size() == initialSize ? "=== Test: SUCCESS ===" : "=== Test: FAILURE === - Collection size changed");
        }
    }

    private static void testRemoveNullEmployee(EmployeeCollection employees) {
        System.out.println("=== Test: Removing Null Employee ===");
        int initialSize = employees.size();
        try {
            boolean removed = employees.remove(null);
            System.out.println("- Remove returned: " + removed);
            System.out.println((!removed && employees.size() == initialSize) ? "=== Test: SUCCESS ===" : "=== Test: FAILURE === - Unexpected removal or size change");
        } catch (Exception e) {
            System.out.println("- Exception caught: " + e.getMessage());
            System.out.println("=== Test: SUCCESS ===");
        }
    }

    private static void testGetEmployeeAtInvalidIndex(EmployeeCollection employees, int index) {
        System.out.println("=== Test: Getting Employee at Invalid Index (" + index + ") ===");
        try {
            Employee employee = employees.get(index);
            System.out.println(employee == null ? "=== Test: SUCCESS ===" : "=== Test: FAILURE === - Returned employee unexpectedly");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("- Expected exception caught: " + e.getMessage());
            System.out.println("=== Test: SUCCESS ===");
        } catch (Exception e) {
            System.out.println("- Unexpected exception: " + e.getMessage());
            System.out.println("=== Test: FAILURE ===");
        }
    }

    private static void testAddInvalidEmployees(EmployeeCollection employees) {
        System.out.println("=== Test: Adding Invalid Employees ===");
        Employee empNullName = createNewEmployee(); empNullName.name = null;
        Employee empNullSurname = createNewEmployee(); empNullSurname.surname = null;
        Employee empInvalidPesel = createNewEmployee(); empInvalidPesel.pesel = "15";
        Employee empNullGender = createNewEmployee(); empNullGender.gender = null;
        Employee empNullPosition = createNewEmployee(); empNullPosition.position = null;
        Employee empNullAddress = createNewEmployee(); empNullAddress.address = null;
        Employee empFutureBirthday = createNewEmployee(); empFutureBirthday.birthdayDate = DataParser.getDate("11.07.2032");
        Employee empFutureEmployment = createNewEmployee(); empFutureEmployment.employmentDate = DataParser.getDate("11.07.2032");

        Object[][] invalidCases = {
                {empNullName, "null name"},
                {empNullSurname, "null surname"},
                {empInvalidPesel, "invalid PESEL"},
                {empNullGender, "null gender"},
                {empNullPosition, "null position"},
                {empNullAddress, "null address"},
                {empFutureBirthday, "future birthday"},
                {empFutureEmployment, "future employment date"}
        };

        for (Object[] caseData : invalidCases) {
            Employee invalidEmployee = (Employee) caseData[0];
            String description = (String) caseData[1];
            System.out.println("- Attempting to add employee with " + description);
            int initialSize = employees.size();
            try {
                employees.add(invalidEmployee);
                System.out.println("=== Test: FAILURE === - Added employee with " + description);
            } catch (Exception e) {
                System.out.println("- Exception caught: " + e.getMessage());
                int newSize = employees.size();
                System.out.println(newSize == initialSize ? "=== Test: SUCCESS ===" : "=== Test: FAILURE === - Collection size changed: " + newSize + " (expected " + initialSize + ")");
            }
        }
    }
}