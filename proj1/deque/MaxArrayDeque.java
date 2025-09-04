package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    public T max() {
        if (super.size() == 0) {
            return null;
        }
        int maxIndex = 0;
        int size = super.size();
        for (int i = 1; i < size; i++) {
            if (this.comparator.compare(super.get(i), super.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return super.get(maxIndex);
    }

    public T max(Comparator<T> c) {
        if (super.size() == 0) {
            return null;
        }
        int maxIndex = 0;
        int size = super.size();
        for (int i = 1; i < size; i++) {
            if (c.compare(super.get(i), super.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return super.get(maxIndex);
    }
}
