/* File: WordSalad.java - April 2018 */
package week09;

import java.util.*;

/**
 *  WordSalad class for COSC241 assignment 1.
 *
 *  @author Nicholas Dong, Anthony Julian, Makoto Mclennan
 */
public class WordSalad implements Iterable<String> {

    /**
     * First WordNode object in WordSalad.
     */
    private WordNode first;

    /**
     * Last WordNode object in WordSalad.
     */
    private WordNode last;

    /**
     * Number of WordNode objects in WordSalad.
     */
    private int l;

    /**
     * Base constructor for WordSalad.
     */
    public WordSalad() {
        this.first = null;
        this.last = null;
        this.l = 0;
    }

    /**
     * Constructor for WordSalad object with list of Strings.
     * @param words List of String objects.
     */
    public WordSalad(java.util.List<String> words) {
        for (String word : words) {
            addLast(word);
        }
    }

    /**
     * Method to add a word at the front of the WordSalad.
     * @param word String to be added as a WordNode.
     */
    public void add(String word) {
        if (this.first == null) {
            this.first = new WordNode(word, null);
            this.last = this.first;
            l++;
            return;
        }
        WordNode newFirst = new WordNode(word, this.first);
        this.first = newFirst;
        this.l++;
    }

    /**
     * Method to add a word at the end of the WordSalad.
     * @param word String to be added as a WordNode.
     */
    public void addLast(String word) {
        if (this.first == null) {
            add(word);
            return;
        }
        WordNode newLast = new WordNode(word, null);
        this.last.next = newLast;
        this.last = newLast;
        this.l++;
    }
            
    /**
     * Class for WordNode objects to be put into WordSalads.
     */
    private class WordNode {

        /**
         * String held by WordNode.
         */
        private String word;

        /**
         * Link to following WordNode object.
         */
        private WordNode next;

        /**
         * Constructor for WordNode object with a given String and following
         * WordNode.
         * @param word String to be held.
         * @param next desired WordNode following new WordNode.
         */
        private WordNode(String word, WordNode next) {
            this.word = word;
            this.next = next;
        }
        
    }
    
    /**
     * Interface for cycling through elements in a collection.
     * @return elements of type String.
     */
    public java.util.Iterator<String> iterator() {
        return new java.util.Iterator<String>() {
            private WordNode current = first;
      
            public boolean hasNext() {
                return current != null;
            }
      
            public String next() {
                String result = current.word;
                current = current.next;
                return result;
            }
      
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Method to return contents of WordSalad as a String.
     * @return String of words.
     */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        WordNode node = first;
        while (node != null) {
            result.append(node.word);
            result.append(node.next == null ? "" : ", ");
            node = node.next;
        }
        return result.toString() + "]";
    }


    // Method stubs to be completed for the assignment.
    // See the assignment description for specification of their behaviour.

    /**
     * A method to distribute a WordSalad into k blocks, moving to a new
     * block each time a word is added.
     * @param k number of blocks.
     * @return multi array of WordSalad objects.
     */    
    public WordSalad[] distribute(int k) {
        WordNode node = first; // assign first node for referencing
        WordSalad[] salads = new WordSalad[k]; // create WordSalad array
        int i = 0;
        
        while(node != null) {  // uses all entries
            if(salads[i] == null) { // instantiates WordSalad objects
                salads[i] = new WordSalad();
            }
            salads[i].addLast(node.word); // add word to WordSalad
            node = node.next; // move to next word
            i++;
            if(i == k) { // reaching end of WordSalad[], loop back to start
                i = 0;
            }
        }
        return salads;
    }

    /**
     * A method to chop a WordSalad into k blocks of equal size.
     * @param k number of blocks.
     * @return multi array of WordSalad objects.
     */
    public WordSalad[] chop(int k) {
        WordNode node = first; // assign first node for referencing
        WordSalad[] salads = new WordSalad[k]; // create WordSalad array
        int i = 0;
        
        int length = (l)/k;
        int remainder = (l)%k;
        int count = 0;
        
        while(count < length) {
            if(salads[i] == null){ // instantiates WordSalad objects
                salads[i] = new WordSalad();
            }
            salads[i].addLast(node.word); // add word to WordSalad
            node = node.next; // move to next word           
            count++;
            if(count == length) { // if block is full
                if(remainder > 0){ // if any words should be added as extra
                    salads[i].addLast(node.word);
                    node = node.next;
                    remainder--;
                }
                if(i == (k-1)) { //if every word has been assigned into a block
                    return salads;
                }
                i++;
                count = 0;
            }         
        }
        return salads;
    }

    /**
     * A method to distribute a WordSalad into the first block,
     * then take the remainder and split that, until no words are left.
     * Creates an ArrayList, which is later converted into an array.
     * @param k parameter to split into.
     * @return multi array of WordSalad objects.
     */
     
    public WordSalad[] split(int k) {
        WordSalad rest = new WordSalad();
        rest = this;
        List<WordSalad> salads = new ArrayList<WordSalad>();
        
        while(rest != null) {
            WordSalad salad = (new WordSalad());
            salad = rest.subSplit(k, true);
            salads.add(salad);
            rest = rest.subSplit(k, false);
        }
        return salads.toArray(new WordSalad[salads.size()]);
    }
    
    /**
     * Helper method for split. Creates two WordSalad objects. First object is
     * the first block for split, second object is the rest of the WordSalad.
     * @param k parameter to split into.
     * @param b return first WordSalad if true, return rest if false.
     * @return WordSalad object for split.
     */
    public WordSalad subSplit(int k, boolean b) {
        WordNode node = first; // assign first node for referencing
        WordSalad[] salads = new WordSalad[k]; // create WordSalad array

        int i = 0;    
        while(node != null) {  // uses all entries
            if(salads[i] == null) {
                salads[i] = new WordSalad();
            }   
            if(i == 0) {
                salads[0].addLast(node.word); // add word to WordSalad
            } else {
                salads[1].addLast(node.word);
            }
            node = node.next; // move to next word
            i++;
            if(i == k) { // reaching end of WordSalad[], loop back to start
                i = 0;
            }
        }
        if(b) {
            return salads[0];
        } else {
            return salads[1];
        }
    }
    
    /**
     * A method to sequentially take the first WordNode of a series of WordSalad
     * blocks, merging them all into a single WordSalad.
     * @param blocks multi array of WordSalad objects to be combined.
     * @return single merged WordSalad object
     */
    public static WordSalad merge(WordSalad[] blocks) {
        WordSalad salad = new WordSalad(); // create WordSalad object
        
        while(blocks[0].first != null) { //while first WordSalad has nodes
            /* insert first node of each WordSalad into bigger WordSalad
             */
            for(int i = 0; i < blocks.length; i++) {
                WordNode node = blocks[i].first;
                if(node != null) {
                    salad.addLast(node.word);
                }
            }
            /* move to the next entry of the node
             */
            for(int a = 0; a < blocks.length; a++) {
                if(blocks[a].first != null) {
                    blocks[a].first = blocks[a].first.next;
                }
            }
        }     
        return salad;
    }

    /**
     * A method to take every WordNode of a series of WordSalad
     * blocks, joining them all into a single WordSalad.
     * @param blocks multi array of WordSalad objects to be combined.
     * @return single joined WordSalad object.
     */
    public static WordSalad join(WordSalad[] blocks) {
        WordSalad salad = new WordSalad(); // create WordSalad object

        /* adding all words, in order, to a single WordSalad
         */
        for(WordSalad block : blocks) {
            WordNode node = block.first; // assign first node of block
            while(node != null) {  // uses all entries
                salad.addLast(node.word); // add word to WordSalad
                node = node.next; // move to next word
            }
        }
        return salad;
    }

    /**
     * A method for recombining an array of WordSalad objects after use of the
     * split method.
     * @param blocks multi array of WordSalad objects to be recombined.
     * @param k parameter to recombine from.
     * @return single recombined WordSalad object.
     */
     
    public static WordSalad recombine(WordSalad[] blocks, int k) {
        return null;
    }

}
