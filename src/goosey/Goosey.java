package goosey;

import goosey.ui.BoardScreen;
import goosey.ui.DisplayManager;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * Goosey
 */
public class Goosey extends MIDlet {
    
    public void startApp() {     
        DisplayManager.initialize(Display.getDisplay(this));
        DisplayManager.getDisplay().setCurrent(new BoardScreen());
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
