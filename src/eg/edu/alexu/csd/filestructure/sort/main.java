package eg.edu.alexu.csd.filestructure.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {
    public static void main(String[] args)
    {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        binaryHeap.build(arr);
        binaryHeap.heapSort();
    }
}
