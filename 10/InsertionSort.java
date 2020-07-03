package week10;

/**
 * Insertion sort implementation which is able to be observed through its
 * Sorter superclass.
 *
 * @author Nicholas Dong
 */
public class InsertionSort extends Sorter {

    /**
     * Create a new SelectionSort sorter with the given integers to sort.
     * @param nums the integers to sort.
     */
    public InsertionSort(Integer[] nums) {
        super(nums); // pass nums[] to the superclass Sort
    }

    /**
     * Sort the integers (which are stored in the parent Sorter class).
     */
    public void sortNums() {
        comparisons = 0;
        for(i = 0; i < nums.length; i++) {
            findAndInsert(i, nums[i]);
            update();
        }
    }

    /**
     * Find where the next value should go, in the sorted part of the list.
     * @param index current point to be inserted.
     * @param value value of current point.
     */
    public void findAndInsert(int index, int value) {
        index--;
        j = index;
        while (j >= 0 && nums[j] > value) {
            nums[j+1] = nums[j];
            j--;
            comparisons++;
        }
        nums[j+1] = value;
    }

}

