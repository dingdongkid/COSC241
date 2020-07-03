package week08;

import java.util.*;
import java.io.*;

/**
 *  Word generator based on letter frequency.
 * @author Nicholas Dong
 */
public class FrequencyGenerator implements WordGenerator {

    /** Random source used to generate words. */
    private Random random;

    /** Array of frequencies. */
    private double[] w;

    /** Letter frequencies. */
    private File txt = new File("letter-frequencies.txt");

    /** Length of alphabet. */
    private final int ALPHABETLENGTH = 26;

    /**
     * Constructs array of frequencies.
     * Constructs a frequency-based random word generator which uses the given
     * random source.
     *
     * @param r a source of randomness used when generating words.
     */
    public FrequencyGenerator(Random r) {
        random = r;
        try{
            Scanner scan = new Scanner(txt);
            this.w = new double[ALPHABETLENGTH];
            for(int i = 0; i < w.length; i++){
                w[i] = Double.parseDouble(scan.nextLine());
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found. Check file name and location.");
            System.exit(1);
        }
            
    }

    /**
     * Return a random word of length n.
     * @param n the required length of the word.
     * @return a random word of lenth n.
     */
    public String nextWord(int n) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < n; i++){
            char c = (char)('a' + chooseIndex(w));
            result.append(c);
        }
        return result.toString();
    }

    /**
     * Chooses a letter to insert into the random word, sorted by frequency.
     * @param w an array of letter frequencies.
     * @return an index of the array.
     */
    public int chooseIndex(double[] w){
        int i = 0;
        double r = random.nextDouble();

        while(r > w[i]){
            r -= w[i];
            i += 1;
        }
        return i;
    }
}
