    public interface ICollection<E> {
        void add(E element) throws InvalidEmployeeDataException;
        boolean remove(E element);
        int findPosition(E element);
        E get(int index);
        int size();
        void clear();
    }
