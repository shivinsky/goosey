package goosey.common;

import java.util.Vector;
import java.util.Enumeration;

/**
 * Utilites
 */
public class Utils {
    
    /**
     * Split string
     */
    public static Vector split(String text, String delimiter)  
    {  
        Vector tokens = new Vector(); 
        int pos = 0, last;
        while ((last = text.indexOf(delimiter, pos)) >= 0) {
            tokens.addElement(text.substring(pos, last));
            pos = last + delimiter.length();
        }
        if (pos < text.length())
            tokens.addElement(text.substring(pos));           
        return tokens;  
    } 
    
    /**
     * Bubble sort
     */
    public static void sort(Vector vector) {
        for (int i = 1; i < vector.size(); i++)      
            for (int j = 0; j < vector.size() - i; j++) {
                Comparable item1 = (Comparable)vector.elementAt(j);
                Comparable item2 = (Comparable)vector.elementAt(j + 1);
                if (item1.compareTo(item2) > 0) {
                    vector.setElementAt(item2, j);
                    vector.setElementAt(item1, j + 1);
                }    
            }
    }
    
    /**
     * Clone vector
     */
    public static Vector clone(Vector vector) {
        Vector result = new Vector(vector.size());
        for (Enumeration keys = vector.elements(); keys.hasMoreElements();)
            result.addElement(keys.nextElement());
        return result;
    }
    
    /**
     * To lower case
     */
    public static String toLowerCase(String text) {
        for (int i = 0; i < Locale.ALPHABET_UPPER.length(); i++)
            text = text.replace(Locale.ALPHABET_UPPER.charAt(i), Locale.ALPHABET.charAt(i));
        return text;
    }
 
}
