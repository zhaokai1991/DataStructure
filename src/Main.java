import algorithm.Sort;

import java.util.Arrays;

/**
 * Created by zhaokai on 16-6-21.
 */
public class Main {

    private static final int _1MB=1024*1024;

    public static void main(String[] args) {
        int[] array=new int[]{54,2,75,4,8,9,52,65,97,16,12,83,28};
        Sort.quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}

