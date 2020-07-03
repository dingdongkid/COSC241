package week12;
import java.util.Arrays;

/**
 *  A merge sort implementation which is able to be observed through its
 *  Sorter superclass.
 *
 * @author Iain Hewson
 */
public class MergeSort extends Sorter {

    /** Second array used for merge sorting. */
    private int[] tempMergArr;

    /**
     *  Create a new MergeSort sorter with the given integers to sort.
     * 
     * @param nums the integers to sort.
     */
    public MergeSort(Integer[] nums) {
        super(nums);
        tempMergArr = new int[nums.length];       
    }

    /**
     *  Sort the integers (which are stored in the parent Sorter class)
     *  using merge sort.
     */
    public void sortNums() {
        comparisons = 0;
        mergeSort(0, nums.length-1);

    }

    public void mergeSort(int left, int right) {
        if(left < right) {
            int mid = (left + right)/2;
            mergeSort(left, mid);
            mergeSort(mid+1, right);
            merge(left, mid+1, right);
        }
        return;
    }

    public void merge(int left, int mid, int right) {
        Integer[] temp = Arrays.copyOf(nums, nums.length);
        i = left;
        j = left;
        int k = mid;

        while(i < mid && k <= right) {
            comparisons++;
            if(temp[i] < temp[k]) {
                nums[j++] = temp[i++];
                update();
            } else {
                nums[j++] = temp[k++];
                update();
            }
        }

        while(i < mid) {
            nums[j++] = temp[i++];
            comparisons++;
            update();
        }

        while(k <= right) {
            nums[j++] = temp[k++];
            comparisons++;
            update();
        }
    }
}
