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

        if (node == null)
            throw new NullPointerException("There's no node to heapify the heap!!");

        if (node.getLeftChild() != null || node.getRightChild() != null)
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

        INode<T> extracted = nodes.remove(0);

        // Move last node to first position
        if (nodes.size() != 0)
        {
            INode<T> node = nodes.remove(nodes.size() - 1);
            INode<T> newNode = new NodeImp<>(nodes, 0, node.getValue());

            this.nodes.add(0, newNode);
            this.heapify(newNode);
        }

        return extracted.getValue();
    }

    @Override
    public void insert(T element) {
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
            return (IHeap<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
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
