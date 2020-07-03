package week11;

/**
 *  Quicksort implementation which is able to be observed through its
 *  Sorter superclass.
 *
 * @author Iain Hewson
 */
public class QuickSort extends Sorter {

    /**
     *  Create a new QuickSort sorter with the given integers to sort.
     * 
     * @param nums the integers to sort.
     */
    public QuickSort(Integer[] nums) {
        super(nums); // pass nums[] to the superclass Sort
    }

    /**
     * Sort the integers (which are stored in the parent Sorter class). 
     */
    public void sortNums() {
        // int i, j, comparisons, and nums[] are all protected datafields in
        // the superclass Sort so we can use them without declaring them
        comparisons = 0;
        quickSort(0, nums.length-1);    
    }

    /**
     * Sort according to a pivot point recursively.
     * @param left first value
     * @param right last value
     */
    public void quickSort(int left, int right) {
        if (left < right) {
            int p = partition(left, right);
            quickSort(left, p);
            quickSort(p+1, right);
        }
    }

    /**
     * Partition array, with pivot as first item.
     * @param low first value
     * @param high last value
     * @return location of hole
     */
    private int partition(int low, int high) {
        int pivot = nums[low];
        int hole = low;
        i = low+1;
        j = high;
        while (true) {
            while (j > hole && nums[j] >= pivot) {
                j--;
                comparisons++;
                update();
            }
            if (j == hole) {
                break;
            }      
            nums[hole] = nums[j];
            hole = j;
            
            while (i < hole && nums[i] < pivot) {
                i++;
                comparisons++;
                update();
            }
            if (i == hole){
                break;
            }
            nums[hole] = nums[i];
            hole = i;
    
        }
        nums[hole] = pivot;
        return hole;
    }   
}
