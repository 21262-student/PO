public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Employee Collection Tests ===");
        EmployeeCollection employees = initializeEmployeeCollection();
        if (employees != null) {
            runTests(employees);
        }
        System.out.println("=== Tests Completed ===");
    }

    private static EmployeeCollection initializeEmployeeCollection() {
        System.out.println("- Loading employee data...");
        try {
            EmployeeGenerator dataGenerator = new EmployeeGenerator();
            Employee[] data = dataGenerator.generate();
            EmployeeCollection employees = new EmployeeCollection(data);
            System.out.println("- Data loaded successfully!");
            return employees;
        } catch (Exception e) {
            System.err.println("- Failed to load employee data: " + e.getMessage());
            return null;
        }
    }

    private static void runTests(EmployeeCollection employees) {
        System.out.println("\n\n=== Running serialization tests ===");
        testSerialization(employees);

        System.out.println("\n\n=== Running text file save/load tests ===");
        testSaveAndLoadTextFile(employees);

        System.out.println("\n\n=== Running collection functionality tests ===");
        testCollection(employees);
    }

    private static void testCollection(EmployeeCollection employees) {
        EmployeeCollectionTester.testCollection(employees);
    }

    private static void testSerialization(EmployeeCollection employees) {
        EmployeeCollectionSerializationTester.testAll(employees);
    }

    private static void testSaveAndLoadTextFile(EmployeeCollection employees) {
        EmployeeCollectionSerializationTester.testSaveAndLoadTextFile(employees);
    }
}