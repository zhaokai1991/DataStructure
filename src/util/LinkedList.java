package util;

import api.List;

/**
 * Created by zhaokai on 16-6-21.
 */
public class LinkedList<T> implements List<T>{

    static class ListNode<T>
    {
        T value;
        ListNode<T> previous;
        ListNode<T> next;

        public ListNode(T value)
        {
            this(value,null,null);
        }

        public ListNode(T value,ListNode<T> previous,ListNode<T> next)
        {
            this.value=value;
            this.previous=previous;
            this.next=next;
        }
    }

    private ListNode<T> head;
    private ListNode<T> tail;

    private int size;

    public LinkedList()
    {
        head=null;
        tail=null;
        size=0;
    }

    @Override
    public void add(T value)
    {
        addLast(value);
    }

    public void addLast(T value)
    {
        ListNode<T> node=new ListNode<T>(value,tail,null);

        if(head==null)
            head=node;

        tail=node;

        size++;
    }

    public void addFirst(T value)
    {
        ListNode<T> node=new ListNode<T>(value,null,head);

        if(tail==null)
            tail=node;

        head=node;

        size++;
    }

    @Override
    public void insert(int index,T value)
    {
        if(index>size||index<0)
            throw new IllegalArgumentException("index "+index+" is inavailable");

        if(index==0)
        {
            addFirst(value);
            return;
        }

        if(index==size)
        {
            addLast(value);
            return;
        }

        ListNode<T> next=getNode(index);
        ListNode<T> previous=next.previous;

        ListNode<T> newNode=new ListNode<T>(value,previous,next);
        previous.next=newNode;
        next.previous=newNode;

        size++;
    }

    @Override
    public T remove(int index)
    {
        ListNode<T> p=getNode(index);

        p.previous.next=p.next;
        p.next.previous=p.previous;

        p.next=null;
        p.previous=null;

        return p.value;
    }

    public T removeLast()
    {
        if(tail==null)
            return null;

        ListNode<T> node=tail;
        tail=tail.previous;
        tail.next=null;
        node.previous=null;

        size--;

        return node.value;
    }

    public T removeFirst()
    {
        if(head==null)
            return null;

        ListNode<T> node=head;
        head=head.next;
        head.previous=null;
        node.next=null;

        size--;

        return node.value;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size==0;
    }

    private ListNode<T> getNode(int index)
    {
        if(index>=size||index<0)
            throw new IllegalArgumentException("index "+index+" is inavailable");

        ListNode<T> p=head;
        int i=0;

        while(i<index) {
            p = p.next;
            i++;
        }

        return p;
    }

    @Override
    public T get(int index)
    {
        return getNode(index).value;
    }

    @Override
    public String toString()
    {
        if(size==0)
            return "[]";

        StringBuilder result=new StringBuilder();
        result.append("[");

        ListNode<T> p=head;

        while(p!=tail)
        {
            result.append(p.value.toString()+",");
            p=p.next;
        }

        result.append(p.value.toString()+"]");

        return result.toString();
    }

}
