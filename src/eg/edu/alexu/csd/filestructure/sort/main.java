package eg.edu.alexu.csd.filestructure.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {
    public static void main(String[] args)
    {
        BinaryHeap_2<Integer> binaryHeap = new BinaryHeap_2<>();
        ArrayList<Integer> arr = new ArrayList<>();
        binaryHeap.insert(21);
        binaryHeap.insert(255);
        binaryHeap.insert(21);
        binaryHeap.insert(5);
        binaryHeap.insert(285);
        binaryHeap.insert(255);
        binaryHeap.insert(400);
        binaryHeap.extract();
        binaryHeap.extract();
        binaryHeap.extract();
        binaryHeap.insert(4885566);
        binaryHeap.extract();
        binaryHeap.insert(5646);
        binaryHeap.insert(1);
        binaryHeap.extract();
        binaryHeap.extract();
    }
}
