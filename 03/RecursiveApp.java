package week03;
import java.util.Scanner;
import java.lang.Math.*;
/** Program to demonstrate a recursive method.
 *  @author Nicholas Dong
 */

public class RecursiveApp{
    /** Runs methods.
     *  @param args data that the program will use.
    */
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number");
        long num = sc.nextLong();
        System.out.println("Number of digits: " + digits(num));
        System.out.println("Sum of digits: " + sumOfDigits(num));

    }
    
    /** Counts digits in a given long.
     * @param n a given long.
     * @return number of digits in given long
     */
    public static long digits(long n){
        if(n < 0){
            n = -n;
        }
        if(n < 10){
            return 1;
        } else {
            return 1 + digits(n/10);
        }
    }
    /** Adds digits in a given long.
     * @param n a given long.
     * @return sum of digits in given long
     */
    public static long sumOfDigits(long n){
        if(Math.abs(n) < 10){
            return n%10;
        } else {
            return n%10 + sumOfDigits(n/10);
        }
    }
}
