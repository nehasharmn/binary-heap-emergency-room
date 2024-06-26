package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Part 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>(initialEntries.length);
        for(Prioritized<V, P> entry : initialEntries) {
            _heap.add(entry);
        }
        for (int i = parentIndex(_heap.size() - 1); i >= 0; i--) {
            bubbleDown(i);
        }
    }
    private int parentIndex(int index) {
        return (index - 1) / 2; }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
            _heap.add(new Patient<>(value,priority));
            bubbleUp(_heap.size() - 1);
        }

    private void bubbleUp(int index) {
        int parentIndex;
        while (index > 0 && _heap.get(index).compareTo(_heap.get((parentIndex = (index - 1) / 2))) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void swap(int i, int j) {
        Prioritized<V, P> temp = _heap.get(i);
        _heap.set(i, _heap.get(j));
        _heap.set(j, temp);
    }


    // TODO: enqueue
    public void enqueue(V value) {
        _heap.add(new Patient<>(value));
        bubbleUp(_heap.size() - 1);
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (_heap.isEmpty()) {
            return null;
        }
        if (_heap.size() == 1) {
            return _heap.remove(0).getValue();
        }
        Prioritized<V, P> root = _heap.get(0);
        _heap.set(0, _heap.remove(_heap.size() - 1));
        bubbleDown(0);
        return root.getValue();
    }
    private void bubbleDown(int index) {
        int maxIndex = index;
        int lChildIndex = 2 * index + 1;
        int rChildIndex = 2 * index + 2;

        if (lChildIndex < _heap.size() && _heap.get(lChildIndex).compareTo(_heap.get(maxIndex)) > 0) {
            maxIndex = lChildIndex;
        }
        if (rChildIndex < _heap.size() && _heap.get(rChildIndex).compareTo(_heap.get(maxIndex)) > 0) {
            maxIndex = rChildIndex;
        }

        if (index != maxIndex) {
            swap(index, maxIndex);
            bubbleDown(maxIndex);
        }
    }


    // TODO: getMax
    @Override
    public V getMax() {
        if (_heap.isEmpty()) {
            return null;
        }
        return _heap.get(0).getValue();
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }






}