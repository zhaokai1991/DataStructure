package util;

import java.util.Random;

/**
 * Created by zhaokai on 16-6-21.
 */
public class SkipList<T extends Comparable<? super T>> {

    private class SkipNode<T extends Comparable<? super T>>
    {
        T element;
        SkipNode[] forward;

        public SkipNode(T element,int level)
        {
            this.element=element;
            this.forward=new SkipNode[level];
        }
    }

    private static final int MAX_LEVEL=63;

    private SkipNode head;

    private int size;
    private int level;
    private Random rand;

    public SkipList(int level)
    {
        if(level<2)
            this.level=2;
        if(level>MAX_LEVEL)
            this.level=MAX_LEVEL;

        this.size=0;
        this.rand=new Random();
        this.head=new SkipNode(null,this.level);
    }

    public SkipList()
    {
        this(MAX_LEVEL);
    }

    /**
     * to determine the level of a new node
     * @return the new level
     */
    private int chooseLevel()
    {
        return rand.nextInt(level)+1;
    }

    /**
     * insert a new node into the SKipList in ascending order
     * @param e the element to insert
     */
    public void insert(T e)
    {
        int nodeLevel=chooseLevel();
        SkipNode<T> node=new SkipNode<>(e,nodeLevel);

        SkipNode<T> cur=head;

        for(int curLevel=nodeLevel-1;curLevel>=0;curLevel--)
        {
            while(cur.forward[curLevel]!=null&&
                    cur.forward[curLevel].element.compareTo(e)<0)
            {
                cur=cur.forward[curLevel];
            }

            node.forward[curLevel]=cur.forward[curLevel];
            cur.forward[curLevel]=node;
        }

        size++;
    }

    public void remove(T e)
    {
        if(size==0)
            return;

        SkipNode<T> cur=head;
        int curLevel=level-1;
        boolean found=false;

        while(curLevel>=0)
        {
            if(cur.forward[curLevel]==null||cur.forward[curLevel].element.compareTo(e)>0)
            {
                curLevel--;
            }
            else if(cur.forward[curLevel].element.compareTo(e)<0)
            {
                cur=cur.forward[curLevel];
            }
            else
            {
                //found the element
                SkipNode<T> node=cur.forward[curLevel];
                cur.forward[curLevel]=node.forward[curLevel];
                node.forward[curLevel]=null;

                curLevel--;
                found=true;
            }
        }

        if(found)
            size--;
    }

    /**
     * determine if the element e exists in this SkipList
     * @param e target element
     * @return true if exists, otherwise false
     */
    public boolean contains(T e)
    {
        SkipNode<T> cur=head;
        int curLevel=level-1;

        while(curLevel>=0)
        {
            if(cur.forward[curLevel]==null||cur.forward[curLevel].element.compareTo(e)>0)
            {
                curLevel--;
            }
            else if(cur.forward[curLevel].element.compareTo(e)<0)
            {
                cur=cur.forward[curLevel];
            }
            else
            {
                //found the element
                return true;
            }
        }

        return false;
    }

    /**
     * get the index-th element in this SkipList
     * @param index
     * @return the index-th element
     */
    public T get(int index)
    {
        if(index>size)
            throw new IllegalArgumentException("maximum available index is "+size);

        SkipNode<T> node=head.forward[0];
        int i=1;

        while(i!=index&&node!=null)
        {
            node=node.forward[0];
            i++;
        }

        return node.element;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size==0;
    }

}
