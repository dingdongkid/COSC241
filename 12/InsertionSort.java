package week12;

/**
 *  An insertion sort implementation which is able to be observed through its
 *  Sorter superclass.
 *
 * @author Iain Hewson
 */
public class InsertionSort extends Sorter {

    /**
     *  Create a new InsertionSort sorter with the given integers to sort.
     * 
     * @param nums the integers to sort.
     */
    public InsertionSort(Integer[] nums) {
        super(nums);
    }

    /**
     *  Sort the integers (which are stored in the parent Sorter class)
     *  using insertion sort.
     */
    public void sortNums() {
        comparisons = 0;
        for(i = 0; i < nums.length; i++) {
            findAndInsert(i, nums[i]);
        }
    }

    public void findAndInsert(int index, int value) {
        index--;
        j = index;
        while(j >= 0 && nums[j] > value) {
            nums[j+1] = nums[j];
            j--;
            comparisons++;
            update();
        }
        nums[j+1] = value;
    }

}
