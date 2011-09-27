package goosey.ui;

import goosey.ui.control.SpinEdit;
import goosey.*;
import goosey.common.Locale;

import javax.microedition.lcdui.*;
import java.util.Vector;

/**
 * Board screen
 */
public class BoardScreen extends Canvas {
        
    private EditScreen editScreen;
    private LoadScreen loadScreen;
    private WordScreen wordScreen;
    
    private Board board = new Board();
    private Word highlight;
    
    private SpinEdit spinEdit;
    
    private int size;
    
    private Command edit = new Command(Locale.STR_EDIT, Command.SCREEN, 1); 
    private Command solve = new Command(Locale.STR_SOLVE, Command.SCREEN, 0);  
    private Command back = new Command(Locale.STR_BACK, Command.SCREEN, 0); 
     
    private class TableListener implements CommandListener {
        
        public void commandAction(Command c, Displayable d) {
            if (c == edit) {
                editScreen.setString(board.toString());
                DisplayManager.getDisplay().setCurrent(editScreen);
            } else if (c == solve) 
                DisplayManager.getDisplay().setCurrent(loadScreen);
            else if (c == back) 
                DisplayManager.getDisplay().setCurrent(wordScreen);
        }
    
    }
    
    public BoardScreen() {    
        size = Font.getDefaultFont().getHeight();
        
        editScreen = new EditScreen(this);
        wordScreen = new WordScreen(this);
        loadScreen = new LoadScreen(this, wordScreen);
        
        int width = (int)(getWidth() / 100.0f * 75);
        spinEdit = new SpinEdit((getWidth() - width) / 2, 150, width, 15);
        
        board.parseBoard( 
              "#######" +
            "\n#аргюб#" +
            "\n#акубе#" +
            "\n#пeтка#" +
            "\n###яен#" +
            "\n####ч##"
        );   
        
        addCommand(edit);
        addCommand(solve);
        setCommandListener(new TableListener());      
    }
      
    protected void paintGrid(Graphics g) {
        g.setColor(0x000000);
        for (int i = 0; i < board.getWidth(); i++)
            for (int j = 0; j < board.getHeight(); j++) {
                g.drawRect(i * size, j * size, size, size);
                char symbol = board.getData(new Point(i, j));
                if (symbol != Noodle.EMPTY)
                    g.drawChar(symbol, i * size + size / 2, j * size, 
                        Graphics.TOP | Graphics.HCENTER);
            }    
    }
    
    protected void paintHighlight(Graphics g) {
        g.setColor(0xc0c0c0);   
        Vector points = highlight.getPoints();
        for (int i = 0; i < points.size(); i++) {
            Point point = (Point)points.elementAt(i);
            g.fillRect(point.getX() * size + 1, point.getY() * size + 1, 
                size - 1, size - 1);   
            if (board.getData(point) == Noodle.EMPTY) {
                g.setColor(0x000000);
                g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
                g.drawChar(highlight.getWord().charAt(i), 
                    point.getX() * size + size / 2, point.getY() * size, 
                    Graphics.TOP | Graphics.HCENTER);  
                g.setFont(Font.getDefaultFont());
                g.setColor(0xc0c0c0);
            }
        }     
    } 
    
    protected void pointerPressed(int x, int y) {
        spinEdit.pointerPressed(x, y);
    }
        
    protected void paint(Graphics g) {
        g.setColor(0xffffff);
        g.fillRect(0, 0, getWidth(), getHeight());
        
       // g.translate(getWidth() / 2 - size * board.getWidth() / 2, 
       //     getHeight() / 2 - size * board.getHeight());
        
        if (highlight != null)
            paintHighlight(g);
        paintGrid(g);
        
        spinEdit.paint(g);
    }
    
    public void setHighlight(Word word) {
        highlight = word;
    }
    
    public void setSolved(boolean solved) {
        if (solved) {
            removeCommand(solve);
            addCommand(back);
        } else {
            removeCommand(back);
            addCommand(solve);
            setHighlight(null);
        }      
    }
    
    public Board getBoard() {
        return board;
    }
    
}