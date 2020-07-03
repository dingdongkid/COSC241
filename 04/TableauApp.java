/* File: TableauApp.java - March 2018 */
package week04;

/**
 * Skeleton code for an array based implementation of Young's tableau.
 *
 * @author Nicholas Dong
 */
public class TableauApp {

    /**
     * The main method is just used for testing.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        final int[][] valid = {{1, 4, 5, 10, 11}, {2, 6, 8}, {3, 9, 12}, {7}};
        final int[][] invalid = {{1, 4, 3, 10}, {1}, {1, 2}};
        final int[][] empty = {};
        System.out.println(TableauApp.toString(valid));
        System.out.println(rowLengthsDecrease(valid));
        System.out.println(rowValuesIncrease(valid));
        System.out.println(columnValuesIncrease(valid));
        System.out.println(isSetOf1toN(valid));
        
        System.out.println(TableauApp.toString(invalid));
        System.out.println(rowLengthsDecrease(invalid));
        System.out.println(rowValuesIncrease(invalid));
        System.out.println(columnValuesIncrease(invalid));
        System.out.println(isSetOf1toN(invalid));
        
        System.out.println(TableauApp.toString(empty));
        System.out.println(rowLengthsDecrease(empty));
        System.out.println(rowValuesIncrease(empty));
        System.out.println(columnValuesIncrease(empty));
        System.out.println(isSetOf1toN(empty));
    }

    /**
     * Determines whether the array passed to it is a valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){
        if(rowLengthsDecrease(t)){
            if(rowValuesIncrease(t)){
                if(columnValuesIncrease(t)){
                    if(isSetOf1toN(t)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Returns a string representation of an array based tableau.
     *
     * @param t a two-dimensional array which represents a tableau.
     *
     * @return a string representation of an array based tableau.
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
     * Checks if rows decrease in length.
     * @param t a two-dimensional array which represents a tableau
     * @return true or false
     */
    public static boolean rowLengthsDecrease(int[][] t){
        for(int i = 0; i < (t.length-1); i++){
            if(t[i].length < t[i+1].length){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if numbers in a row increase in value.
     * @param t a two-dimensional array which represents a tableau
     * @return true or false
     */
    public static boolean rowValuesIncrease(int[][] t){
        for(int i = 0; i < (t.length-1); i++){
            for(int j = 0; j < (t[i].length-1); j++){
                if(t[i][j] > t[i][j+1]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if numbers in a column increase in value.
     * @param t a two-dimensional array which represents a tableau
     * @return true or false
     */
    public static boolean columnValuesIncrease(int[][] t){
        for(int i = 0; i < (t.length-1); i++){
            for(int j = 0; j < (t[i].length); j++){
                try{
                    if(t[i][j] > t[i+1][j]){
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return true;
    }

    /**
     * Checks if set of integers used is {1,2,...,n}.
     * @param t a two-dimensional array which represents a tableau
     * @return true or false
     */
    public static boolean isSetOf1toN(int[][] t){
        int cells = 0;
        for(int[] cols: t){
            for(int rows: cols){
                cells++;
            }
        }
        for(int a = 1; a < cells; a++){
            boolean isFound = false;
            for(int[] cols: t){
                for(int rows: cols){
                    if(rows == a){
                        isFound = true;
                    }
                }
            }
            if(!isFound){
                return false;
            }
        }
        return true;
    }
}
