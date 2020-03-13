package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class BinaryHeap<T extends Comparable<T>> implements IHeap<T>{

    private List<INode<T>> nodes;

    BinaryHeap(){

        nodes = new ArrayList<>();
    }

    @Override
    public INode<T> getRoot() {
        if (nodes.size() <= 0)
        {
            return null;
        }
        return nodes.get(0);
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public void heapify(INode<T> node) {

        if (node != null && (node.getLeftChild() != null || node.getRightChild() != null))
        {
            INode<T> child = shiftTest(node.getLeftChild(), node.getRightChild()) ? node.getLeftChild() : node.getRightChild();

            if (child.getValue().compareTo(node.getValue()) > 0)
            {
                swap(child, node);
                heapify(child);
            }
        }
    }

    @Override
    public T extract() {
        // Empty list
        if (nodes.size() <= 0)
        {
            return null;
        }
        // One node
        else if (nodes.size() == 1)
        {
            return nodes.remove(0).getValue();
        }

        T extracted = nodes.get(0).getValue();

        // Move last node to first position
        INode<T> node = nodes.remove(nodes.size() - 1);
        this.nodes.get(0).setValue(node.getValue());

        this.heapify(this.nodes.get(0));

        return extracted;
    }

    @Override
    public void insert(T element) {

        if (element == null) {
            return;
        }

        INode<T> node = new NodeImp<>(nodes, nodes.size(), element);
        nodes.add(node);
        shiftUp(node);
    }

    @Override
    public void build(Collection<T> unordered) {
        if (unordered == null)
        {
            return;
        }

        for (T t : unordered)
        {
            INode<T> node = new NodeImp<>(nodes, nodes.size(), t);
            nodes.add(node);
        }

        for (int i = (nodes.size() / 2) - 1; i >= 0; i--)
        {
            this.heapify(this.nodes.get(i));
        }
    }

    @Override
    public IHeap<T> clone()
    {
        try {
            IHeap<T> cloned = (IHeap<T>) super.clone();
            this.nodes = new ArrayList<>(nodes);
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertLast(T element) {
        INode<T> node = new NodeImp<>(nodes, nodes.size(), element);
        this.nodes.add(node);
    }

    public void heapSort() {
        int size = this.nodes.size();

        for (int i = 0, staticSize = size; i < staticSize; i++)
        {
//            INode<T> node = new NodeImp<>(this.nodes, size - 1 - i, this.extract());
//            this.nodes.add(node);

            this.swap(nodes.get(size - 1), nodes.get(0));
            this.heapify(this.nodes.get(0), 0, --size);
        }
    }

    private void heapify(INode<T> node, int index, int size) {
        if (index < size / 2)
        {
            INode<T> child;
            boolean test = shiftTest(node.getLeftChild(), node.getRightChild(), (2 * index) + 1, (2 * index) + 2, size);
            if (test && (2 * index) + 1 < size)
            {
                child = node.getLeftChild();
                index = (2 * index) + 1;
            }
            else if ((2 * index) + 2 < size)
            {
                child = node.getRightChild();
                index = (2 * index) + 2;
            }
            else
                return;

            if (child.getValue().compareTo(node.getValue()) > 0)
            {
                swap(child, node);
                this.heapify(child, index, size);
            }
        }
    }

    private void shiftUp(INode<T> node)
    {
        if (node.getParent() == null)
            return;

        if (shiftTest(node, node.getParent()))
        {
            swap(node, node.getParent());
            shiftUp(node.getParent());
        }
    }

    private boolean shiftTest(INode<T> fNode, INode<T> nNode, int ind1, int ind2, int size)
    {
        if (fNode == null || ind1 >= size)
            return false;
        else if (nNode == null || ind2 >= size)
            return true;
        return fNode.getValue().compareTo(nNode.getValue()) > 0;
    }

    private boolean shiftTest(INode<T> fNode, INode<T> nNode)
    {
        if (fNode == null)
            return false;
        else if (nNode == null)
            return true;
        return fNode.getValue().compareTo(nNode.getValue()) > 0;
    }

    private void swap(INode<T> fNode, INode<T> nNode)
    {
        T comparable = fNode.getValue();
        fNode.setValue(nNode.getValue());
        nNode.setValue(comparable);
    }
}
