package upo.additionalstructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.AbstractMap.SimpleEntry;

public class RealPriorityQueue<E, P> {

    PriorityQueue<P> queue;
    ArrayList<SimpleEntry<P, E>> list;

    public RealPriorityQueue() {
        queue = new PriorityQueue<>();
        list = new ArrayList<>();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty() && list.isEmpty();
    }

    public boolean containsPriority(P priority) {
        return queue.contains(priority);
    }

    public boolean containsElement(E element) {
        for (SimpleEntry<P, E> item : list)
            if (item.getValue().equals(element))
                return true;
        return false;
    }

    public Iterator<P> queueIterator() {
        return queue.iterator();
    }

    public Iterator<SimpleEntry<P, E>> elementsIterator() {
        return list.iterator();
    }

    public boolean enqueue(P priority, E element) {
        boolean result = true;
        result &= queue.add(priority);
        result &= list.add(new SimpleEntry<>(priority, element));
        return result;
    }

    public E peek() {
        for (SimpleEntry<P, E> item : list)
            if (item.getKey().equals(queue.peek()))
                return item.getValue();
        return null;
    }

    public int indexOf(E element) {
        for (SimpleEntry<P, E> item : list)
            if (item.getValue().equals(element))
                return list.indexOf(item);
        return -1;
    }

    public int indexOfPriority(P priority) {
        for (SimpleEntry<P, E> item : list)
            if (item.getKey().equals(priority))
                return list.indexOf(item);
        return -1;
    }

    public void decreaseKey(P newPriority, E element) {
        int index = indexOf(element);
        var temp = list.get(index);
        SimpleEntry<P, E> newEntry = new SimpleEntry<>(newPriority, temp.getValue());
        list.remove(index);
        queue.remove(temp.getKey());
        queue.add(newPriority);
        list.add(newEntry);
    }

    public SimpleEntry<P, E> dequeue(P priority, E element) {
        queue.remove(priority);
        return list.remove(indexOf(element));
    }

    public SimpleEntry<P, E> dequeue() {
        return list.remove(indexOfPriority(queue.remove()));
    }

    public void clear() {
        queue.clear();
        list.clear();
    }
}