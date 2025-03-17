import java.io.*;

public class EmployeeCollectionSerializationTester {
    public static void testAll(EmployeeCollection collection) {
        System.out.println("=== Test: Serialization and Deserialization ===");
        new File("data").mkdirs();
        System.out.println("- Serializing collection to data/objects.bin");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("data/objects.bin"))) {
            outputStream.writeObject(collection);
            System.out.println("- Serialization: SUCCESS");
        } catch (IOException e) {
            System.out.println("- Serialization: FAILURE - IOException: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("- Serialization: FAILURE - Unexpected error: " + e.getMessage());
            return;
        }
        System.out.println("- Deserializing collection from data/objects.bin");
        EmployeeCollection deserializedCollection = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("data/objects.bin"))) {
            deserializedCollection = (EmployeeCollection) inputStream.readObject();
            System.out.println("- Deserialization: SUCCESS");
        } catch (IOException e) {
            System.out.println("- Deserialization: FAILURE - IOException: " + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("- Deserialization: FAILURE - Class not found: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("- Deserialization: FAILURE - Unexpected error: " + e.getMessage());
            return;
        }
        System.out.println("- Comparing original and deserialized collections");
        if (collection.equals(deserializedCollection)) {
            System.out.println("=== Test: SUCCESS ===");
        } else {
            System.out.println("=== Test: FAILURE === - Collections differ in content");
        }
    }

    public static void testSaveAndLoadTextFile(EmployeeCollection original) {
        System.out.println("=== Test: Save and Load Text File ===");
        new File("data").mkdirs();
        String filePath = "data/employees.txt";
        try {
            original.saveToTextFile(filePath);
            System.out.println("- Saved to " + filePath);
            EmployeeCollection loaded = new EmployeeCollection(null);
            loaded.loadFromTextFile(filePath);
            System.out.println("- Loaded from " + filePath);
            if (original.equals(loaded)) {
                System.out.println("=== Test: SUCCESS ===");
            } else {
                System.out.println("=== Test: FAILURE === - Loaded collection differs");
            }
        } catch (IOException e) {
            System.out.println("=== Test: FAILURE === - IO Error: " + e.getMessage());
        }
    }
}