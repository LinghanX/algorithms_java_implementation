package Graphs;

/**
 * This is an implementation of the linked list data structure.
 * It supports operations such as:
 *     size: O(1)
 *     isEmpty: O(1)
 *     first: O(1)
 *     add: O(1)
 *     remove: O(1)
 *
 * @param <E> The element that gets stored in the linked list
 */
public class MyLinkedList<E> {

    /**
     * A private class Node as elements in the linked list
     * @param <E> the element
     */
    private static class Node<E> {
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
        public E getElement() { return element; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> n) { next = n; }
    }

    private Node<E> head = null; // keep a track of the head;
    private int size = 0;

    public MyLinkedList(){}

    /**
     *
     * @return number of elements in the linked list
     */
    public int size() { return size; }

    /**
     *
     * @return true if there is no element in the linked list, false otherwise
     */
    public boolean isEmpty() { return size == 0; }

    /**
     *
     * @return the head element of the linked list
     */
    public E first() {
        if(isEmpty()) return null;
        return head.getElement();
    }

    /**
     *
     * @param e add element to the head of the linked list
     */
    public void add(E e) {
        head = new Node<>(e, head);
        size++;
    }

    /**
     *
     * @return remove the head of the linked list, return null if the
     *         linked list is empty;
     */
    public E remove() {
        if(isEmpty()) return null;
        E result = head.getElement();
        head = head.getNext();
        size--;
        return result;
    }
}

