package util;

/**
 * Created by zhaokai on 16-6-14.
 */
public class Vector<T> {

    private static final int DEFAULT_CAPACITY=16;

    private int capacity;
    private int size;

    private Object[] elements;

    public Vector()
    {
        this(DEFAULT_CAPACITY);
    }

    public Vector(int capacity)
    {
        if(capacity<=0)
            throw new IllegalArgumentException("capacity:"+capacity);

        this.capacity=capacity;
        this.size=0;
        this.elements=new Object[DEFAULT_CAPACITY];
    }

    private boolean isFull()
    {
        return size>=capacity;
    }

    private void reallocate()
    {
        capacity*=2;
        Object[] newELements=new Object[capacity];

        for(int i=0;i<size;i++)
            newELements[i]=elements[i];

        elements=newELements;
    }

    public void add(T element)
    {
        if(isFull())
            reallocate();

        elements[size++]=element;
    }

    public void add(int index,T element)
    {
        if(index>=size||index<0)
            throw new ArrayIndexOutOfBoundsException(index);

        if(isFull())
            reallocate();

        for(int i=size;i>=index+1;i--)
            elements[i]=elements[i-1];

        elements[index]=element;
    }

    public T get(int index)
    {
        if(index>=size||index<0)
            throw new ArrayIndexOutOfBoundsException(index);

        return (T)elements[index];
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public T remove(int index)
    {
        if(index>=size||index<0)
            throw new ArrayIndexOutOfBoundsException(index);

        Object removed=elements[index];

        for(int i=index;i<size-1;i++)
        {
            elements[i]=elements[i+1];
        }

        elements[size-1]=null;
        size--;

        return (T)removed;
    }

}
