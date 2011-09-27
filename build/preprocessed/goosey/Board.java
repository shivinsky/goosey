package goosey;

import goosey.common.Utils;
import java.util.Vector;

/**
 * Board
 */
public class Board {
    
    private int height;
    
    private int width;
    
    private boolean[][] marked;
    
    private char[][] data;
    
    /**
     * Parse board
     */
    public boolean parseBoard(String board) {
        Vector list = Utils.split(Utils.toLowerCase(board.trim()), "\n");
        if (!list.isEmpty()) {
            height = list.size();
            width = ((String)list.elementAt(0)).length();
            data = new char[height][];
            for (int i = 0; i < height; i++) {
                String line = ((String)list.elementAt(i));
                if (line.length() != width)
                    return false;
                data[i] = line.toCharArray();    
            }
            marked = new boolean[height][width];
            return true;
        }
        return false;
    }
    
    /**
     * Is cell marked
     */
    public boolean isMarked(Point point) {
        return marked[point.getY()][point.getX()];
    }
    
    /**
     * Set cell marked
     */
    public void setMarked(Point point, boolean value) {
        marked[point.getY()][point.getX()] = value;
    }
    
    /**
     * Returns cell value
     */
    public char getData(Point point) {
        return data[point.getY()][point.getX()];
    }
    
    /**
     * Set cell value
     */
    public void setData(Point point, char value) {
        data[point.getY()][point.getX()] = value;
    }
        
    /**
     * Returns width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns height
     */
    public int getHeight() {
        return height;
    }
   
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < height; i++) {
            sb.append(data[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
    
}
