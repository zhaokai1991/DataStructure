package algorithm;

/**
 * Created by Zhaokai on 2016/6/30.
 */
public class Sort {

    /**
     * Sort an anrray with Shell Sort Method
     * @param array
     */
    public static void shellSort(int[] array)
    {
        int n=array.length;
        if(n==0)
            return;

        for(int increment=n/2;increment>=1;increment/=2)
            for(int i=increment;i<n;i++)
            {
                int j=i;
                int temp=array[i];

                for(;j>=increment;j-=increment)
                {
                    if(temp<array[j-increment])
                        array[j]=array[j-increment];
                    else
                        break;
                }

                array[j]=temp;
            }
    }

    /**
     * Sort an anrray with Insertion Sort Method
     * @param array
     */
    public static void insertionSort(int[] array)
    {
        int n=array.length;

        if(n==0)
            return;

        for(int i=1;i<n;i++)
        {
            int temp=array[i];
            int j=i;
            for(;j>0;j--)
            {
                if(temp<array[j-1])
                    array[j]=array[j-1];
                else
                    break;
            }
            array[j]=temp;
        }
    }

    /*
    Sort an array in Merge Sort method
     */
    private static void mergeSort(int[] array,int left,int right)
    {
        if(left<right)
        {
            int mid=left+(right-left)/2;

            mergeSort(array,left,mid);
            mergeSort(array,mid+1,right);
            merge(array,left,right);
        }
    }

    private static void merge(int[] array,int left,int right)
    {
        int cnt=right-left+1;
        int mid=left+(right-left)/2;

        int left_start=left;
        int left_end=mid;
        int right_start=mid+1;
        int right_end=right;

        int[] temp=new int[cnt];

        int p=left_start;
        int q=right_start;
        int i=0;

        while(p<=left_end&&q<=right_end)
        {
            if(array[p]<array[q])
                temp[i++]=array[p++];
            else
                temp[i++]=array[q++];
        }

        while(q<=right_end)
            temp[i++]=array[q++];

        while(p<=left_end)
            temp[i++]=array[p++];

        int k=left;
        for(int t:temp)
            array[k++]=t;
    }

    public static void mergeSort(int[] array)
    {
        int n=array.length;

        if(n==0)
            return;

        mergeSort(array,0,n-1);
    }

    private static int partition(int[] array,int left,int right)
    {
        int temp=array[right];

        int p=left-1;

        for(int i=left;i<=right-1;i++)
        {
            if(array[i]<temp)
            {
                p++;
                int t=array[i];
                array[i]=array[p];
                array[p]=t;
            }
        }

        p++;
        int t=array[right];
        array[right]=array[p];
        array[p]=t;

        return p;
    }

    private static void quickSort(int[] array,int left,int right)
    {
        if(left<right)
        {
            int pivot=partition(array,left,right);
            quickSort(array,left,pivot-1);
            quickSort(array,pivot+1,right);
        }
    }

    public static void quickSort(int[] array)
    {
        int n=array.length;

        if(n==0)
            return;

        quickSort(array,0,n-1);
    }

}
