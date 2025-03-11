import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

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

    public void sortAlphabetically()
    {
        Arrays.sort(data, Comparator.comparing(e -> e.name));
    }

    public void sortStreet()
    {
        Arrays.sort(data, Comparator.comparing(e -> e.address.number));
    }

    @Override
    public void add(Employee employee) {
        if (employee == null) {
            throw new NullPointerException("Employee is null");
        }

        if (size == data.length) {
            Employee[] newData = new Employee[data.length + 1];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }

        data[size++] = employee;
    }

    @Override
    public boolean remove(Employee employee) {
        if (employee == null || data == null) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (data[i] == employee) {
                Employee temp = data[i];
                data[i] = data[size - 1];
                data[size - 1] = temp;
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public long findPosition(Employee employee) {
        if (employee == null || data == null) {
            return -1;
        }

        for (int i = 0; i < size; i++) {
            if (data[i] == employee)
                return i;
        }

        return -1;
    }

    @Override
    public Employee get(int index) {
        if (index < 0 || index >= size)
            return null;

        return data[index];
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        data = new Employee[CAPACITY];
    }
}
