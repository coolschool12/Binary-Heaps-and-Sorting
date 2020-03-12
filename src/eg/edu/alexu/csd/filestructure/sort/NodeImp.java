package eg.edu.alexu.csd.filestructure.sort;

public class NodeImp<T extends Comparable<T>> implements INode<T> {
    private INode<T>[] heapArr;
    private int index;

    private T value;

    /**
     * Node constructor.
     * @param heapArr a reference to the array containing the node.
     * @param index index of the node.
     */
    public NodeImp(INode<T>[] heapArr, int index, T value) throws IllegalArgumentException {
        if (heapArr == null)
        {
            throw new IllegalArgumentException("null array");
        }
        else if (index < 0 || index >= heapArr.length)
        {
            throw new IllegalArgumentException("invalid index");
        }

        this.heapArr = heapArr;
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

        if (leftIndex >= heapArr.length)
        {
            return null;
        }

        return this.heapArr[leftIndex];
    }

    /**
     * Returns the right child of the current element/node in the heap tree
     * @return        INode wrapper to the right child of the current element/node
     */
    @Override
    public INode<T> getRightChild() {
        int rightIndex = (2 * index) + 2;

        if (rightIndex >= heapArr.length)
        {
            return null;
        }

        return this.heapArr[rightIndex];
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

        return this.heapArr[parentIndex];
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
