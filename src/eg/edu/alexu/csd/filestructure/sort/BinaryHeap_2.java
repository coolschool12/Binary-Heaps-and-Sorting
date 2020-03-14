package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryHeap_2<T extends Comparable<T>> implements IHeap<T>{

    private List<INode<T>> nodes;
    private long size;

    BinaryHeap_2(){
        nodes = new ArrayList<>();
        size = 0;
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
        if (node != null
                && (node.getLeftChild() != null || node.getRightChild() != null)
                && ((NodeImp<T>)node).getIndex() < (size() / 2))
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
        if (nodes.size() <= 0) {
            return null;
        }
        // One node
        else if (nodes.size() == 1) {
            return nodes.remove(0).getValue();
        }

        T extracted = nodes.get(0).getValue();

        // Move last node to first position
      //  INode<T> node = nodes.remove(nodes.size() - 1);
        this.nodes.get(0).setValue(nodes.get(size() - 1).getValue());
        this.nodes.get(size() - 1).setValue(extracted);
        setSize(size() - 1);

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
        setSize(size() + 1);
    }

    @Override
    public void build(Collection<T> unordered) {
        if (unordered == null)
        {
            return;
        }
        nodes.clear();
        for (T t : unordered)
        {
            INode<T> node = new NodeImp<>(nodes, nodes.size(), t);
            nodes.add(node);
        }
        setSize(nodes.size());
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
            ArrayList<INode<T>> deepClonedNodes = new ArrayList<>();

            for (INode<T> node : this.nodes) {
                deepClonedNodes.add(new NodeImp<T>(deepClonedNodes, ((NodeImp<T>)node)));
            }
            this.nodes = deepClonedNodes;

            return cloned;
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
}
