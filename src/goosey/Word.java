package goosey;

import goosey.common.Comparable;
import java.util.Vector;

/**
 * Word
 */
public class Word implements Comparable {

    private Vector points;
    private String word;
    
    public Word(String word, Vector points) {
        this.points = points;
        this.word = word;
    }
    
    public Vector getPoints() {
        return points;
    }
    
    public String getWord() {
        return word;
    }

    public int compareTo(Object o) {
        String other = ((Word)o).getWord();
        if (word.length() < other.length())
            return 1;
        else if (word.length() > other.length())
            return - 1;
        else 
            return word.compareTo(other);
    }
   
}
