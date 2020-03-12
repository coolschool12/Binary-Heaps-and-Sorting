package eg.edu.alexu.csd.filestructure.sort;

import java.util.List;

public class NodeImp<T extends Comparable<T>> implements INode<T> {
    private List<INode<T>> heapList;
    private int index;

    private T value;

    /**
     * Node constructor.
     * @param heapList a reference to the array containing the node.
     * @param index index of the node.
     */
    public NodeImp(List<INode<T>> heapList, int index, T value) throws IllegalArgumentException {
        if (heapList == null)
        {
            throw new IllegalArgumentException("null array");
        }
        else if (index < 0)
        {
            throw new IllegalArgumentException("invalid index");
        }

        this.heapList = heapList;
        this.index = index;
        this.value = value;
    }

    /**
     * Returns the left child of the current element/node in the heap tree
     * @return        INode wrapper to the left child of the current element/node
     */
    @Override
    public INode<T> getLeftChild() {
        int leftIndex = (2 * index) + 1;

        if (leftIndex >= heapList.size())
        {
            return null;
        }

        return this.heapList.get(leftIndex);
    }

    /**
     * Returns the right child of the current element/node in the heap tree
     * @return        INode wrapper to the right child of the current element/node
     */
    @Override
    public INode<T> getRightChild() {
        int rightIndex = (2 * index) + 2;

        if (rightIndex >= heapList.size())
        {
            return null;
        }

        return this.heapList.get(rightIndex);
    }

    /**
     * Returns the parent node of the current element/node in the heap tree
     * @return        INode wrapper to the parent of the current element/node
     */
    @Override
    public INode<T> getParent() {
        int parentIndex = (this.index - 1) / 2;

        if (parentIndex < 0)
        {
            return null;
        }

        return this.heapList.get(parentIndex);
    }

    /**
     * Get the value of the current node
     * @return        Value of the current node
     */
    @Override
    public T getValue() {
        return this.value;
    }

    /**
     * Set the value of the current node
     */
    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
