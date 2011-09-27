package goosey.ui;

import goosey.Word;
import goosey.common.Locale;
import javax.microedition.lcdui.*;
import java.util.Vector;

/**
 * Words screen
 */
public class WordScreen extends List {
    
    private BoardScreen boardScreen;
    
    private Command back = new Command(Locale.STR_BACK, Command.BACK, 0);
    
    private Vector words;

    private class WordScreenListener implements CommandListener {
        
        public void commandAction(Command c, Displayable d) {
            if (c == List.SELECT_COMMAND) {
                int index = ((List)d).getSelectedIndex();
                boardScreen.setHighlight((Word)words.elementAt(index));  
            }   
            DisplayManager.getDisplay().setCurrent(boardScreen);      
        }   
        
    }
    
    public WordScreen(BoardScreen boardScreen) {
        super(Locale.STR_GAME, List.IMPLICIT);

        this.boardScreen = boardScreen;
        
        addCommand(back);

        setCommandListener(new WordScreenListener());
    }
    
    public void setResult(Vector words) {
        this.words = words;
        
        deleteAll();
        for (int i = 0; i < words.size(); i++)
            append(((Word)words.elementAt(i)).getWord(), null);
        
        boardScreen.setSolved(true);
    }
    
}
