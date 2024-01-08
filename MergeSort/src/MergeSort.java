public class MergeSort {

    public static void main(String[] args)
    {
        int[] left = {6, 7, 8, 9, 10, 1, 2, 3, 4, 5};
        int[] right = {1, 2, 3, 4, 5};

        int[] m = mergeSort(left);

        for(int i = 0; i < m.length; i++)
        {
            System.out.println(m[i]);
        }
    }

    public static int[] mergeSort(int[] nums)
    {
        if(nums.length <= 1)
        {
            return nums;
        }else {

            int[] first = new int[nums.length / 2];
            for(int i = 0; i < nums.length/2; i++)
            {
                first[i] = nums[i];
            }
            first = mergeSort(first);
            int[] second = new int[nums.length - (nums.length / 2)];
            for(int i = nums.length/2; i < nums.length; i++)
            {
               second[i] = nums[i];
            }
            second = mergeSort(second);
            return merge(first, second);
        }

        //return merge(first, second);

    }

    private static int[] merge(int[] left, int[] right)
    {
        int[] mergedArray = new int[left.length + right.length];

        int r = 0;
        int l = 0;
        int mergedPos = 0;

        while(l < left.length && r < right.length)
        {
            if(left[l] < right[r])
            {
                mergedArray[mergedPos] = left[l];
                l++;

            }else{
                mergedArray[mergedPos] = right[r];
                r++;
            }

            mergedPos++;
        }

        while(l < left.length)
        {
            mergedArray[mergedPos] = left[l];
            l++;
            mergedPos++;
        }

        while(r < right.length)
        {
            mergedArray[mergedPos] = right[r];
            r++;
            mergedPos++;
        }

        return mergedArray;
    }
    
}
