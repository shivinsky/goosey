package goosey;

import goosey.common.Utils;
import goosey.common.Locale;
import goosey.ui.ProgressListener;
import java.util.Stack;
import java.util.Vector;


/**
 * Noodle
 */
public class Noodle extends Thread {
            
    public static final char EMPTY = '#';
    
    private final Point[] directions = {
        new Point(0, - 1), new Point(1, 0), new Point(0, 1), new Point(- 1, 0)
    };
    
    private ProgressListener listener;
    
    private Dictionary dictionary;
    
    private Board board = new Board();
    
    private Vector data = new Vector();
    
    private boolean quit;
     
    private class Node extends Point {
        
        private boolean skip;
    
        private Node(Point point, boolean skip) {
            super(point.getX(), point.getY());
            this.skip = skip;
        }
            
    }
    
    public Noodle(Board board, ProgressListener listener) {
        this.board = board;
        this.listener = listener;
    }
    
    public void run() {
        data.removeAllElements();
        for (int volume = 0; volume < Locale.ALPHABET.length(); volume++) {
            if (quit)
                return;
            if (listener != null)
                listener.onProgress();
            dictionary = new Dictionary(volume);
            if (dictionary.load()) {
                for (int i = 0; i < board.getWidth(); i++) 
                    for (int j = 0; j < board.getHeight(); j++) {
                        Point point = new Point(i, j);
                        if (board.getData(point) == EMPTY && hasNeighbors(point))
                            find(point, Locale.ALPHABET.charAt(volume));
                    }
            }
        }
        Utils.sort(data); 
    }
    
    public void quit() {
        quit = true;
    }
       
    public Vector getResult() {
        return Utils.clone(data);      
    }
        
    /**
     * Find
     */
    private void find(Point cross, char data) {
        for (int alpha = 0; alpha < Locale.ALPHABET.length(); alpha++) {
            board.setData(cross, Locale.ALPHABET.charAt(alpha));
            for (int i = 0; i < board.getWidth(); i++) 
                for (int j = 0; j < board.getHeight(); j++) {
                    Point point = new Point(i, j);
                    if (board.getData(point) == data) 
                        solve(point, cross);
                }
        }
        board.setData(cross, EMPTY);
    }
    
    /**
     * Solve noodle
     */
    public void solve(Point point, Point cross) {
        StringBuffer word = new StringBuffer();
        Stack points = new Stack();
        Stack stack = new Stack();
        stack.push(new Node(point, false));
        while (!stack.empty()) {
            Node node = (Node)stack.pop();
            board.setMarked(node, !node.skip);
            if (node.skip) {
                word.deleteCharAt(word.length() - 1);
                points.pop();
            } else {
                word.append(board.getData(node));
                points.push(node);
                stack.push(new Node(node, true));
                if (board.isMarked(cross) && word.length() > 3 &&
                    dictionary.contains(word.toString())) 
                    data.addElement(new Word(word.toString(), Utils.clone(points)));
                for (int i = 0; i < directions.length; i++) {
                    Node checkNode = new Node(node.add(directions[i]), false);
                    if (check(checkNode) && dictionary.prefixMatch(word.toString())) 
                        stack.push(checkNode);
                }
            }
        }
    }   
     
    /**
     * Check
     */
    private boolean check(Point point) {
        return point.getX() >= 0 && point.getX() < board.getWidth() && 
            point.getY() >= 0 && point.getY() < board.getHeight() &&
            !board.isMarked(point) && board.getData(point) != EMPTY;
    }
    
    /**
     * Has neighbors
     */
    private boolean hasNeighbors(Point point) {
        for (int i = 0; i < directions.length; i++)
            if (check(point.add(directions[i])))
                return true;
        return false;
    }
           
}
