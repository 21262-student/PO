import java.io.*;

public class EmployeeCollectionSerializationTester {
    public static void testDeserialization(EmployeeCollection collection)
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("objects.bin"))) {
            outputStream.writeObject(collection);
        }
        catch (IOException e)
        {
            System.out.println("Blad deserializacji");
        }
    }

    public static EmployeeCollection testSerialization()
    {
        EmployeeCollection employees = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("objects.bin"))) {
            employees = (EmployeeCollection)inputStream.readObject();
        }
        catch (IOException e)
        {
            System.out.println("Blad deserializacji");
        } catch (ClassNotFoundException e) {
            System.out.println("Blad deserializacji");
        }

        return employees;
    }
}
