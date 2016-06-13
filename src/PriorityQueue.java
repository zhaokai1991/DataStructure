import java.util.Comparator;

/**
 * Created by zhaokai on 16-6-7.
 */
public class PriorityQueue<T> {

    private static final int DEFAULT_CAPACITY=16;

    private int size;
    private int capacity;
    private Object[] elements;
    private Comparator<T>  comparator;

    public PriorityQueue()
    {
        this(DEFAULT_CAPACITY,null);
    }

    public PriorityQueue(int capacity)
    {
        this(capacity,null);
    }

    public PriorityQueue(int capacity, Comparator<T> comparator)
    {
        if(capacity<0)
            throw new IllegalArgumentException("IllegalArgument:capacity:"+capacity);

        this.capacity=capacity;
        this.size=0;
        this.elements=new Object[this.capacity+1];
        elements[0]=null;
        this.comparator=comparator;
    }

    private boolean isFull()
    {
        return size==capacity;
    }

    private void reAllocate()
    {
        if(capacity==0)
            capacity=DEFAULT_CAPACITY;
        else
            capacity*=2;
        Object[] newElements=new Object[capacity+1];

        for(int i=0;i<=size;i++)
            newElements[i]=elements[i];

        elements=newElements;
    }

    private void percolateUpWithComparable(int k,T e)
    {

        while(k>1)
        {
            int p=k/2;
            Comparable<T> parent=(Comparable<T>)elements[p];
            if(parent.compareTo(e)<=0)
                break;
            elements[k]=elements[p];
            k=p;
        }

        elements[k]=e;
    }

    private void percolateUpWithComparator(int k,T e)
    {
        while(k>1)
        {
            int p=k/2;
            Object parent=elements[p];
            if(comparator.compare((T)parent,e)<=0)
                break;
            elements[k]=parent;
            k=p;
        }

        elements[k]=e;
    }

    public void insert(T e)
    {
        if(isFull())
            reAllocate();

        int k=++size;

        if(comparator==null)
            percolateUpWithComparable(k,e);
        else
            percolateUpWithComparator(k,e);

    }

    private void percolateDownWithComparable(int k,T e)
    {
        while(2*k<=size)
        {
            int lc=2*k;
            int small=lc;

            if(lc+1<=size)
            {
                small=((Comparable<T>)elements[lc]).compareTo((T)elements[lc+1])<=0?lc:lc+1;
            }

            Comparable<T> smallChild=(Comparable<T>)elements[small];

            if(smallChild.compareTo(e)>=0)
                break;

            elements[k]=elements[small];
            k=small;
        }

        elements[k]=e;
    }

    public T pop()
    {
        if(size==0)
            throw new NullPointerException();

        T minElement=(T)elements[1];

        int k=1;
        T lastElement=(T)elements[size--];

        percolateDownWithComparable(k,lastElement);

        return minElement;
    }

    @Override
    public String toString()
    {
        StringBuilder res=new StringBuilder("[");

        for(int i=1;i<size;i++)
            res.append(elements[i].toString()+",");
        res.append(elements[size].toString()+"]");
        return res.toString();
    }
}
