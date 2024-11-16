package com.mycompany.datastructures;

import java.util.*;

public class PriorityQueue<T extends Comparable<T>> {

    private int size; // Number of elements in the heap
    private List<T> heap; // The underlying list used to store elements
    private final Comparator<? super T> comparator; // Comparator to define heap order

    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    // Constructor for a natural ordering min-heap
    public PriorityQueue() {
        this(null);
    }

    // Constructor with a custom Comparator
    public PriorityQueue(Comparator<? super T> comparator) {
        this.comparator = (comparator != null) ? comparator : Comparator.naturalOrder();
        heap = new ArrayList<>();
        size = 0;
    }

    // Constructor with a custom Comparator and initial capacity
    public PriorityQueue(Comparator<? super T> comparator, int numOfElems) {
        this.comparator = (comparator != null) ? comparator : Comparator.naturalOrder();
        heap = new ArrayList<>(numOfElems);
        size = numOfElems;
    }

    // Constructor to build a heap from a collection
    public PriorityQueue(Comparator<? super T> comparator, Collection<T> elems) {
        this.comparator = (comparator != null) ? comparator : Comparator.naturalOrder();
        size = elems.size();
        heap = new ArrayList<>(size);

        for (T elem : elems) {
            add(elem);
        }

    }

    public void add(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException("Cannot add null element.");
        }

        if (size < heap.size()) {
            heap.set(size, elem);
        } else {
            heap.add(elem);
        }

        mapAdd(elem, size);

        size++;
        swim(size - 1);
    }

    private boolean less(int i, int k) {
        T node1 = heap.get(i);
        T node2 = heap.get(k);
        return comparator.compare(node1, node2) < 0;
    }

    private void swim(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && less(index, parent)) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void sink(int index) {
        int child = 2 * index + 1;
        while (child < size) {
            if (child + 1 < size && less(child + 1, child)) {
                child = child + 1;
            }
            if (!less(child, index)) {
                break;
            }
            swap(index, child);
            index = child;
            child = 2 * index + 1;
        }
    }

    private void swap(int i, int j) {
        T i_elem = heap.get(i);
        T j_elem = heap.get(j);

        heap.set(i, j_elem);
        heap.set(j, i_elem);

        mapSwap(i_elem, j_elem, i, j);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        heap.clear();
        size = 0;
        map.clear();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public boolean contains(T elem) {

        if (elem == null) {
            return false;
        }

        return map.containsKey(elem);

    }

    public T poll() {
        return removeAt(0);
    }

    
    public T removeAt(int index) {
        if (isEmpty()) {
            return null;
        }

        T removed = heap.get(index);
        int lastIndex = size - 1;

        swap(index, lastIndex);
        heap.set(lastIndex, null);
        size--;
        mapRemove(removed, size);
        
        
        
        if (index < size) {
            T current = heap.get(index);
            sink(index);
            if (heap.get(index).equals(current)) {
                swim(index);
            }
        }

        return removed;
    }
    
   
    

    public boolean remove(T element) {

        if (element == null) {
            return false;
        }

        Integer index = mapGet(element);
        if (index != null) {
            removeAt(index);
        }

        return index != null;
    }

    public int size() {
        return size;
    }

    private void mapAdd(T value, int index) {

        TreeSet<Integer> set = map.get(value);

        // New value being inserted in map
        if (set == null) {

            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);

            // Value already exists in map
        } else {
            set.add(index);
        }
    }
    
    private void mapRemove(T value, int index) {
       
        TreeSet<Integer> set = map.get(value);
        set.remove(index); 
        if (set.isEmpty()) map.remove(value);
    }
    
    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if (set != null) return set.last();
        return null;
    }
    
    private void mapSwap(T val1, T val2, int val1Index, int val2Index) {

        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        set1.remove(val1Index);
        set2.remove(val2Index);

        set1.add(val2Index);
        set2.add(val1Index);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
