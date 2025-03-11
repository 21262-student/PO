import java.io.*;

public class Main {

    public static void main(String[] args)
    {
        System.out.println("Generowanie danych...");
        EmployeeGenerator dataGenerator = new EmployeeGenerator();
        Employee[] data = dataGenerator.generate();
        EmployeeCollection employees = new EmployeeCollection(data);
        System.out.println("Dane wygenerowane.");

        testSerialization(employees);
    }

    private static void testCollection(EmployeeCollection employees)
    {
        EmployeeCollectionTester.testCollection(employees);
    }

    private static void testSerialization(EmployeeCollection employees)
    {
        EmployeeCollectionSerializationTester.testDeserialization(employees);
        employees = EmployeeCollectionSerializationTester.testSerialization();
        testCollection(employees);
    }
}
