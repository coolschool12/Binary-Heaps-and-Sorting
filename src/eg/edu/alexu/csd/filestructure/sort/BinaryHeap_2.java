package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryHeap_2<T extends Comparable<T>> implements IHeap<T>{

    private List<T> nodes;
    private long size;

    BinaryHeap_2(){
        nodes = new ArrayList<>();
        size = 0;
    }

    @Override
    public INode<T> getRoot() {
        if (size() <= 0)
        {
            return null;
        }
        return new NodeImp<T>(this.nodes, this, 0);
    }

    @Override
    public int size() {
        return (int) size;
    }

    public void setSize(int size)
    {
        if (nodes.size() < size)
            return;
        this.size = size;
    }

    @Override
    public void heapify(INode<T> node) {

        while ((node.getLeftChild() != null || node.getRightChild() != null)
                && ((NodeImp<T>)node).getIndex() < (size() / 2))
        {
            INode<T> child = shiftTest(node.getLeftChild(), node.getRightChild()) ? node.getLeftChild() : node.getRightChild();

            if (child.getValue().compareTo(node.getValue()) > 0)
            {
                swap(child, node);
                node = child;
            }
        }
    }
    @Override
    public T extract() {
        // Empty list
        if (size() <= 0) {
            return null;
        }
        // One node
        else if (size() == 1) {
            setSize(size() - 1);
            return nodes.get(0);
        }

        T extracted = nodes.get(0);

        // Move last node to first position
      //  INode<T> node = nodes.remove(nodes.size() - 1);
        this.nodes.set(0, this.nodes.get(size() - 1));
        this.nodes.set(size() - 1, extracted);
        setSize(size() - 1);

        this.heapify(new NodeImp<>(this.nodes, this, 0));

        return extracted;
    }

    @Override
    public void insert(T element) {

        if (element == null) {
            return;
        }

        //INode<T> node = new NodeImp<>(nodes, this, size(), element);

        nodes.add(size(), element);
        /*for (int i = size(); i < nodes.size(); i++)
        {
            ((NodeImp<T>)this.nodes.get(i)).setIndex(i);
        }*/
        setSize(size() + 1);
        shiftUp(size() - 1);

    }

    @Override
    public void build(Collection<T> unordered) {
        if (unordered == null)
        {
            return;
        }
        nodes.clear();
        // INode<T> node = new NodeImp<>(nodes, this,nodes.size(), t);
        nodes.addAll(unordered);

        setSize(nodes.size());
        for (int i = (nodes.size() / 2) - 1; i >= 0; i--)
        {
            this.heapify(new NodeImp<>(this.nodes, this, i));
        }
    }
    @Override
    public IHeap<T> clone()
    {
        try {
            IHeap<T> cloned = (IHeap<T>) super.clone();
            ArrayList<T> deepClonedNodes = new ArrayList<>(this.nodes);
            this.nodes = deepClonedNodes;
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void shiftUp(int node)
    {
        while (node > 0) {
            int parentNode = (node - 1) / 2;
            if (shiftTest(node, parentNode)) {
                swap(node, parentNode);
                node = parentNode;
            }
        }
    }

    private boolean shiftTest(INode<T> fNode, INode<T> nNode)
    {
        if (fNode == null || ((NodeImp<T>)fNode).getIndex() >= size())
            return false;
        else if (nNode == null || ((NodeImp<T>) nNode).getIndex() >= size())
            return true;
        return fNode.getValue().compareTo(nNode.getValue()) > 0;
    }

    private void swap(INode<T> fNode, INode<T> nNode)
    {
        T comparable = fNode.getValue();
        fNode.setValue(nNode.getValue());
        nNode.setValue(comparable);
    }
    private boolean shiftTest(int fNode, int  nNode)
    {
        if (fNode >= size())
            return false;
        else if (nNode >= size())
            return true;
        return nodes.get(fNode).compareTo(nodes.get(nNode)) > 0;
    }

    private void swap(int fNode, int nNode)
    {
        T comparable = nodes.get(fNode);
        nodes.set(fNode, nodes.get(nNode));
        nodes.set(nNode, comparable);
    }
}
