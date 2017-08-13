package Graphs;

/**
 * This is an implementation of stack data structure using linked list.
 * The running time of each operations are:
 * Push: O(1)
 * Peek: O(1)
 * Pop: O(1)
 * isEmpty: O(1)
 * size: O(1)
 *
 * @param <E> the element stored in the stack
 */
public class MyStack<E> {
    private MyLinkedList<E> list = new MyLinkedList<>();

    /**
     * Default instantiator
     */
    public MyStack(){}

    /**
     *
     * @return the size of the stack
     */
    public int size() { return list.size(); }

    /**
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() { return list.isEmpty(); }

    /**
     *
     * @param element push the element into the top of the stack
     */
    public void push(E element) { list.add(element); }

    /**
     *
     * @return get top element of the stack
     * Note: this does not remove the element
     */
    public E peek() { return list.first(); }

    /**
     *
     * @return the top element of the stack, also remove the element
     */
    public E pop() { return list.remove(); }
}
