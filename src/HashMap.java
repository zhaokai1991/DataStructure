/**
 * Created by zhaokai on 16-6-11.
 */
public class HashMap<K,V> {

    private static final int DEFAULT_CAPACITY=17;

    private static final double DEFAULT_LOAD_FACTOR=0.75;

    private int size;//the number of pairs in the HashMap
    private int capacity;//length of the table
    private double loadFactor;
    private int threshold;//capacity*loadfactor

    Node<K,V>[] table;

    static class Pair<K,V>
    {
        private final K key;
        private V value;

        public Pair(K key,V value)
        {
            this.key=key;
            this.value=value;
        }

        public K getKey()
        {
            return key;
        }

        public V getValue()
        {
            return value;
        }

        public void setValue(V value)
        {
            this.value=value;
        }

        @Override
        public int hashCode()
        {
            return key.hashCode();
        }

        @Override
        public boolean equals(Object obj)
        {
            if(obj==null)
                return false;

            if(obj instanceof Pair)
            {
                Pair o=(Pair)obj;
                return key.equals(o.key)&&value.equals(o.value);
            }
            else
                return false;
        }

        @Override
        public String toString()
        {
            return "["+key.toString()+":"+value.toString()+"]";
        }
    }

    private class Node<K,V>
    {
        Pair<K,V> pair;
        Node<K,V> next;

        Node(K key,V value)
        {
            pair=new Pair(key,value);
            next=null;
        }

        Node(Pair<K,V> pair)
        {
            this.pair=pair;
            next=null;
        }

        @Override
        public int hashCode()
        {
            return pair.hashCode();
        }

        @Override
        public String toString()
        {
            return pair.toString();
        }
    }

    public HashMap()
    {
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
    }

    public HashMap(double loadFactor)
    {
        this(DEFAULT_CAPACITY,loadFactor);
    }

    public HashMap(int capacity)
    {
        this(capacity,DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity,double loadFactor)
    {
        this.capacity=capacity;
        this.loadFactor=loadFactor;
        this.size=0;
        this.threshold=(int)(capacity*loadFactor);
        this.table=new Node[this.capacity];
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public int size()
    {
        return size;
    }

    private void putToTable(K key,V value,Node<K,V>[] table)
    {

    }

    public void put(K key,V value)
    {
        int position=position(key);

        Node<K,V> p=table[position];

        while(p!=null)
        {
            if(p.pair.key.equals(key)) {
                p.pair.setValue(value);
                return;
            }

            p=p.next;
        }

        Node<K,V> node=new Node<K,V>(key,value);
        node.next=table[position];
        table[position]=node;
        size++;

        if(size>=threshold)
            rehash();
    }

    public V get(K key)
    {
        int position=position(key);

        Node<K,V> p=table[position];

        while(p!=null)
        {
            if(p.pair.key.equals(key))
                return p.pair.value;
            p=p.next;
        }

        return null;
    }

    public boolean containsKey(K key)
    {
        int position=position(key);

        Node<K,V> p=table[position];

        while(p!=null)
        {
            if(p.pair.key.equals(key))
                return true;
            p=p.next;
        }

        return false;
    }

    public V remove(K key)
    {
        int position=position(key);

        if(table[position]==null)
            return null;
        else
        {
            Node<K,V> p=table[position];

            if(p.pair.key.equals(key))
            {
                table[position]=p.next;
                p.next=null;
                size--;
                return p.pair.value;
            }

            while(p.next!=null)
            {
                if(p.next.pair.key.equals(key))
                {
                    Node<K,V> node=p.next;
                    p.next=node.next;
                    node.next=null;
                    size--;
                    return node.pair.value;
                }
                else
                    p=p.next;
            }

            return null;
        }
    }

    public void clear()
    {
        for(Node<K,V> node:table)
            node=null;
        size=0;
    }

    public void rehash()
    {
        capacity*=2;
        size=0;
        threshold=(int)(capacity*loadFactor);

        Node[] oldTable=table;
        table=new Node[capacity];

        for(Node<K,V> node:oldTable)
        {
            if(node==null)
                continue;
            else
            {
                Node<K,V> p=node;
                while(p!=null)
                {
                    put(p.pair.key,p.pair.value);
                    p=p.next;
                }
            }
        }

        oldTable=null;
    }

    private int hash(Object obj)
    {
        return Math.abs(obj.hashCode());
    }

    private int position(K key)
    {
        return hash(key)%capacity;
    }

    @Override
    public String toString()
    {
        StringBuilder ts=new StringBuilder();

        for(int i=0;i<capacity;i++)
        {
            ts.append(i+"->");
            if(table[i]==null)
            {
                ts.append("NULL");
            }
            else
            {
                Node p=table[i];
                while(p!=null) {
                    ts.append(p.toString() + " ");
                    p=p.next;
                }

            }
            ts.append("\n");
        }

        return ts.toString();
    }

}
