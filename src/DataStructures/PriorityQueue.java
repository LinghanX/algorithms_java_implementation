package DataStructures;

import java.util.ArrayList;

/**
 * An implementation of priority queue using ArrayList
 * Created by Linghan on 7/12/17.
 */
public class PriorityQueue<E extends Comparable<E>> {
    private ArrayList<E> heap;

    public PriorityQueue() {
        this.heap = new ArrayList<E>();
    }

    public boolean offer(E e) {
        if(heap.isEmpty()) { return heap.add(e); }

        heap.add(e);
        heapifyUp(heap.size() - 1);
        return true;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }
    public int size() {
        return heap.size();
    };
    public E peek() {
        if(heap.isEmpty()) throw new IllegalArgumentException();
        return heap.get(0);
    };

    public E poll() {
        if(heap.isEmpty()) throw new IllegalArgumentException();

        E ret = heap.get(0);
        E item = heap.remove(heap.size() - 1);
        heap.set(0, item);
        heapifyDown(0);

        return ret;
    };

    private void heapifyDown(int index) {
        while(hasLeft(index)) {
            int leftIndex = left(index);
            int smallerChild = leftIndex;

            if(hasRight(index)) {
                int rightIndex = right(index);
                if(heap.get(leftIndex).compareTo(heap.get(rightIndex)) > 0) {
                    smallerChild = rightIndex;
                }
            }

            if(heap.get(smallerChild).compareTo(heap.get(index)) > 0) break;
            swap(index, smallerChild);
            index = smallerChild;
        }
    }

    private void heapifyUp(int index) {
        while(index > 0) {
            int p = parent(index);
            if(heap.get(p).compareTo(heap.get(index)) >= 0) break;
            swap(p, index);
            index = p;
        }
    }

    private int parent(int i) { return (i-1) / 2; }
    private int left(int i) { return 2*i + 1; }
    private int right(int i) { return 2*i + 2; }
    private boolean hasLeft(int i) { return left(i) < heap.size(); }
    private boolean hasRight(int i) { return right(i) < heap.size(); }
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
