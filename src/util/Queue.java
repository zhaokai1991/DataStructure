package util;

/**
 * Created by zhaokai on 16-6-21.
 */
public class Queue<T> {

    private LinkedList<T> queue;

    public Queue()
    {
        queue=new LinkedList<>();
    }

    public void push(T element)
    {
        queue.addLast(element);
    }

    public T peek()
    {
        if(queue.isEmpty())
            throw new NullPointerException("the queue is empty");
        else
            return queue.get(0);
    }

    public T pop()
    {
        if(queue.isEmpty())
            throw new NullPointerException("the queue is empty");
        else
            return queue.removeFirst();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public int size()
    {
        return queue.size();
    }

}
