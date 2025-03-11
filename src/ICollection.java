    public interface ICollection<E> {
        void add(E element);
        boolean remove(E element);
        long findPosition(E element);
        E get(int index);
        long size();
        void clear();
    }
