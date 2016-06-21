package api;

/**
 * Created by zhaokai on 16-6-21.
 */
public interface List<T> {

    void add(T element);

    void insert(int index,T element);

    T get(int index);

    boolean isEmpty();

    T remove(int index);

    int size();
}
