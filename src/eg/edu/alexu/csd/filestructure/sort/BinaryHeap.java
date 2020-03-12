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
        return null;
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
            INode child = shiftTest(node.getLeftChild(), node.getRightChild()) ? node.getLeftChild() : node.getRightChild();

            if (child.getValue().compareTo(node.getValue()) > 0)
            {
                shift(child, node);
                heapify(child);
            }
        }
    }

    @Override
    public T extract() {
        return null;
    }

    @Override
    public void insert(T element) {
        //INode<T> node = new NodeImp<T>(nodes, nodes.size(), element);

    }

    @Override
    public void build(Collection unordered) {

    }

    private boolean shiftTest(INode<T> fNode, INode<T> nNode)
    {
        if (fNode == null)
            return false;
        else if (nNode == null)
            return true;
        return fNode.getValue().compareTo(nNode.getValue()) > 0;
    }

    private void shift(INode<T> fNode, INode<T> nNode)
    {
        T comparable = fNode.getValue();
        fNode.setValue(nNode.getValue());
        nNode.setValue(comparable);
    }

}
