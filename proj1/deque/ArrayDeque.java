package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{

    private T[] array;
    private int size;
    private int front;
    private int back;

    public ArrayDeque() {
        this.size = 0;
        this.array = (T[]) new Object[8];
        this.front = 0;
        this.back = 1;
    }

    @Override
    public void addFirst(T item) {
        if (size >= array.length) {
            resize(array.length * 2);
        }
        array[front] = item;
        front = (front - 1 + array.length) % array.length;
        size ++;
    }

    public void resize(int capity) {
        int length = array.length;
        T[] newArray = (T[]) new Object[capity];
        int beg = front;
        int end = back;
        System.arraycopy(array, (beg + 1) % array.length, newArray, 0, array.length - beg - 1);
        System.arraycopy(array, 0, newArray, array.length - beg - 1, end);
        front = array.length - 1;
        back = array.length - beg - 1 + end;
        array = newArray;
    }

    @Override
    public void addLast(T item) {
        if (size >= array.length) {
            resize(array.length * 2);
        }
        array[back] = item;
        back = (back + 1) % array.length;
        size ++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int beg = (front + 1) % array.length;
        int end = back;
        for (int i = beg; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }
        for (int i = 0; i < end; i++) {
            System.out.println(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        double factor = (double) size / array.length;
        if (this.size > 16 && factor < 0.25) {
            resize(array.length / 2);
        }
        front = (front + 1) % array.length;
        T oldFirst = array[front];
        size --;
        array[front] = null;
        return oldFirst;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        double factor = (double) size / array.length;
        if (this.size > 16 && factor < 0.25) {
            resize(array.length / 2);
        }
        back = (back - 1) % array.length;
        T oldLast = array[back];
        size --;
        array[back] = null;
        return oldLast;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int realIndex = (index + front) % array.length;
        return array[realIndex];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pointer = (front + 1) % array.length;
            @Override
            public boolean hasNext() {
                return pointer != back;
            }

            @Override
            public T next() {
                T item = array[pointer];
                pointer = (pointer + 1) % array.length;
                return item;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque) || size != ((Deque<?>) o).size()) {
            return false;
        }
        int beg = (front + 1) % array.length;
        for (int i = beg; i < array.length;) {
            T item1 = get(i);
            T item2 = ((Deque<T>) o).get(i);
            if (item1 != null && !item1.equals(item2)) {
                return false;
            }
            i = (i + 1) % array.length;
        }
        for (int i = 0; i < back; i++) {
            T item1 = get(i);
            T item2 = ((Deque<T>) o).get(i);
            if (item1 != null && !item1.equals(item2)) {
                return false;
            }
        }
        return true;
    }
}
