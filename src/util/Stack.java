package util;

/**
 * Created by zhaokai on 16-6-7.
 */
public class Stack<T> {

    private class Node
    {
        T val;
        Node next;

        Node(T val)
        {
            this.val=val;
            next=null;
        }
    }

    private Node top;
    private int size;

    public Stack()
    {
        top=null;
        size=0;
    }

    public void push(T val)
    {
        Node node=new Node(val);

        if(top==null)
            top=node;
        else
        {
            node.next=top;
            top=node;
        }

        size++;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public T peek()
    {
        if(isEmpty())
            throw new NullPointerException();

        return top.val;
    }

    public T pop()
    {
        if(isEmpty())
            throw new NullPointerException();

        Node node=top;
        top=top.next;
        node.next=null;
        size--;

        return node.val;

    }

}
