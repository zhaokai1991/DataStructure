import algorithm.Sort;

import java.util.Arrays;

/**
 * Created by zhaokai on 16-6-21.
 */
public class Main {

    public static void main(String[] args) {
        int[] array=new int[]{7,4,2,8,5,6,1};
        Sort.mergeSort(array);
        System.out.println(Arrays.toString(array));
    }
}
