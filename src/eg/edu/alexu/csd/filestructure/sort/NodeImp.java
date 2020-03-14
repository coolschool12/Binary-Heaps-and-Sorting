package eg.edu.alexu.csd.filestructure.sort;

import org.junit.Ignore;

import java.util.List;

public class NodeImp<T extends Comparable<T>> implements INode<T> {
    private List<T> heapList;
    private int index;
    private IHeap<T> heap;
    private INode<T> left,right,parent;
    /**
     * Node constructor.
     * @param heapList a reference to the array containing the node.
     * @param index index of the node.
     */
    public NodeImp(List<T> heapList, IHeap<T> binaryHeap,int index) throws IllegalArgumentException {
        if (heapList == null)
        {
            throw new IllegalArgumentException("null array");
        }
        else if (index < 0)
        {
            throw new IllegalArgumentException("invalid index");
        }
        heap = binaryHeap;
        this.heapList = heapList;
        this.index = index;
    }

    /**
     * Returns the left child of the current element/node in the heap tree
     * @return        INode wrapper to the left child of the current element/node
     */
    @Override
    public INode<T> getLeftChild() {
        int leftIndex = (2 * index) + 1;

        if (leftIndex >= heap.size())
        {
            return null;
        }
        if (left != null) {
            left.setValue(heapList.get(leftIndex));
            return left;
        }

        return left = new NodeImp<>(this.heapList, heap,leftIndex);
    }

    /**
     * Returns the right child of the current element/node in the heap tree
     * @return        INode wrapper to the right child of the current element/node
     */
    @Override
    public INode<T> getRightChild() {
        int rightIndex = (2 * index) + 2;

        if (rightIndex >= heap.size())
        {
            return null;
        }
        if (right != null) {
            right.setValue(this.heapList.get(rightIndex));
            return right;
        }
        return right = new NodeImp<>(this.heapList, heap, rightIndex);
    }

    /**
     * Returns the parent node of the current element/node in the heap tree
     * @return        INode wrapper to the parent of the current element/node
     */
    @Override
    public INode<T> getParent() {
        if (this.index == 0)
        {
            return null;
        }
        if (parent != null){
            parent.setValue(this.heapList.get((this.index- 1) / 2));
            return parent;
        }

        return parent = new NodeImp<>(this.heapList, heap, (this.index - 1) / 2);
    }

    /**
     * Get the value of the current node
     * @return        Value of the current node
     */
    @Override
    public T getValue() {
        return this.heapList.get(index);
    }

    /**
     * Set the value of the current node
     */
    @Override
    public void setValue(T value) {
        this.heapList.set(this.index, value);
    }

    public int getIndex() {
        return index;
    }
}
