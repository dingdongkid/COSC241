package week12;

/**
 *  A selection sort implementation which is able to be observed through
 *  its Sorter superclass.
 *
 * @author Iain Hewson
 */
public class SelectionSort extends Sorter {
    
    /**
     *  Create a new SelectionSort sorter with the given integers to sort.
     * 
     * @param nums the integers to sort.
     */
    public SelectionSort(Integer[] nums) {
        super(nums);
    }

    /**
     *  Sort the integers (which are stored in the parent Sorter class)
     *  using selection sort.
     */
    public void sortNums() {
        comparisons = 0;
        for(i = 0; i < nums.length; i++) {
            swap(i, minPosition(i, nums.length));
        }
    }

    public int minPosition(int left, int right) {
        int result = left;
        int minValue = nums[left];
        for(j = left+1; j < right; j++) {
            if(nums[j] < minValue) {
                result = j;
                minValue = nums[j];
            }
            comparisons++;
            update();
        }
        return result;
    }

    public void swap(int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
