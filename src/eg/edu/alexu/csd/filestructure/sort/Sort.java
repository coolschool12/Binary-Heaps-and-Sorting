package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.List;

public class Sort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public IHeap<T> heapSort(ArrayList<T> unordered) {

        IHeap<T> binaryHeap = new BinaryHeap<>();
        binaryHeap.build(unordered);
        IHeap<T> cloned = ((BinaryHeap<T>) binaryHeap).clone();

        if (unordered == null || unordered.size() == 0)
            return cloned;

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

    /**
     * implementing merge sort
     * @param unordered unordered elements
     */
    @Override
    public void sortFast(ArrayList<T> unordered) {
        if (unordered == null)
        {
            return;
        }

        List<T> sorted = this.mergeSort(unordered);

        unordered.clear();
        unordered.addAll(sorted);
    }

    /**
     * implement merge sort
     * @param unordered unordered elements
     * @return sorted list
     */
    private List<T> mergeSort(List<T> unordered) {
        if (unordered.size() > 1)
        {
            List<T> firstSub = this.mergeSort(unordered.subList(0, unordered.size() / 2));
            List<T> secondSub = this.mergeSort(unordered.subList(unordered.size() / 2, unordered.size()));

            return merge(firstSub, secondSub);
        }

        return unordered;
    }

    /**
     * merges two lists
     * @param first first list
     * @param second second list
     * @return merged list
     */
    private List<T> merge(List<T> first, List<T> second) {
        List<T> merged = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;
        while (index1 < first.size() || index2 < second.size())
        {
            if (index1 >= first.size())
            {
                merged.add(second.get(index2++));
            }
            else if (index2 >= second.size())
            {
                merged.add(first.get(index1++));
            }
            else
            {
                // Second list contains smaller element
                if (first.get(index1).compareTo(second.get(index2)) > 0)
                {
                    merged.add(second.get(index2++));
                }
                // First list contains smaller element
                else
                {
                    merged.add(first.get(index1++));
                }
            }
        }

        return merged;
    }
}
