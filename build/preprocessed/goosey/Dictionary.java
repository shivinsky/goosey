package goosey;

import goosey.common.Locale;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

/**
 * Dictionary 
 */
public class Dictionary {
    
    private final int BUFFER_SIZE = 8 * 1024;
    
    private TernaryTree tree = new TernaryTree();
    
    private String fileName;
    
    public Dictionary(int volume)
    {
        fileName = "/" + Locale.NAME + "/volume." + volume;
    }
    
    /**
     * Load
     */
    public boolean load() {
        InputStream reader = getClass().getResourceAsStream(fileName);
        if (reader == null)
            return false;
        byte[] buffer = new byte[BUFFER_SIZE];
        StringBuffer word = new StringBuffer();
        try {
            int count;
            while ((count = reader.read(buffer)) != - 1) {
                 ByteArrayInputStream is = new ByteArrayInputStream(buffer, 0, count);  
                 while (readLine(is, word)) {
                     tree.put(word.toString());
                     word.delete(0, word.length());
                 }  
            }
            tree.put(word.toString());
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            close(reader);
        }
        return false;
    }
    
    /**
     * Close stream
     */
    private void close(InputStream is) {
        if (is != null)
            try {
                is.close();
            } catch (IOException ex) {
            }
    }
    
    /**
     * Contains
     */
    public boolean contains(String key) {
        return tree.contains(key);
    }

    /**
     * Prefix match
     */
    public boolean prefixMatch(String prefix) {
        return tree.prefixMatch(prefix);
    }
    
    
    /**
     * Convert from CP1251 to UTF-8
     */
    private char convert(int letter) {
        if (letter == 184)
            return 0x401;
        else if (letter == 168)
            return 0x451;
        else if (letter > 191)
            return (char)(0x400 | ((letter & 0x7f) - 0x30));
        else
            return (char)letter;
    }
    
    /**
     * Read line
     */
    private boolean readLine(ByteArrayInputStream stream, StringBuffer word) {
        int readChar;
        while ((readChar = stream.read()) != - 1) {
             if (readChar == '\n') 
                 return true;
             else if (readChar != '\r')
                 word.append(convert(readChar));
        }
        return false;
    }
    
}
