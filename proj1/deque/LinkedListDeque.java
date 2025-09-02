package deque;

import java.util.Iterator;

public class LinkedListDeque<T>  implements Deque<T>{

    private Node<T> sentinel ;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public class Node<T> {
        T item = (T) new Object();
        Node<T> next;
        Node<T> pre;

        public Node() {
            item = null;
            next = null;
            pre = null;
        }

        public Node(T item, Node<T> pre, Node<T> next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    @Override
    public void addFirst(T item) {
        Node<T> oldFirst = sentinel.next;
        Node<T> first = new Node<>(item, sentinel, oldFirst);
        oldFirst.pre = first;
        sentinel.next = first;
        size ++;
    }

    @Override
    public void addLast(T item) {
        Node<T> oldLast = sentinel.pre;
        Node<T> last = new Node<>(item, oldLast, sentinel);
        sentinel.pre = last;
        oldLast.next = last;
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
        Node<T> pointer = sentinel.next;
        while(pointer != sentinel) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> oldFirst = sentinel.next;
        sentinel.next = oldFirst.next;
        oldFirst.next.pre = sentinel;
        oldFirst.pre = null;
        oldFirst.next = null;
        size --;
        return oldFirst.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node<T> oldLast = sentinel.pre;
        sentinel.pre = oldLast.pre;
        oldLast.pre.next = sentinel;
        oldLast.pre = null;
        oldLast.next = null;
        size --;
        return oldLast.item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node<T> pointer = sentinel.next;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> pointer = sentinel.next;
            @Override
            public boolean hasNext() {
                return pointer != null;
            }

            @Override
            public T next() {
                T item = pointer.item;
                pointer = pointer.next;
                return item;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque) || size != ((Deque<T>) o).size()) {
            return false;
        }
        int oSize = ((Deque<T>) o).size();
        int maxSize = Math.max(size, oSize);
        for (int i = 0; i < maxSize; i++) {
            T item1 = this.get(i);
            T item2 = ((Deque<T>) o).get(i);
            if (item1 != null && !item1.equals(item2)) {
                return false;
            }
        }
        return true;
    }

    public T getRecursive(int index) {
        Node<T> pointer = sentinel;
        return getRecursive(pointer, index);
    }
    public T getRecursive(Node<T> pointer, int index) {
        if (index == 0) {
            return pointer.item;
        }
        return getRecursive(pointer.next, index - 1);
    }
}
