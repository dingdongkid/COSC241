package week12;

/**
 *  Practical test 2 - Part A
 *
 *  An array based implementation of Young's tableau.
 *
 * @author Iain Hewson
 */
public class TableauApp {

    /**
     * The main method is just used for testing.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        final int[][] valid = {{1, 4, 5, 10, 11}, {2, 6, 8}, {3, 9, 12}, {7}};
        System.out.println(TableauApp.toString(valid));
    }

    /**
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){
        return rowLengthsDecrease(t) && rowValuesIncrease(t) &&
            columnValuesIncrease(t) && isSetOf1toN(t);
    }

    /**
     *  Returns a string representation of a tableau.
     *
     * @param t a two-dimensional array which should be a tableau.
     *
     * @return a string representation of a tableau.
     */
    public static String toString(int[][] t) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                result.append(String.format("%-4s", t[i][j]));
            }
            if (i < t.length-1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean rowLengthsDecrease(int[][] t) {
        for(int x = 0; x < t.length-1; x++) {
            if(t[x].length < t[x+1].length) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean rowValuesIncrease(int[][] t) {
        for(int x = 0; x < t.length; x++) {
            for(int y = 0; y < t[x].length-1; y++) {
                if(t[x][y] > t[x][y+1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean columnValuesIncrease(int[][] t) {
        for(int x = 0; x < t.length; x++) {
            for(int y = 0; y < t[x].length; y++) {
                try {
                    if(t[x][y] > t[x+1][y]) {
                        return false;
                    }
                } catch(ArrayIndexOutOfBoundsException e){
                }
            }
        }
                
        return true;
    }

    /**
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isSetOf1toN(int[][] t) {
        int count = 0;

        for(int[] cols: t) {
            for(int rows: cols) {
                count++;
            }
        }

        for(int a = 1; a < count; a++) {
            boolean isFound = false;
            for(int[] cols: t) {
                for(int row: cols) {
                    if(row == a) {
                        isFound = true;
                    }
                }
            }
            if(!isFound) {
                return false;
            }
        }
        return true;
    }
}
