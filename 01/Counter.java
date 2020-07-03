/* File: Counter.java - February 2018 */
package week01;
import java.util.Scanner;
/** A counter of lines and words in given text.
 *  @author Nicholas Dong
 */

public class Counter{

    /**
     * Reads text with Scanner.
     * @param args data that the program will use 
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int spaceCount = 0;
        int lineCount = 0;
        
        while(sc.hasNextLine()){
            String text = sc.nextLine();
            Scanner t = new Scanner(text);
            while(t.hasNext()){
                spaceCount++;
                t.next();
            }
            lineCount++;
            
        }
        
        System.out.println("lines: " + lineCount);
        System.out.println("words: " + spaceCount);
    }   
}
