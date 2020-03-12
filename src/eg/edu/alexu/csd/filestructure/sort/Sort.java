package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Sort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public IHeap<T> heapSort(ArrayList<T> unordered) {

        IHeap<T> binaryHeap = new BinaryHeap<>();
        binaryHeap.build(unordered);

        IHeap<T> cloned = ((BinaryHeap<T>) binaryHeap).clone();

        unordered.clear();

        while (binaryHeap.size() > 0)
        {
            unordered.add(0, binaryHeap.extract());
        }
        return cloned;
    }

    @Override
    public void sortSlow(ArrayList<T> unordered) {

    }

    @Override
    public void sortFast(ArrayList<T> unordered) {

    }
}
