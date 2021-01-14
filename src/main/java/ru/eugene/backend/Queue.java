package ru.eugene.backend;

public class Queue<T> {
    private Node<T> first = null;
    private Node<T> last = null;
    private int size = 0;

    public Node<T> getFirst() {
        return this.first;
    }

    public Node<T> getLast() {
        return this.last;
    }

    public int size() {
        return this.size;
    }

    public Node<T> add(T value) {
        Node<T> created = new Node<>(value);
        if (first == null) {
            first = created;
        } else {
            last.next = created;
            created.prev = last;
        }

        last = created;

        size++;
        return created;
    }

    public T remove() {
        return remove(first);
    }

    public T remove(T element) {
        Node<T> current = first;

        while (current != null) {
            if (current.value.equals(element))
                return remove(current);

            current = current.getNext();
        }

        return null;
    }

    public T remove(Node<T> node) {
        if (node == null)
            return null;

        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev != null)
            prev.next = next;

        if (next != null)
            next.prev = prev;

        if (node == first)
            first = next;

        if (node == last)
            last = prev;
        size--;
        return node.getValue();
    }

    public static class Node<T> {
        private final T value;
        private Node<T> prev = null;
        private Node<T> next = null;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public Node<T> getPrevious() {
            return this.prev;
        }

        public Node<T> getNext() {
            return this.next;
        }
    }
}
