package goosey.ui;

import goosey.common.Locale;
import javax.microedition.lcdui.*;

/**
 * Edit screen
 */
public class EditScreen extends TextBox {
    
    private BoardScreen boardScreen;

    private Command back = new Command(Locale.STR_BACK, Command.BACK, 0); 
    private Command apply = new Command(Locale.STR_APPLY, Command.SCREEN, 1); 
    
    private class EditScreenListener implements CommandListener {

        public void commandAction(Command c, Displayable d) {
            if (c == apply) {
                if (!boardScreen.getBoard().parseBoard(getString()))
                    return;
                boardScreen.setSolved(false);
            }
            DisplayManager.getDisplay().setCurrent(boardScreen); 
        }
        
    }
    
    public EditScreen(BoardScreen boardScreen) {
        super(Locale.STR_GAME, null, 2048, TextField.ANY);
        
        this.boardScreen = boardScreen;

        addCommand(back);
        addCommand(apply);
        setCommandListener(new EditScreenListener());
    }
    
}
